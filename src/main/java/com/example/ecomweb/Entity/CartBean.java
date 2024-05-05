package com.example.ecomweb.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ecomcart")
public class CartBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // One-to-many relationship with CartItemBean
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemBean> items = new ArrayList<>();

    // Calculate the total cost of the cart based on the total cost of the items
    public long calculateTotalCost() {
        long totalCost = 0;
        for (CartItemBean item : items) {
            totalCost += item.getTotalCost();
        }
        return totalCost;
    }

}
