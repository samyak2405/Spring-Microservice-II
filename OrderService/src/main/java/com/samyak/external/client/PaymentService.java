package com.samyak.external.client;

import com.samyak.dto.PaymentRequest;
import com.samyak.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(name="external",fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/api/payment")
@Configuration
public interface PaymentService {

    @PostMapping("/do-payment")
    public ResponseEntity<String> doPayment(@RequestBody PaymentRequest paymentRequest);

    default void fallback(Exception e){
        throw new CustomException("Payment service is not available","UNAVAILABLE",500);
    }
}
