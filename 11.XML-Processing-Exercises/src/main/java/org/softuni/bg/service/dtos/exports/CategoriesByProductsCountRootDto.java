package org.softuni.bg.service.dtos.exports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesByProductsCountRootDto {

    @XmlElement(name = "category")
    List<CategoriesByProductsCountDto> categoriesByProductsCountDto;

    public List<CategoriesByProductsCountDto> getCategoriesByProductsCountDto() {
        return categoriesByProductsCountDto;
    }

    public void setCategoriesByProductsCountDto(List<CategoriesByProductsCountDto> categoriesByProductsCountDto) {
        this.categoriesByProductsCountDto = categoriesByProductsCountDto;
    }
}
