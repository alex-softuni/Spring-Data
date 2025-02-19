package org.softuni.bg.service;

import jakarta.xml.bind.JAXBException;

public interface UserService extends BaseService {
    void seedUsers() throws JAXBException;

    void exportSoldProducts() throws JAXBException;
}
