package softuni.exam.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "personal_datas")
public class PersonalData extends BaseEntity {

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @Column
    private char gender;

    @Column
    private int age;

    public PersonalData() {
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
