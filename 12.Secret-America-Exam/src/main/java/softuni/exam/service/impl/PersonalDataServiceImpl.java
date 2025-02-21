package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PersonalDataDto;
import softuni.exam.models.dto.PersonalDataRootDto;
import softuni.exam.models.entity.PersonalData;
import softuni.exam.repository.PersonalDataRepository;
import softuni.exam.service.PersonalDataService;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class PersonalDataServiceImpl implements PersonalDataService {
    private static final String FILE_PATH = "src/main/resources/files/xml/personal_data.xml";

    private final PersonalDataRepository personalDataRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public PersonalDataServiceImpl(PersonalDataRepository personalDataRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.personalDataRepository = personalDataRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public boolean areImported() {
        return this.personalDataRepository.count() > 0;
    }

    @Override
    public String readPersonalDataFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importPersonalData() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        PersonalDataRootDto dtos = this.xmlParser.parse(PersonalDataRootDto.class, FILE_PATH);

        for (PersonalDataDto personalDataDto : dtos.getPersonalDataDtos()) {
            String cardNumber = personalDataDto.getCardNumber();

            if (this.personalDataRepository.findByCardNumber(cardNumber).isPresent() || !this.validatorUtil.isValid(personalDataDto)) {
                sb.append("Invalid personal data").append(System.lineSeparator());
                continue;
            }

            PersonalData pd = this.modelMapper.map(personalDataDto, PersonalData.class);

            this.personalDataRepository.save(pd);

            sb.append("Successfully imported personal data for visitor with card number ")
                    .append(cardNumber)
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
