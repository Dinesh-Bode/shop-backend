package com.web.shop.model.api;

import com.web.shop.model.table.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String image;

    public static Product productMapper(ProductDetail productDetail) {
        Product product = new Product();
        product.setId(productDetail.getId());
        product.setName(productDetail.getName());
        product.setDescription(productDetail.getDescription());
        product.setPrice(productDetail.getPrice());
        product.setImage(productDetail.getImage());

        return product;
    }
}
