package com.web.shop.controller;

import com.web.shop.model.api.ApiResponse;
import com.web.shop.model.table.CartItem;
import com.web.shop.service.CartService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

  @Autowired private CartService cartService;
  private static final Logger logger = LogManager.getLogger();

  @PostMapping
  public ResponseEntity<ApiResponse> addToCart(@RequestBody CartItem item) {
    cartService.addToCart(item.getUserId(), item.getProductId(), item.getQuantity());
    return ResponseEntity.ok(
        ApiResponse.builder().code(HttpStatus.OK.value()).message("Item added to cart").build());
  }

  @GetMapping("/{userId}")
  public ResponseEntity<ApiResponse> getCartItems(@PathVariable String userId) {
    List<CartItem> cartItems = cartService.getCartItems(userId);
    return ResponseEntity.ok(
        ApiResponse.builder()
            .code(HttpStatus.OK.value())
            .message("Cart items fetched")
            .data(cartItems)
            .build());
  }

  @PutMapping
  public ResponseEntity<ApiResponse> updateQuantity(@RequestBody CartItem item) {
    cartService.updateQuantity(item.getUserId(), item.getProductId(), item.getQuantity());
    return ResponseEntity.ok(
        ApiResponse.builder().code(HttpStatus.OK.value()).message("Cart item updated").build());
  }

  @DeleteMapping("/item")
  public ResponseEntity<ApiResponse> removeItem(@RequestBody CartItem item) {
    cartService.removeItem(item.getUserId(), item.getProductId());
    return ResponseEntity.ok(
        ApiResponse.builder()
            .code(HttpStatus.OK.value())
            .message("Item removed from cart")
            .build());
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<ApiResponse> clearCart(@PathVariable String userId) {
    cartService.clearCart(userId);
    return ResponseEntity.ok(
        ApiResponse.builder()
            .code(HttpStatus.OK.value())
            .message("Cart cleared successfully")
            .build());
  }
}
