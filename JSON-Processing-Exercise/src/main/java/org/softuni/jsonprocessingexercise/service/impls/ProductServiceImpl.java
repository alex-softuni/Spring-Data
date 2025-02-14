package org.softuni.jsonprocessingexercise.service.impls;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.softuni.jsonprocessingexercise.service.dtos.ProductSeedDto;
import org.softuni.jsonprocessingexercise.model.entities.Category;
import org.softuni.jsonprocessingexercise.model.entities.Product;
import org.softuni.jsonprocessingexercise.model.entities.User;
import org.softuni.jsonprocessingexercise.model.repositories.CategoryRepository;
import org.softuni.jsonprocessingexercise.model.repositories.ProductRepository;
import org.softuni.jsonprocessingexercise.model.repositories.UserRepository;
import org.softuni.jsonprocessingexercise.service.ProductService;
import org.softuni.jsonprocessingexercise.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String FILE_PATH = "src/main/resources/json/products.json";

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedProducts() throws IOException {
        if (productRepository.count() == 0) {
            String fileContent = Files.readString(Path.of(FILE_PATH));
            ProductSeedDto[] productSeedDtos = gson.fromJson(fileContent, ProductSeedDto[].class);
            //Improves performance drastically avoiding database calls (save)
            // by savingAll :)))
            List<Product> products = new ArrayList<>();

            for (ProductSeedDto productSeedDto : productSeedDtos) {
                if (!this.validationUtil.isValid(productSeedDto)) {
                    this.validationUtil.getViolations(productSeedDto)
                            .forEach(v -> System.out.println(v.getMessage()));

                    continue;
                }

                Product product = this.modelMapper.map(productSeedDto, Product.class);
                product.setBuyer(getRandomUser(true));
                product.setSeller(getRandomUser(false));
                product.setCategories(getRandomCategories());
                products.add(product);
            }
            this.productRepository.saveAll(products);
        }
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();

        long categoryCount = categoryRepository.count();
        if (categoryCount == 0) return categories;  // Avoid errors if no categories exist

        int randomCount = ThreadLocalRandom.current().nextInt(1, 4);

        while (categories.size() < randomCount) {  // Ensure unique categories
            long randomId = ThreadLocalRandom.current().nextLong(1, categoryCount + 1);
            categoryRepository.findById(randomId).ifPresent(categories::add);
        }

        return categories;
    }

    private User getRandomUser(boolean isBuyer) {
        long randomId = ThreadLocalRandom.current().nextLong(1, this.userRepository.count() + 1);

        return isBuyer && randomId % 4 == 0 ? null : this.userRepository.findById(randomId).get();
    }
}
