package com.example.advquerying;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.LabelRepository;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.LabelService;
import com.example.advquerying.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class Runner implements CommandLineRunner {

    private final ShampooService shampooService;
    private final LabelRepository labelRepository;
    private final IngredientService ingredientService;

    public Runner(ShampooService shampooService, LabelRepository labelRepository, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
        this.labelRepository = labelRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//        List<Shampoo> medium = shampooService.findBySize(Size.MEDIUM);
//        Label label = labelRepository.findById(5L).orElseThrow();
//        List<Shampoo> list = shampooService.findBySizeOrLabelOrderByPriceAsc(Size.SMALL, label);
//        List<Shampoo> list1 = shampooService.findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal.valueOf(5));
//        List<Ingredient> ingByStartingLetter =  ingredientService.findAllByNameStartingWith("M");
//        List<Ingredient> ingByNameIn = this.ingredientService.findAllByNameIn(List.of("Lavender", "Herbs", "Apple"));
//        int countShampoosByPriceLessThan = this.shampooService.countByPriceLessThan(BigDecimal.valueOf(8.50));
//        List<Shampoo> byIngredientsCountLessThan = this.shampooService.findByIngredientsCountLessThan(2);
//        this.ingredientService.deleteByName("Apple"); Chain Deletes the whole base;
//        this.ingredientService.updateIncreasePriceByPercentage(1.1);
        this.ingredientService.updateIncreasePriceByName("Apple");

    }
}
