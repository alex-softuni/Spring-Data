package org.automapping.exercises.service.impls;

import jakarta.validation.ConstraintViolation;
import org.automapping.exercises.data.entities.User;
import org.automapping.exercises.data.repositories.UserRepository;
import org.automapping.exercises.service.UserService;
import org.automapping.exercises.service.dtos.UserLoginDTO;
import org.automapping.exercises.service.dtos.UserRegisterDTO;
import org.automapping.exercises.util.ValidatorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private User currentLoggedInUser;

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

    @Override
    public String LoginUser(UserLoginDTO userLoginDTO) {
        Optional<User> optional = this.userRepository.findByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        if (!optional.isPresent()) {
            return "Email or password incorrect.";
        }

        this.setCurrentLoggedInUser(optional.get());
        return String.format("Successfully logged in: %s.", this.getCurrentLoggedInUser().getFullName());
    }

    @Override
    public String Logout() {
        if (this.currentLoggedInUser == null) {
            return "You are not logged in.";
        }
        String username = this.currentLoggedInUser.getFullName();
        this.currentLoggedInUser = null;
        return String.format("User: %s successfully logged out.", username);
    }

    private void setAdminIfUserIsFirst(User toPersist) {
        if (this.userRepository.count() == 0) {
            toPersist.setAdmin(true);
        }
    }


    public User getCurrentLoggedInUser() {
        return this.currentLoggedInUser;
    }

    public void setCurrentLoggedInUser(User currentLoggedInUser) {
        this.currentLoggedInUser = currentLoggedInUser;
    }
}
