package com.web.shop.model.table;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private String image;

}
