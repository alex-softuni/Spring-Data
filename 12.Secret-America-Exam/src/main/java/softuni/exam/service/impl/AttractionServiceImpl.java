package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AttractionDto;
import softuni.exam.models.entity.Attraction;
import softuni.exam.repository.AttractionRepository;
import softuni.exam.service.AttractionService;
import softuni.exam.util.ValidatorUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service

public class AttractionServiceImpl implements AttractionService {
    private static final String FILE_PATH = "src/main/resources/files/json/attractions.json";
    private final AttractionRepository attractionRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public AttractionServiceImpl(AttractionRepository attractionRepository, Gson gson, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.attractionRepository = attractionRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }


    @Override
    public boolean areImported() {
        return this.attractionRepository.count() > 0;
    }

    @Override
    public String readAttractionsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importAttractions() throws IOException {
        StringBuilder sb = new StringBuilder();
        String json = Files.readString(Path.of(FILE_PATH));
        AttractionDto[] attractions = this.gson.fromJson(json, AttractionDto[].class);
        for (AttractionDto dto : attractions) {
            if (this.attractionRepository.findByName(dto.getName()).isPresent() || !this.validatorUtil.isValid(dto)) {
                sb.append("Invalid attraction").append(System.lineSeparator());
                continue;
            }

            Attraction attraction = this.modelMapper.map(dto, Attraction.class);
            this.attractionRepository.saveAndFlush(attraction);
            sb.append("Successfully imported attraction ").append(dto.getName()).append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String exportAttractions() throws IOException {
        return null;
    }
}