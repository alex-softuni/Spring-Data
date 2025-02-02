package org.example.bookshopsystem.services.Impls;

import org.example.bookshopsystem.models.Category;
import org.example.bookshopsystem.repositories.CategoryRepository;
import org.example.bookshopsystem.services.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private static final String CATEGORY_PATH = "src/main/resources/files/categories.txt";

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() > 0) {
            return;
        }
        Files
                .readAllLines(Path.of(CATEGORY_PATH))
                .stream()
                .filter(line -> !line.trim().isEmpty())
                .forEach(line -> {

            Category category = new Category(line);
            categoryRepository.saveAndFlush(category);

        });

    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int randInt = ThreadLocalRandom
                .current().nextInt(1, 3);
        long catRepoCount = categoryRepository.count();

        for (int i = 0; i < randInt; i++) {
            long randId = ThreadLocalRandom.current()
                    .nextLong(1, catRepoCount + 1);

            Category category = categoryRepository
                    .findById(randId)
                    .orElse(null);

            categories.add(category);
        }

        return categories;
    }
}
