package com.web.shop.service;

import com.web.shop.model.api.ProductRequest;
import com.web.shop.model.api.ProductResponse;
import java.util.List;

public interface ProductService {
  List<ProductResponse> getProducts();

  ProductResponse getProductById(Integer id);

  ProductResponse createProduct(ProductRequest productRequest);
}
