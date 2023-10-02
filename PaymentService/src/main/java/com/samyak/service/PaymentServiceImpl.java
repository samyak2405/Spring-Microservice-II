package com.samyak.service;

import com.samyak.dto.PaymentMode;
import com.samyak.dto.PaymentRequest;
import com.samyak.dto.PaymentResponse;
import com.samyak.entity.TransactionDetails;
import com.samyak.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void doPayment(PaymentRequest paymentRequest) {
        log.info("Recording payment for order: {}",paymentRequest.getOrderId());
        TransactionDetails transactionDetails =
                TransactionDetails.builder()
                        .orderId(paymentRequest.getOrderId())
                        .paymentMode(paymentRequest.getPaymentMode().name())
                        .referenceNumber(paymentRequest.getReferenceNumber())
                        .paymentDate(Instant.now())
                        .paymentStatus("SUCCESS")
                        .amount(paymentRequest.getAmount())
                        .build();
        paymentRepository.save(transactionDetails);
        log.info("Transaction completed with Id: {}",transactionDetails.getId());
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
        log.info("Getting payment details for order id: {}",orderId);
        TransactionDetails transactionDetails =
                paymentRepository.findByOrderId(orderId);
        return PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .paymentDate(transactionDetails.getPaymentDate())
                .status(transactionDetails.getPaymentStatus())
                .amount(transactionDetails.getAmount())
                .build();
    }
}
