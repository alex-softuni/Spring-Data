package org.example.bookshopsystem.services;

import org.example.bookshopsystem.models.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {

    void seedCategories() throws IOException;
    Set<Category> getRandomCategories();
}
