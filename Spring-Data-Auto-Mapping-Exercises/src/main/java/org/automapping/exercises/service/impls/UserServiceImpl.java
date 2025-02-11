package org.automapping.exercises.service.impls;

import jakarta.validation.ConstraintViolation;
import org.automapping.exercises.data.entities.User;
import org.automapping.exercises.data.repositories.UserRepository;
import org.automapping.exercises.service.UserService;
import org.automapping.exercises.service.dtos.UserRegisterDTO;
import org.automapping.exercises.util.ValidatorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ValidatorService validatorService;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, ValidatorService validatorService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.validatorService = validatorService;
    }


    @Override
    public String RegisterUser(UserRegisterDTO userRegisterDTO) {
        if (!this.validatorService.isValid(userRegisterDTO)) {
            Set<ConstraintViolation<UserRegisterDTO>> violations = this.validatorService.validate(userRegisterDTO);
            return violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("\n"));
        }
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return "Passwords do not match";
        }

        Optional<User> optional = this.userRepository.findByEmail(userRegisterDTO.getEmail());
        if (optional.isPresent()) {
            return "User with this email already exists";
        }

        User user = this.modelMapper.map(userRegisterDTO, User.class);
        setAdminIfUserIsFirst(user);
        this.userRepository.save(user);
        return String.format("Successfully registered %s", user.getFullName());
    }

    private void setAdminIfUserIsFirst(User toPersist) {
        if (this.userRepository.count() == 0) {
            toPersist.setAdmin(true);
        }
    }
}
