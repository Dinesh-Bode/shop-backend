package com.web.shop.mapper;

import com.web.shop.model.api.ProductResponse;
import com.web.shop.model.api.ProductVariantResponse;
import com.web.shop.model.table.Product;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductMapper {
  public static ProductResponse productProductResponseMapper(Product product) {
    List<ProductVariantResponse> productVariantResponse =
        Optional.ofNullable(product.getProductVariants()).orElseGet(Collections::emptyList).stream()
            .map(
                productVariant ->
                    new ProductVariantResponse(
                        productVariant.getProductVariantId(),
                        productVariant.getSize(),
                        productVariant.getLength(),
                        productVariant.getPrice(),
                        productVariant.getQuantityAvailable()))
            .toList();

    return ProductResponse.builder()
        .productId(product.getProductId())
        .name(product.getName())
        .description(product.getDescription())
        .images(product.getImages())
        .productVariants(productVariantResponse)
        .build();
  }
}
