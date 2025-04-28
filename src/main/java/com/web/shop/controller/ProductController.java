package com.web.shop.controller;

import com.web.shop.model.api.ApiResponse;
import com.web.shop.model.api.ProductRequest;
import com.web.shop.model.api.ProductResponse;
import com.web.shop.service.ProductService;
import java.util.List;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/product")
public class ProductController {

  private static final Logger logger = LogManager.getLogger();
  @Autowired ProductService productService;

  @GetMapping
  ResponseEntity<ApiResponse> getProducts() {
    ApiResponse apiResponse;
    List<ProductResponse> product = productService.getProducts();
    apiResponse =
        ApiResponse.builder()
            .code(HttpStatus.OK.value())
            .message(HttpStatus.OK.getReasonPhrase())
            .data(product)
            .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @GetMapping("{id}")
  ResponseEntity<ApiResponse> getProductById(@PathVariable(name = "id") Integer id) {
    ApiResponse apiResponse;
    ProductResponse product = productService.getProductById(id);
    apiResponse =
        ApiResponse.builder()
            .code(HttpStatus.OK.value())
            .message(HttpStatus.OK.getReasonPhrase())
            .data(product)
            .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @PostMapping()
  ResponseEntity<ApiResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
    ApiResponse apiResponse;
    ProductResponse product = productService.createProduct(productRequest);
    apiResponse =
        ApiResponse.builder()
            .code(HttpStatus.OK.value())
            .message(HttpStatus.OK.getReasonPhrase())
            .data(product)
            .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }
}
