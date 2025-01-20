package entities.BillsPaymentSystem;

import jakarta.persistence.*;

import java.time.Month;
import java.time.Year;

@Entity
@Table(name = "credit_cards")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "expiration_month")
    private Month expriationMonth;

    @Column(name = "expiration_year")
    private Year expriationYear;

    @ManyToOne
    private User user;

    public CreditCard() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Month getExpriationMonth() {
        return expriationMonth;
    }

    public void setExpriationMonth(Month expriationMonth) {
        this.expriationMonth = expriationMonth;
    }

    public Year getExpriationYear() {
        return expriationYear;
    }

    public void setExpriationYear(Year expriationYear) {
        this.expriationYear = expriationYear;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
