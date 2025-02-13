package org.softuni.jsonprocessingexercise.service.impls;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.softuni.jsonprocessingexercise.model.dtos.CategorySeedDTO;
import org.softuni.jsonprocessingexercise.model.entities.Category;
import org.softuni.jsonprocessingexercise.model.repositories.CategoryRepository;
import org.softuni.jsonprocessingexercise.service.CategoryService;
import org.softuni.jsonprocessingexercise.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String FILE_PATH = "src/main/resources/json/categories.json";

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedCategories() throws FileNotFoundException {
        if (this.categoryRepository.count() == 0) {
            CategorySeedDTO[] categorySeedDTOS = this.gson.fromJson(new FileReader(FILE_PATH), CategorySeedDTO[].class);
            for (CategorySeedDTO categorySeedDTO : categorySeedDTOS) {
                if (!this.validationUtil.isValid(categorySeedDTO)) {
                    this.validationUtil.getViolations(categorySeedDTO)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }
                this.categoryRepository.saveAndFlush(this.modelMapper.map(categorySeedDTO, Category.class));
            }
        }
    }
}
