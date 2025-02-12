package org.automapping.exercises.service;


import org.automapping.exercises.service.dtos.AddGameDTO;

import java.util.Map;

public interface GameService {
    String addGame(AddGameDTO addGameDTO);

    String EditGame(Integer id, Map<String, String> map);
}
