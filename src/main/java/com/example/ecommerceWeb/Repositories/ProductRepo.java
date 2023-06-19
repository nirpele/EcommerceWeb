package com.example.ecommerceWeb.Repositories;



import com.example.ecommerceWeb.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    //smart dilect
    Product findProductById(int id);

    List<Product> findByNameLike(String name);
}
