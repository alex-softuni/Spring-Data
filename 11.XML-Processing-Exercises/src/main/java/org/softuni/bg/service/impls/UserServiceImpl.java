package org.softuni.bg.service.impls;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.softuni.bg.model.entities.User;
import org.softuni.bg.model.repositories.UserRepository;
import org.softuni.bg.service.UserService;
import org.softuni.bg.service.dtos.exports.SoldProductDto;
import org.softuni.bg.service.dtos.exports.UserSoldProductsDto;
import org.softuni.bg.service.dtos.exports.UserSoldProductsRootDto;
import org.softuni.bg.service.dtos.imports.UserSeedDto;
import org.softuni.bg.service.dtos.imports.UserSeedRootDto;
import org.softuni.bg.util.ValidatorUtil;
import org.softuni.bg.util.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private static final String XML_PATH = "src/main/resources/xml/users.xml";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil, XmlParser xmlParser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean isImported() {
        return this.userRepository.count() > 0;
    }

    @Override
    public void seedUsers() throws JAXBException {
        UserSeedRootDto parse = this.xmlParser.parse(UserSeedRootDto.class, XML_PATH);

        for (UserSeedDto user : parse.getUsers()) {
            if (!this.validatorUtil.isValid(user)) {
                this.validatorUtil.getViolations(user).forEach(System.out::println);
                continue;
            }
            this.userRepository.save(modelMapper.map(user, User.class));
        }
    }

    @Override
    public void exportSoldProducts() throws JAXBException {

        List<User> users = this.userRepository.findAllBySoldWithBuyer();
        List<UserSoldProductsDto> userSoldProductsDtos = users.stream().map(u -> {
            UserSoldProductsDto dto = this.modelMapper.map(u, UserSoldProductsDto.class);
            List<SoldProductDto> soldDto = u.getSold().stream().map(p -> this.modelMapper.map(p, SoldProductDto.class)).toList();
            dto.setSoldProducts(soldDto);
            return dto;
        }).toList();

        UserSoldProductsRootDto root = new UserSoldProductsRootDto();
        root.setUserSoldProductsDto(userSoldProductsDtos);
        System.out.println();
        this.xmlParser.exportToFile(root, XML_PATH);


    }
}
