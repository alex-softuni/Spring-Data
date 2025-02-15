package org.softuni.jsonprocessingexercise.service.impls;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.softuni.jsonprocessingexercise.service.dtos.Seeds.UserSeedDto;
import org.softuni.jsonprocessingexercise.model.entities.User;
import org.softuni.jsonprocessingexercise.model.repositories.UserRepository;
import org.softuni.jsonprocessingexercise.service.UserService;
import org.softuni.jsonprocessingexercise.service.dtos.export.ProductSoldDto;
import org.softuni.jsonprocessingexercise.service.dtos.export.UserSoldProductsDto;
import org.softuni.jsonprocessingexercise.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;


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
    public void seedUsers() throws IOException {
        if (this.userRepository.count() == 0) {

            UserSeedDto[] userSeedDtos = gson.fromJson(Files.readString(Path.of(FILE_PATH)), UserSeedDto[].class);

            for (UserSeedDto userSeedDTO : userSeedDtos) {
                if (!this.validationUtil.isValid(userSeedDTO)) {
                    this.validationUtil.getViolations(userSeedDTO)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }
                this.userRepository.saveAndFlush(this.modelMapper.map(userSeedDTO, User.class));
            }
        }
    }

    @Override
    public void printExportSoldProducts() {

        List<UserSoldProductsDto> dtos = this.userRepository.findAllSoldProductsWithBuyer()
                .stream()
                .filter(u ->
                        u.getSold().stream().anyMatch(p -> p.getBuyer() != null))
                .map(u -> {
                    UserSoldProductsDto dto = this.modelMapper
                            .map(u, UserSoldProductsDto.class);

                    dto.setSoldProducts(u.getSold()
                            .stream()
                            .filter(p -> p.getBuyer() != null)
                            .map(p -> this.modelMapper.map(p, ProductSoldDto.class))
                            .sorted(
                                    Comparator.comparing(ProductSoldDto::getBuyerLastName)
                                            .thenComparing(ProductSoldDto::getBuyerFirstName))
                            .toList());
                    return dto;
                })
                .toList();

        this.gson.toJson(dtos, System.out);

    }
}

