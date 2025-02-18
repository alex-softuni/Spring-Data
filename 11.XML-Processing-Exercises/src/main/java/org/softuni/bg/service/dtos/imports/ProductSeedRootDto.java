package org.softuni.bg.service.dtos.imports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedRootDto {

    @XmlElement(name = "product")
    private List<ProductSeedDto> productSeedDto;

    public List<ProductSeedDto> getProducts() {
        return productSeedDto;
    }

    public void setProductSeedDto(List<ProductSeedDto> productSeedDto) {
        this.productSeedDto = productSeedDto;
    }
}
