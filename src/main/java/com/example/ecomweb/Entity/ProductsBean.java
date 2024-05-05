package com.example.ecomweb.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ecomproducts")
public class ProductsBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    private String description;

    @Lob
    private byte[] image;

    private String imageType;

    // Additional fields
    // private Integer stock;
}
