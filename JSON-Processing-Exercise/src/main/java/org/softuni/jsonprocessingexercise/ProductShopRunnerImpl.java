package org.softuni.jsonprocessingexercise;

import org.softuni.jsonprocessingexercise.service.CategoryService;
import org.softuni.jsonprocessingexercise.service.ProductService;
import org.softuni.jsonprocessingexercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class ProductShopRunnerImpl implements CommandLineRunner {
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductShopRunnerImpl(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() throws FileNotFoundException {
        this.userService.seedUsers();
        this.categoryService.seedCategories();
        this.productService.seedProducts();
    }
}
