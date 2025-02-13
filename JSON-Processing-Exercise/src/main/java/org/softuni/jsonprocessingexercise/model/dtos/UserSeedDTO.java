package org.softuni.jsonprocessingexercise.model.dtos;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Size;

public class UserSeedDTO {

    @Expose
    private String firstName;
    @Expose
    @Size(min = 3)
    private String lastName;
    @Expose
    private int age;

    public UserSeedDTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
