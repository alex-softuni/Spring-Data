package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.entity.Attraction;
import softuni.exam.repository.AttractionRepository;
import softuni.exam.service.AttractionService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service

public class AttractionServiceImpl implements AttractionService {
    private static final String FILE_PATH = "src/main/resources/files/json/attractions.json";
    private final AttractionRepository attractionRepository;
    private final Gson gson;

    @Autowired
    public AttractionServiceImpl(AttractionRepository attractionRepository, Gson gson) {
        this.attractionRepository = attractionRepository;
        this.gson = gson;
    }


    @Override
    public boolean areImported() {
        return this.attractionRepository.count() > 0;
    }

    @Override
    public String readAttractionsFileContent() throws IOException {
        String json = Files.readString(Path.of(FILE_PATH));

        return json;
    }

    @Override
    public String importAttractions() {
        return null;
    }

    @Override
    public String exportAttractions() {
        return null;
    }
}
