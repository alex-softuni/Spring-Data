package org.softuni.bg.service;

import jakarta.xml.bind.JAXBException;

public interface CategoryService extends BaseService {
    void seedCategories() throws JAXBException;

    void getCategoriesRevenueInfo() throws JAXBException;
}
