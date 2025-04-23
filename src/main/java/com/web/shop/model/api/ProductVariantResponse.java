package com.web.shop.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantResponse {
  private Integer productVariantId;
  private String size;
  private Double length;
  private Double price;
  private Integer quantityAvailable;
}
