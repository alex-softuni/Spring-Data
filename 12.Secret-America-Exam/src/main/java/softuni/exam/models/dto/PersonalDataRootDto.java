package softuni.exam.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "personal_datas")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonalDataRootDto {
    @XmlElement(name = "personal_data")
    List<PersonalDataDto> personalDataDtos;

    public List<PersonalDataDto> getPersonalDataDtos() {
        return personalDataDtos;
    }

    public void setPersonalDataDtos(List<PersonalDataDto> personalDataDtos) {
        this.personalDataDtos = personalDataDtos;
    }
}
