package com.example.ecomweb.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecomweb.Entity.CartBean;
import com.example.ecomweb.Entity.ProductsBean;

@Repository
public interface CartRepository extends JpaRepository<CartBean, Long> {

    void save(ProductsBean product);

    void deleteById(int id);
    
}
