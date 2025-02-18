package org.softuni.bg.service.dtos.exports;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsInPriceRangeRootDto {

    @XmlElement(name = "product")
    private List<ProductsInPriceRangeDto> products;


    public List<ProductsInPriceRangeDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsInPriceRangeDto> products) {
        this.products = products;
    }
}
