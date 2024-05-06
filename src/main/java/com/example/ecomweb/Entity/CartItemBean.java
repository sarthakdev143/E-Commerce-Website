package com.example.ecomweb.Entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ecom_cart_items")
public class CartItemBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartBean cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductsBean product;

    private Integer quantity;

    // Calculate the total cost of the cart item based on quantity and product price
    public Integer getTotalCost() {
        return Integer.parseInt(product.getPrice()) * quantity;
    }
}
