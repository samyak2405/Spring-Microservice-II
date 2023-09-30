package com.samyak.service;

import com.samyak.dto.*;
import com.samyak.entity.Order;
import com.samyak.exception.CustomException;
import com.samyak.external.client.PaymentService;
import com.samyak.external.client.ProductService;
import com.samyak.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        //Order Entity -> Save the data with status order created
        //Product service -> Block Products(Reduce the Quantity)
        //Payment Service -> Payments -> Success -> COMPLETE, else Failure
        log.info("Placing order request: {}",orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
        log.info("Creating order with status created");

        Order order = Order.builder()
                .amount(orderRequest.getAmount())
                .orderStatus("CREATED")
                .orderDate(Instant.now())
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .build();
        orderRepository.save(order);
        log.info("Order Processed successfully");
        log.info("Heading to payment");

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getAmount())
                .build();

        String orderStatus = null;

        try{
            paymentService.doPayment(paymentRequest);
            log.info("Payment done successfully");
            orderStatus = "PLACED";
        }catch (Exception e){
            log.error("Error...Payment Failure");
            orderStatus = "PAYMENT_FAILURE";
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        log.info("Order Placed Successfully");
        return order;
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Get order details for orderId: {}",orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new CustomException("Order Not found",
                        "NOT_FOUND",
                        404));

        //Rest Template
        log.info("Invoking Product service to fetch product details using RestTemplate");
        ProductResponse productResponse =
                restTemplate.getForObject(
                  "http://PRODUCT-SERVICE/api/product/get/"+order.getProductId(),
                        ProductResponse.class
                );

        log.info("Getting payment information from the payment service");
        PaymentResponse paymentResponse =
                restTemplate.getForObject(
                "http://PAYMENT-SERVICE/api/payment/get/"+order.getId(),
                PaymentResponse.class
                );


        OrderResponse.ProductDetails productDetails =
                OrderResponse.ProductDetails
                        .builder()
                        .productName(productResponse.getProductName())
                        .price(productResponse.getPrice())
                        .productId(productResponse.getProductId())
                        .build();

        OrderResponse.PaymentDetails paymentDetails =
                OrderResponse.PaymentDetails.builder()
                        .paymentId(paymentResponse.getPaymentId())
                        .paymentStatus(paymentResponse.getStatus())
                        .paymentDate(paymentResponse.getPaymentDate())
                        .paymentMode(paymentResponse.getPaymentMode())
                        .build();

        return OrderResponse.builder()
                .orderStatus(order.getOrderStatus())
                .amount(order.getAmount())
                .orderId(order.getId())
                .orderDate(order.getOrderDate())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();
    }
}
