package com.web.shop.repository;

import com.web.shop.model.table.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByUserId(String userId);

  void deleteByUserId(String userId);
}
