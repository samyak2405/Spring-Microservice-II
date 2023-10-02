package com.samyak.external.client;

import com.samyak.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@CircuitBreaker(name = "external",fallbackMethod = "fallback")
@FeignClient(name = "PRODUCT-SERVICE/api/product")
@Configuration
public interface ProductService {

    @PutMapping("/reduce-quantity/{productId}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable long productId,
                                               @RequestParam long quantity);

    default void fallback(Exception e){
        throw new CustomException("Product service is not available","UNAVAILABLE",500);
    }
}
