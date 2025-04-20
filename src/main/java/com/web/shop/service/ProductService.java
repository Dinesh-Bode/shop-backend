package com.web.shop.service;

import com.web.shop.model.api.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductById();
}
