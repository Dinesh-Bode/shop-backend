package com.web.shop.repository;

import com.web.shop.model.table.CartItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
  List<CartItem> findByUserId(String userId);

  Optional<CartItem> findByUserIdAndProductId(String userId, String productId);

  void deleteByUserId(String userId);

  void deleteByUserIdAndProductId(String userId, String productId);
}
