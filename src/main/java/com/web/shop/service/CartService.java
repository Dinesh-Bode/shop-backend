package com.web.shop.service;

import com.web.shop.model.table.CartItem;
import com.web.shop.repository.CartItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

  @Autowired private CartItemRepository cartRepo;

  public List<CartItem> getCartItems(String userId) {
    return cartRepo.findByUserId(userId);
  }

  public void addToCart(String userId, String productId, int quantity) {
    CartItem item = cartRepo.findByUserIdAndProductId(userId, productId).orElse(new CartItem());
    item.setUserId(userId);
    item.setProductId(productId);
    item.setQuantity(item.getCartId() == null ? quantity : item.getQuantity() + quantity);
    cartRepo.save(item);
  }

  public void updateQuantity(String userId, String productId, int quantity) {
    CartItem item =
        cartRepo
            .findByUserIdAndProductId(userId, productId)
            .orElseThrow(() -> new RuntimeException("Item not found"));
    item.setQuantity(quantity);
    cartRepo.save(item);
  }

  public void removeItem(String userId, String productId) {
    cartRepo.deleteByUserIdAndProductId(userId, productId);
  }

  public void clearCart(String userId) {
    cartRepo.deleteByUserId(userId);
  }
}
