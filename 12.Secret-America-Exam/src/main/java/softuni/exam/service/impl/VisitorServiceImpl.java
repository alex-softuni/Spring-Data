package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VisitorDto;
import softuni.exam.models.dto.VisitorRootDto;
import softuni.exam.models.entity.Attraction;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.PersonalData;
import softuni.exam.models.entity.Visitor;
import softuni.exam.repository.AttractionRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.PersonalDataRepository;
import softuni.exam.repository.VisitorRepository;
import softuni.exam.service.VisitorService;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class VisitorServiceImpl implements VisitorService {
    private static final String FILE_PATH = "src/main/resources/files/xml/visitors.xml";
    private final VisitorRepository visitorRepository;
    private final PersonalDataRepository personalDataRepository;
    private final CountryRepository countryRepository;
    private final AttractionRepository attractionRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;


    @Autowired
    public VisitorServiceImpl(VisitorRepository visitorRepository, PersonalDataRepository personalDataRepository, CountryRepository countryRepository, AttractionRepository attractionRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil, XmlParser xmlParser) {
        this.visitorRepository = visitorRepository;
        this.personalDataRepository = personalDataRepository;
        this.countryRepository = countryRepository;
        this.attractionRepository = attractionRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.visitorRepository.count() > 0;
    }

    @Override
    public String readVisitorsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importVisitors() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        VisitorRootDto dtos = this.xmlParser.parse(VisitorRootDto.class, FILE_PATH);
        for (VisitorDto dto : dtos.getVisitorDtos()) {
            String fullName = dto.getFirstName() + " " + dto.getLastName();

            PersonalData personalData = this.personalDataRepository.findById(dto.getPersonalData()).orElse(null);
            Country country = this.countryRepository.findById(dto.getCountry()).orElse(null);
            Attraction attraction = this.attractionRepository.findById(dto.getAttraction()).orElse(null);

            if (this.visitorRepository.findByFullNameOrPersonalDataId(fullName, dto.getPersonalData()).isPresent()
                    || !this.validatorUtil.isValid(dto)
                    || personalData == null || country == null || attraction == null) {
                sb.append("Invalid visitor").append(System.lineSeparator());
                continue;
            }


            Visitor visitor = this.modelMapper.map(dto, Visitor.class);
            visitor.setPersonalData(personalData);
            visitor.setCountry(country);
            visitor.setAttraction(attraction);

            this.visitorRepository.save(visitor);
            sb.append("Successfully imported visitor ").append(fullName).append(System.lineSeparator());
        }


        return sb.toString();
    }
}
