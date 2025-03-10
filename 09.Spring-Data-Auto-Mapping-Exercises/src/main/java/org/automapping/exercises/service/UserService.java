package org.automapping.exercises.service;

import org.automapping.exercises.service.dtos.UserLoginDTO;
import org.automapping.exercises.service.dtos.UserRegisterDTO;

public interface UserService {
    String RegisterUser(UserRegisterDTO userRegisterDTO);

    String LoginUser(UserLoginDTO userLoginDTO);

    String Logout();
}
