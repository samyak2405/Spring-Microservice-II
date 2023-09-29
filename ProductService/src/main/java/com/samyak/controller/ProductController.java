package com.samyak.controller;

import com.samyak.dto.ProductRequest;
import com.samyak.dto.ProductResponse;
import com.samyak.entity.Product;
import com.samyak.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest productRequest){
        log.info("<<<<<<<<<<<<Adding Add Product API>>>>>>>>>>>>>>>");
        Product product = productService.addProduct(productRequest);
        log.info("<<<<<<<<<<<<Added Product API success>>>>>>>>>>>>>");
        return new ResponseEntity<Product>(product,HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        log.info("<<<<<<<<<<<<<<<Inside get all Product API>>>>>>>>>>>>>>>>>");
        List<ProductResponse> products = productService.getAllProducts();
        return new ResponseEntity<List<ProductResponse>>(products,HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id){
        log.info("<<<<<<<<<<<<<Inside get by Id API>>>>>>>>>>>>>>>>");
        ProductResponse productResponse = productService.getProductById(id);
        return new ResponseEntity<ProductResponse>(productResponse,HttpStatus.OK);
    }

    @PutMapping("/reduce-quantity/{productId}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable long productId,
                                               @RequestParam long quantity){
        productService.reduceQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
