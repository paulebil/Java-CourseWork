package com.okta.mongodb.AgricultureEnterpriseApp.dao;

import com.okta.mongodb.AgricultureEnterpriseApp.model.Product;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.domain.Pageable;


@Repository
public interface ProductDao extends JpaRepository <Product, Integer> {

    // List<Product> findByCategory(String category);

    // // @Query(value = "SELECT * FROM product p where p.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    // // List<Product> findRandomProductsByCategory(String caategory, int numQ);

    // @Query(value = "SELECT * FROM product p WHERE p.category = :category ORDER BY RANDOM() LIMIT :numQ")
    // List<Product> findRandomProductsByCategory(@Param("category") String category, @Param("numQ") int numQ);

    List<Product> findByCategory(String category);

    @Query("SELECT p FROM Product p WHERE p.category = :category ORDER BY RANDOM()")
    List<Product> findRandomProductsByCategory(@Param("category") String category, Pageable pageable);
    

}

