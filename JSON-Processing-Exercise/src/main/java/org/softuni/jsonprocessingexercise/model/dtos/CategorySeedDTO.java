package org.softuni.jsonprocessingexercise.model.dtos;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Size;

public class CategorySeedDTO {
    @Expose
    @Size(min = 3, max = 15)
    private String name;

    public CategorySeedDTO() {}

    public CategorySeedDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
