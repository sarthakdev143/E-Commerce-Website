package com.example.ecomweb.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecomweb.Entity.CartBean;
import com.example.ecomweb.Entity.CartItemBean;
import com.example.ecomweb.Entity.ProductsBean;

public interface CartItemRepository extends JpaRepository<CartItemBean, Long> {

    List<CartItemBean> findByCart(CartBean cartBean);

    CartItemBean findByCartAndProduct(CartBean cart, ProductsBean productsBean);

	CartItemBean findByProduct(ProductsBean productsBean);

    List<CartItemBean> findAllByProduct(ProductsBean productsBean);
}
