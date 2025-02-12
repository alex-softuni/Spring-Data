package org.automapping.exercises.service.impls;

import jakarta.validation.ConstraintViolation;
import org.automapping.exercises.data.entities.Game;
import org.automapping.exercises.data.repositories.GameRepository;
import org.automapping.exercises.service.GameService;
import org.automapping.exercises.service.dtos.AddGameDTO;
import org.automapping.exercises.util.ValidatorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final ModelMapper modelMapper;
    private final GameRepository gameRepository;
    private final ValidatorService validatorService;


    public GameServiceImpl(ModelMapper modelMapper, GameRepository gameRepository, ValidatorService validatorService) {
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;
        this.validatorService = validatorService;
    }

    @Override
    public String addGame(AddGameDTO addGameDTO) {
        if (!this.validatorService.isValid(addGameDTO)) {
            Set<ConstraintViolation<AddGameDTO>> validate = this.validatorService.validate(addGameDTO);
            return validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("\n"));
        }
        Optional<Game> optional = this.gameRepository.findByTitle(addGameDTO.getTitle());
        if (optional.isPresent()) {
            return "The game already exists!";
        }
        Game game = this.modelMapper.map(addGameDTO, Game.class);
        this.gameRepository.save(game);
        return "Game: " + game.getTitle() + " added successfully!";
    }

    @Override
    public String EditGame(Integer id, Map<String, String> map) {
        Optional<Game> optional = this.gameRepository.findById(Long.valueOf(id));
        if (optional.isEmpty()) {
            return "The game does not exist!";
        }

        Game game = optional.get();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            switch (entry.getKey()) {
                case "title":
                    game.setTitle(entry.getValue());
                    break;
                case "price":
                    game.setPrice(new BigDecimal(entry.getValue()));
                    break;
                case "size":
                    game.setSize(Double.parseDouble(entry.getValue()));
                    break;
            }
        }

        this.gameRepository.saveAndFlush(game);
        return "Edited " + game.getTitle();
    }
}
