package com.web.shop.service;

import com.web.shop.model.api.Product;
import com.web.shop.model.table.ProductDetail;
import com.web.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        List<ProductDetail> productDetailList = productRepository.findAll();
        return productDetailList.stream().map(Product::productMapper).toList();
    }

    @Override
    public Product getProductById() {
        return null;
    }
}
