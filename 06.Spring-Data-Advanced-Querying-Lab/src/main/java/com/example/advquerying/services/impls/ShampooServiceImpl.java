package com.example.advquerying.services.impls;

import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import com.example.advquerying.services.ShampooService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {
    private final ShampooRepository shampooRepository;


    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }


    @Override
    public List<Shampoo> findByIngredientsIn(List<String> names) {
        return this.shampooRepository.findAllByIngredients_nameIn(names);
    }

    @Override
    public List<Shampoo> findBySize(Size size) {
        return this.shampooRepository.findBySizeOrderByIdAsc(size);
    }

    @Override
    public List<Shampoo> findBySizeOrLabelOrderByPriceAsc(Size size, Label label) {
        return this.shampooRepository.findBySizeOrLabelOrderByPriceAsc(size, label);
    }

    @Override
    public List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price) {
        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public int countByPriceLessThan(BigDecimal price) {
        return this.shampooRepository.countByPriceLessThan(price);
    }

    @Override
    public List<Shampoo> findByIngredientsCountLessThan(int num) {
        return this.shampooRepository.findByIngredientsCountLessThan(num);
    }




}
