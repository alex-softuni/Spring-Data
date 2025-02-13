package org.softuni.jsonprocessingexercise.service.impls;

import com.google.gson.Gson;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.softuni.jsonprocessingexercise.model.dtos.UserSeedDTO;
import org.softuni.jsonprocessingexercise.model.entities.User;
import org.softuni.jsonprocessingexercise.model.repositories.UserRepository;
import org.softuni.jsonprocessingexercise.service.UserService;
import org.softuni.jsonprocessingexercise.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static final String FILE_PATH = "src/main/resources/json/users.json";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        if (this.userRepository.count() == 0) {
            UserSeedDTO[] userSeedDTOS = gson.fromJson(new FileReader(FILE_PATH), UserSeedDTO[].class);
            for (UserSeedDTO userSeedDTO : userSeedDTOS) {
                if (!this.validationUtil.isValid(userSeedDTO)) {
                this.validationUtil.getViolations(userSeedDTO)
                        .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }
                this.userRepository.saveAndFlush(this.modelMapper.map(userSeedDTO, User.class));
            }
        }
    }
}
