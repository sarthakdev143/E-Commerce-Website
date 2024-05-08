package com.example.ecomweb.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecomweb.Entity.ProductsBean;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsBean, Long> {

    ProductsBean findByName(String productName);

}
