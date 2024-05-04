package com.example.ecomweb.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomweb.Entity.ProductsBean;
import com.example.ecomweb.Repos.ProductsRepository;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    public ProductsBean save(ProductsBean newProduct) {
        return productsRepository.save(newProduct);
    }

    public List<ProductsBean> getAllProducts() {
        return productsRepository.findAll();
    }

    public ProductsBean findById(Long id) {
        return productsRepository.findById(id).get();
    }

    public Optional<ProductsBean> OptionalfindById(Long id) {
        return productsRepository.findById(id);
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public ProductsBean findByName(String productName) {
        return productsRepository.findByName(productName);
    }
}
