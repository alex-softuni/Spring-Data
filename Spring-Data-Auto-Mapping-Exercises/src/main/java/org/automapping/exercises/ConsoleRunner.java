package org.automapping.exercises;

import org.automapping.exercises.service.UserService;
import org.automapping.exercises.service.dtos.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private static final Scanner sc = new Scanner(System.in);

    private final UserService userService;

    @Autowired
    public ConsoleRunner(UserService userService) {
        this.userService = userService;
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


            }
            System.out.println(command);
            line = sc.nextLine();
        }
    }
}
