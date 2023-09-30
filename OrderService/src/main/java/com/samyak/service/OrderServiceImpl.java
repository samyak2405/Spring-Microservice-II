package com.samyak.service;

import com.samyak.dto.OrderRequest;
import com.samyak.entity.Order;
import com.samyak.external.ProductService;
import com.samyak.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;
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
        log.info("Order Placed Successfully");
        return order;
    }
}
