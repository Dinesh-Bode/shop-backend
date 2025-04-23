package com.web.shop.model.api;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
  private String name;
  private String description;
  private List<String> images;
  List<ProductVariantRequest> productVariants;
}
