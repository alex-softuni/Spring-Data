package com.example.advquerying.services.impls;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> getIngredients() {
        return List.of();
    }

    @Override
    public List<Ingredient> findAllByNameStartingWith(String name) {
        return this.ingredientRepository.findAllByNameStartingWith(name);
    }

    @Override
    public List<Ingredient> findAllByNameIn(List<String> names) {

        return this.ingredientRepository.findAllByNameIn(names);
    }

    @Override
    public void deleteByName(String name) {
        this.ingredientRepository.deleteByName(name);
    }

    @Override
    public void updateIncreasePriceByPercentage(double percentage) {
        this.ingredientRepository.updateIncreasePriceByPercentage(percentage);
    }

    @Override
    public void updateIncreasePriceByName(String name) {
        this.ingredientRepository.updateIncreasePriceByName(name);
    }
}
