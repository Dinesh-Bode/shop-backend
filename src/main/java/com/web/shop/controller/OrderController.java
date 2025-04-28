package com.web.shop.controller;

import com.web.shop.model.api.ApiResponse;
import com.web.shop.model.table.Order;
import com.web.shop.service.OrderService;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  @Autowired private OrderService orderService;
  private static final Logger logger = LogManager.getLogger();

  @PostMapping("/{userId}")
  public ResponseEntity<ApiResponse> placeOrder(@PathVariable String userId) {
    Order order = orderService.placeOrder(userId);
    return ResponseEntity.ok(
        ApiResponse.builder()
            .code(HttpStatus.OK.value())
            .message("Order placed successfully")
            .data(order)
            .build());
  }

  @GetMapping("/{userId}")
  public ResponseEntity<ApiResponse> getOrders(@PathVariable String userId) {
    List<Order> orders = orderService.getOrdersByUser(userId);
    return ResponseEntity.ok(
        ApiResponse.builder()
            .code(HttpStatus.OK.value())
            .message("User orders fetched")
            .data(orders)
            .build());
  }

  @GetMapping("/detail/{orderId}")
  public ResponseEntity<ApiResponse> getOrderDetail(@PathVariable Long orderId) {

    Optional<Order> orderOptional = orderService.getOrderById(orderId);
    return orderOptional
        .map(
            order ->
                ResponseEntity.ok(
                    ApiResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("Order details fetched")
                        .data(order)
                        .build()))
        .orElseGet(
            () ->
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                        ApiResponse.builder()
                            .code(HttpStatus.NOT_FOUND.value())
                            .message("Order not found")
                            .build()));
  }
}
