package org.softuni.bg.service.impls;

import jakarta.validation.ConstraintViolation;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.modelmapper.ModelMapper;
import org.softuni.bg.model.entities.User;
import org.softuni.bg.model.repositories.UserRepository;
import org.softuni.bg.service.UserService;
import org.softuni.bg.service.dtos.imports.UserSeedDto;
import org.softuni.bg.service.dtos.imports.UserSeedRootDto;
import org.softuni.bg.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class UserServiceImpl implements UserService {
    private static final String XML_PATH = "src/main/resources/xml/users.xml";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public boolean isImported() {
        return this.userRepository.count() > 0;
    }

    @Override
    public void seedUsers() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(UserSeedRootDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        UserSeedRootDto unmarshal = (UserSeedRootDto) unmarshaller.unmarshal(new File(XML_PATH));

        for (UserSeedDto user : unmarshal.getUsers()) {
            if (!this.validatorUtil.isValid(user)) {
                this.validatorUtil.getViolations(user).forEach(System.out::println);
                continue;
            }
            this.userRepository.save(modelMapper.map(user, User.class));
        }
    }
}
