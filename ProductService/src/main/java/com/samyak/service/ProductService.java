package com.samyak.service;

import com.samyak.dto.ProductRequest;
import com.samyak.dto.ProductResponse;
import com.samyak.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product addProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    void reduceQuantity(long productId, long quantity);
}
