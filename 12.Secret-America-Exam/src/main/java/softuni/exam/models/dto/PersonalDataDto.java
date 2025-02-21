package softuni.exam.models.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import softuni.exam.util.LocalDateAdapter;

import java.time.LocalDate;

@XmlRootElement(name = "personal_data")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonalDataDto {

    @Min(1)
    @Max(100)
    @XmlElement
    private int age;

    @XmlElement
    private char gender;

    @Size(min = 9, max = 9)
    @XmlElement(name = "card_number")
    private String cardNumber;

    @Past
    @XmlElement(name = "birth_date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthDate;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
