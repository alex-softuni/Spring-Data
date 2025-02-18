package org.softuni.bg.model.repositories;

import org.softuni.bg.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.price BETWEEN :lower AND :upper AND p.buyer IS NULL " +
            "ORDER BY p.price ")
    List<Product> findProductsInPriceRangeOrderedByPrice(BigDecimal lower, BigDecimal upper);
}
