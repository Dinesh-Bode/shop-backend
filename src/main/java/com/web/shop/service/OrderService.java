package com.web.shop.service;

import com.web.shop.model.table.CartItem;
import com.web.shop.model.table.Order;
import com.web.shop.model.table.OrderItem;
import com.web.shop.repository.CartItemRepository;
import com.web.shop.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired private OrderRepository orderRepository;

  @Autowired private CartItemRepository cartItemRepository;

  public Order placeOrder(String userId) {
    List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
    if (cartItems.isEmpty()) {
      throw new RuntimeException("Cart is empty");
    }

    Order order = new Order();
    order.setUserId(userId);
    order.setOrderDate(LocalDateTime.now());
    order.setStatus("PLACED");

    double totalAmount = 0;
    List<OrderItem> orderItems = new ArrayList<>();
    for (CartItem c : cartItems) {
      OrderItem oi = new OrderItem();
      oi.setProductId(c.getProductId());
      oi.setQuantity(c.getQuantity());
      oi.setPrice(
          getProductPrice(c.getProductId())); // You can replace this with a real price lookup
      oi.setOrder(order);
      orderItems.add(oi);
      totalAmount += oi.getPrice() * oi.getQuantity();
    }

    order.setTotalAmount(totalAmount);
    order.setItems(orderItems);

    Order savedOrder = orderRepository.save(order);
    orderRepository.deleteByUserId(userId);

    return savedOrder;
  }

  public List<Order> getOrdersByUser(String userId) {
    return orderRepository.findByUserId(userId);
  }

  public Optional<Order> getOrderById(Long orderId) {
    return orderRepository.findById(orderId);
  }

  private double getProductPrice(String productId) {
    // Mock logic – replace with real price fetch from ProductService or DB
    return 100.0;
  }
}
