package com.web.shop.model.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantRequest {
  @NotBlank(message = "Size cannot be blank")
  private String size;

  @NotNull(message = "Length cannot be null")
  @Positive(message = "Length must be positive")
  private Double length;

  @NotNull(message = "Price cannot be null")
  @Positive(message = "Price must be positive")
  private Double price;

  @NotNull(message = "Quantity available cannot be null")
  @Positive(message = "Quantity must be positive")
  private Integer quantityAvailable;
}
