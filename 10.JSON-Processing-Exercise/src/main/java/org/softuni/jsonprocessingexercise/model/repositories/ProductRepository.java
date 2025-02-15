package org.softuni.jsonprocessingexercise.model.repositories;

import org.softuni.jsonprocessingexercise.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findAllByBuyerIsNullAndPriceBetweenOrderByPrice(BigDecimal min, BigDecimal max);
}
