package org.automapping.exercises.service.impls;

import org.automapping.exercises.data.entities.Game;
import org.automapping.exercises.data.repositories.GameRepository;
import org.automapping.exercises.service.GameService;
import org.automapping.exercises.service.dtos.AddGameDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final ModelMapper modelMapper;
    private final GameRepository gameRepository;


    public GameServiceImpl(ModelMapper modelMapper, GameRepository gameRepository) {
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;
    }

    @Override
    public String addGame(AddGameDTO addGameDTO) {
        Optional<Game> optional = this.gameRepository.findByTitle(addGameDTO.getTitle());
        if (optional.isPresent()) {
            return "The game already exists!";
        }
        Game game = this.modelMapper.map(addGameDTO, Game.class);
        this.gameRepository.save(game);
        return "Game: " + game.getTitle() + " added successfully!";
    }
}
