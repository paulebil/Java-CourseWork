package com.okta.mongodb.AgricultureEnterpriseApp.dao;

import com.okta.mongodb.AgricultureEnterpriseApp.model.Product;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository <Product, Integer> {

    List<Product> findByCategory(String category);

    @Query(value = "SELECT * FROM product p where p.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Product> findRandomProductsByCategory(String caategory, int numQ);
}
