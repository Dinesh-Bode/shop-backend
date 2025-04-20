package com.web.shop.controller;

import com.web.shop.model.api.ApiResponse;
import com.web.shop.model.api.Product;
import com.web.shop.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LogManager.getLogger();
    @Autowired
    ProductService productService;

    @GetMapping
    ResponseEntity<ApiResponse> getProducts() {
        ApiResponse apiResponse = ApiResponse.builder().build();

        try{
            List<Product> product = productService.getProducts();
            apiResponse = ApiResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .data(product)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Exception at getProducts :: {}", String.valueOf(exception));
            apiResponse = ApiResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
