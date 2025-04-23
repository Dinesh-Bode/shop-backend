package com.web.shop.model.table;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_variant")
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productVariantId;
    private String size;
    private Double length;
    private Double price;
    private Integer quantityAvailable;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}