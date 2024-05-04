package com.example.ecomweb.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecomweb.Entity.ProductsBean;

public interface ProductsRepository extends JpaRepository<ProductsBean, Long> {

    ProductsBean findByName(String productName);
    
}
