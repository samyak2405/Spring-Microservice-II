package com.samyak.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "PRODUCT-SERVICE/api/product")
@Configuration
public interface ProductService {

    @PutMapping("/reduce-quantity/{productId}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable long productId,
                                               @RequestParam long quantity);
}
