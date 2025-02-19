package org.softuni.bg;

import org.softuni.bg.service.CategoryService;
import org.softuni.bg.service.ProductService;
import org.softuni.bg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ConsoleRunner(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userService.isImported()) {
            userService.seedUsers();
        }
        if (!categoryService.isImported()) {
            categoryService.seedCategories();
        }
        if (!productService.isImported()) {
            productService.seedProducts();
        }

        // this.productService.exportProductsInPriceRangeOrderedByPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        this.userService.exportSoldProducts();
    }
}
