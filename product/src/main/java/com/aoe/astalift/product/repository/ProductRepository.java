package com.aoe.astalift.product.repository;

import com.aoe.astalift.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by joey on 16-3-16.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByType(String type);
}
