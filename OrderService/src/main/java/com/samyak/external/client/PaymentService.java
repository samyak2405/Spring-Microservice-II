package com.samyak.external.client;

import com.samyak.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE/api/payment")
@Configuration
public interface PaymentService {

    @PostMapping("/do-payment")
    public ResponseEntity<String> doPayment(@RequestBody PaymentRequest paymentRequest);
}
