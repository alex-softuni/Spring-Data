package org.softuni.bg.service;

import jakarta.xml.bind.JAXBException;

public interface ProductService extends BaseService {
    void seedProducts() throws JAXBException;
}
