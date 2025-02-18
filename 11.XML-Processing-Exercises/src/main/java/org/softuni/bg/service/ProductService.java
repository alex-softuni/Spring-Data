package org.softuni.bg.service;

import jakarta.xml.bind.JAXBException;

import java.math.BigDecimal;

public interface ProductService extends BaseService {
    void seedProducts() throws JAXBException;

    void exportProductsInPriceRangeOrderedByPrice(BigDecimal lower, BigDecimal upper) throws JAXBException;
}
