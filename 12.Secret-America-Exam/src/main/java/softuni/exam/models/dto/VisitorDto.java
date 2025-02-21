package softuni.exam.models.dto;

import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "visitor")
@XmlAccessorType(XmlAccessType.FIELD)
public class VisitorDto {

    @Size(min = 2, max = 20)
    @XmlElement(name = "first_name")
    private String firstName;
    @Size(min = 2, max = 20)
    @XmlElement(name = "last_name")
    private String lastName;
    @XmlElement(name = "attraction_id")
    private long attraction;
    @XmlElement(name = "country_id")
    private long country;
    @XmlElement(name = "personal_data_id")
    private long personalData;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPersonalData() {
        return personalData;
    }

    public void setPersonalData(long personalData) {
        this.personalData = personalData;
    }

    public long getCountry() {
        return country;
    }

    public void setCountry(long country) {
        this.country = country;
    }

    public long getAttraction() {
        return attraction;
    }

    public void setAttraction(long attraction) {
        this.attraction = attraction;
    }
}
