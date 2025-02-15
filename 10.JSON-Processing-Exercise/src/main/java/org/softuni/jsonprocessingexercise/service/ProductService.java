package org.softuni.jsonprocessingexercise.service;

import java.io.IOException;

public interface ProductService {
    void seedProducts() throws IOException;

    void printExportProductsInPriceRangeBetween(int min, int max);

}
