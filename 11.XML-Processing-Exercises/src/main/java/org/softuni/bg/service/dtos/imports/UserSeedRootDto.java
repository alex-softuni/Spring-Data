package org.softuni.bg.service.dtos.imports;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSeedRootDto {

  
    @XmlElement(name = "user")
    private List<UserSeedDto> users;

    public List<UserSeedDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserSeedDto> users) {
        this.users = users;
    }
}
