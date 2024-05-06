package com.example.ecomweb.DTOs;

import jakarta.persistence.Lob;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductsDto {
    private Integer id;
    private String name;
    private String price;
    private String description;
    @Lob
    private byte[] image;
    private String imageType;
}
