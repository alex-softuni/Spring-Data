package org.softuni.bg.service.dtos.exports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldProductsRootDto {

    @XmlElement(name = "user")
    private List<UserSoldProductsDto> userSoldProductsDto;

    public List<UserSoldProductsDto> getUserSoldProductsDto() {
        return userSoldProductsDto;
    }

    public void setUserSoldProductsDto(List<UserSoldProductsDto> userSoldProductsDto) {
        this.userSoldProductsDto = userSoldProductsDto;
    }
}
