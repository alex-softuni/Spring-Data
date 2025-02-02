package org.example.bookshopsystem.models;

import jakarta.persistence.*;
import org.example.bookshopsystem.models.enums.AgeRestriction;
import org.example.bookshopsystem.models.enums.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT(1000)")
    private String description;

    @Column(name = "edition_types", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private EditionType editionType;

    @Column(precision = 19, scale = 2)
    private BigDecimal price;

    @Basic(optional = false)
    private int copies;

    @Column(name = "release_date")
    LocalDate releaseDate;

    @Column(name = "age_restriction", nullable = false)
    @Enumerated(EnumType.STRING)
    private AgeRestriction AgeRestriction;

    @ManyToMany
    @JoinTable(name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories;


    @ManyToOne
    @JoinColumn(name = "authors")
    private Author author;

    public Book(){
        this.categories = new HashSet<>();
    }

    public Book(org.example.bookshopsystem.models.enums.AgeRestriction ageRestriction, LocalDate releaseDate, int copies, BigDecimal price, EditionType editionType, String title, Author author) {
        AgeRestriction = ageRestriction;
        this.releaseDate = releaseDate;
        this.copies = copies;
        this.price = price;
        this.editionType = editionType;
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return AgeRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        AgeRestriction = ageRestriction;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
