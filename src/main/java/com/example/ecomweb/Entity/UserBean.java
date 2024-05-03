package com.example.ecomweb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ecomusers")
public class UserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name, address;
    private long phoneNumber;    
}