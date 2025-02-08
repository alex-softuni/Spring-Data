package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getIngredients();

    List<Ingredient> findAllByNameStartingWith(String name);

    List<Ingredient> findAllByNameIn(List<String> names);

    void deleteByName(String name);

    void updateIncreasePriceByPercentage(double percentage);

    void updateIncreasePriceByName(String name);
}
