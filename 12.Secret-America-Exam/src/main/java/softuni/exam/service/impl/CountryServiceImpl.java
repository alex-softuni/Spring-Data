package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CountryServiceImpl implements CountryService {
    private static final String FILE_PATH = "src/main/resources/files/json/countries.json";
    private final CountryRepository countryRepository;
    private final Gson gson;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, Gson gson) {
        this.countryRepository = countryRepository;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountryFileContent() throws IOException {
        String json = Files.readString(Path.of(FILE_PATH));

        return json;
    }

    @Override
    public String importCountries() {
        return null;
    }
}
