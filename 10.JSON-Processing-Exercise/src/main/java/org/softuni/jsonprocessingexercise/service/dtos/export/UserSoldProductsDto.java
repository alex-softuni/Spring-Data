package org.softuni.jsonprocessingexercise.service.dtos.export;

import com.google.gson.annotations.Expose;
import java.util.List;

public class UserSoldProductsDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private List<ProductSoldDto> soldProducts;

    public List<ProductSoldDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductSoldDto> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
