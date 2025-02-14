package org.softuni.jsonprocessingexercise.service;

import java.io.IOException;

public interface UserService {
    void seedUsers() throws IOException;

    void printExportSoldProducts();
}
