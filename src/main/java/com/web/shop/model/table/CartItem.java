package com.web.shop.model.table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class CartItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cartId;

  @NotBlank(message = "UserId cannot be blank")
  private String userId;

  @NotBlank(message = "ProductId cannot be blank")
  private String productId;

  @NotBlank(message = "Quantity cannot be blank")
  @Positive(message = "Quantity must be positive")
  private int quantity;
}
