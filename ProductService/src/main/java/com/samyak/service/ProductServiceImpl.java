package com.samyak.service;

import com.samyak.dto.ProductRequest;
import com.samyak.dto.ProductResponse;
import com.samyak.entity.Product;
import com.samyak.exception.ProductServiceCustomException;
import com.samyak.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(ProductRequest productRequest) {
        log.info("<<<<<<<<<<<<<<<<<Inside add product service>>>>>>>>>>>>>>>>");
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        log.info("<<<<<<<<<<<<<<<<Product created>>>>>>>>>>>>>>>>>>>>>>>>");
        return productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
        log.info("<<<<<<<<<<<Inside get all Products service>>>>>>>>>>>>>>");

        return productRepository
                .findAll()
                .stream()
                .map(product -> ProductResponse.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        log.info("<<<<<<<<<<<<Inside find by Id service>>>>>>>>>>>>>>>>.");
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND"));

        ProductResponse productResponse = new ProductResponse();
        copyProperties(product,productResponse);
        log.info("<<<<<<<<<<<<<<<Product found successfully>>>>>>>>>>>>>>");
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce quantity of product {} by {}",productId,quantity);

        Product product = productRepository.findById(productId).orElseThrow(
                ()->new ProductServiceCustomException(
                        "Product with given ID not found",
                        "PRODUCT_NOT_FOUND"
                )
        );
        if(product.getQuantity()<quantity){
            throw new ProductServiceCustomException(
              "Product does not have sufficient quantity",
              "INSUFFICIENT_QUANTITY"
            );
        }
        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
        log.info("Product quantity updated successfully");
    }


}
