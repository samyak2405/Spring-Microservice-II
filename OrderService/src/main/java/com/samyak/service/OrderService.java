package com.samyak.service;

import com.samyak.dto.OrderRequest;
import com.samyak.dto.OrderResponse;
import com.samyak.entity.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
