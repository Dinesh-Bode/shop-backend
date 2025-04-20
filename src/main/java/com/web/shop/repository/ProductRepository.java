package com.web.shop.repository;

import com.web.shop.model.table.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductDetail, Integer> {
}
