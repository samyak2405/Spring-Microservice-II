package com.samyak.service;

import com.samyak.dto.PaymentRequest;
import com.samyak.dto.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    void doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
