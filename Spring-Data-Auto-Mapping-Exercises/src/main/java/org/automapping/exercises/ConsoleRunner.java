package org.automapping.exercises;

import org.automapping.exercises.service.GameService;
import org.automapping.exercises.service.UserService;
import org.automapping.exercises.service.dtos.AddGameDTO;
import org.automapping.exercises.service.dtos.UserLoginDTO;
import org.automapping.exercises.service.dtos.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private static final Scanner sc = new Scanner(System.in);

    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public ConsoleRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {

        String line;
        while (!(line = sc.nextLine()).equals("exit")) {
            String[] tokens = line.split("\\|");
            String command = tokens[0];
            switch (command) {
                case "RegisterUser":
                    command = userService.RegisterUser(new UserRegisterDTO(tokens[1], tokens[2], tokens[3], tokens[4]));
                    break;
                case "LoginUser":
                    command = userService.LoginUser(new UserLoginDTO(tokens[1], tokens[2]));
                    break;
                case "Logout":
                    command = userService.Logout();
                    break;
                case "AddGame":
                    command = gameService.addGame(new AddGameDTO(tokens[1], new BigDecimal(tokens[2]), Double.parseDouble(tokens[3]), tokens[4], tokens[5], tokens[6], LocalDate.parse(tokens[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                    break;
                case "EditGame":
                    Map<String, String> map = Arrays.stream(tokens).skip(2).map(p -> p.split("="))
                            .collect(Collectors.toMap(p -> p[0], p -> p[1]));
                    command = gameService.EditGame(Long.parseLong(tokens[1]), map);
                    break;
                case "DeleteGame":
                    command = gameService.DeleteGame(Long.parseLong(tokens[1]));
                    break;

            }
            System.out.println(command);
        }
    }
}
