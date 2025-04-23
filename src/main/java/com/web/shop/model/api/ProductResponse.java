package com.web.shop.model.api;

import java.util.List;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
  private Integer productId;
  private String name;
  private String description;
  private List<String> images;
  List<ProductVariantResponse> productVariants;
}
