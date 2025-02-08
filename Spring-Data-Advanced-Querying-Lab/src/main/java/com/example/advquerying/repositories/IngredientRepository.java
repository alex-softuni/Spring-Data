package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findAllByNameStartingWith(String name);

    List<Ingredient> findAllByNameIn(List<String> names);

    @Transactional
    void deleteByName(String name);

    @Query("UPDATE Ingredient i SET i.price = i.price * :percentage")
    @Modifying
    @Transactional
    void updateIncreasePriceByPercentage(double percentage);

    //Create a method that updates the price of all ingredients, which names are in a given list.

    @Query("update Ingredient i set i.price = i.price + 11 where i.name = :name")
    @Modifying
    @Transactional
    void updateIncreasePriceByName(String name);


}
