package org.softuni.bg.service.dtos.imports;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedRootDto {

    @XmlElement(name = "product")
    private List<ProductSeedDto> productSeedDto;

    public List<ProductSeedDto> getProductSeedDto() {
        return productSeedDto;
    }

    public void setProductSeedDto(List<ProductSeedDto> productSeedDto) {
        this.productSeedDto = productSeedDto;
    }
}

