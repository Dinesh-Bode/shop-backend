package com.web.shop.model.api;

import lombok.*;

import java.util.List;

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
