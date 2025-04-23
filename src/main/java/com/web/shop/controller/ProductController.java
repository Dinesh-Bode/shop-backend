package com.web.shop.controller;

import com.web.shop.model.api.ApiResponse;
import com.web.shop.model.api.ProductRequest;
import com.web.shop.model.api.ProductResponse;
import com.web.shop.service.ProductService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

  private static final Logger logger = LogManager.getLogger();
  @Autowired ProductService productService;

  @GetMapping
  ResponseEntity<ApiResponse> getProducts() {
    ApiResponse apiResponse;
    try {
      List<ProductResponse> product = productService.getProducts();
      apiResponse =
          ApiResponse.builder()
              .code(HttpStatus.OK.value())
              .message(HttpStatus.OK.getReasonPhrase())
              .data(product)
              .build();
      return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    } catch (Exception exception) {
      logger.error("Exception at getProducts :: {}", String.valueOf(exception));
      apiResponse =
          ApiResponse.builder()
              .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
              .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
              .build();
      return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("{id}")
  ResponseEntity<ApiResponse> getProductById(@PathVariable(name = "id") Integer id) {
    ApiResponse apiResponse;
    try {
      ProductResponse product = productService.getProductById(id);
      apiResponse =
          ApiResponse.builder()
              .code(HttpStatus.OK.value())
              .message(HttpStatus.OK.getReasonPhrase())
              .data(product)
              .build();
      return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    } catch (Exception exception) {
      logger.error("Exception at getProductById :: {}", String.valueOf(exception));
      apiResponse =
          ApiResponse.builder()
              .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
              .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
              .build();
      return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping()
  ResponseEntity<ApiResponse> createProduct(@RequestBody ProductRequest productRequest) {
    ApiResponse apiResponse;
    try {
      ProductResponse product = productService.createProduct(productRequest);
      apiResponse =
          ApiResponse.builder()
              .code(HttpStatus.OK.value())
              .message(HttpStatus.OK.getReasonPhrase())
              .data(product)
              .build();
      return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    } catch (Exception exception) {
      logger.error("Exception at createProduct :: {}", String.valueOf(exception));
      apiResponse =
          ApiResponse.builder()
              .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
              .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
              .build();
      return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
