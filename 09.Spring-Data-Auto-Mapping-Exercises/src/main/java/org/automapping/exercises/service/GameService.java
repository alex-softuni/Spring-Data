package org.automapping.exercises.service;


import org.automapping.exercises.service.dtos.AddGameDTO;

import java.util.Map;

public interface GameService {
    String addGame(AddGameDTO addGameDTO);

    String EditGame(Long id, Map<String, String> map);

    String DeleteGame(long id);
}
