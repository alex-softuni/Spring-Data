package org.automapping.exercises.service.dtos;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddGameDTO {

    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 symbols")
    @Pattern(regexp = "^[A-Z].*", message = "Titles should start with uppercase letter")
    private String title;

    @Positive
    private BigDecimal price;
    @Positive
    private double size;

    @Size(min = 11, max = 11)
    private String trailer;

    @Pattern(regexp = "^(?:http://)*(?:https://)*.+", message = "Invalid URL ---> Valid URL start with 'http://' or 'https://'")
    private String thumbnail_url;

    @Size(min = 20, message = "Description must be at least 20 symbols")
    private String description;

    private LocalDate releaseDate;

    public AddGameDTO(String title, BigDecimal price, double size, String trailer, String thumbnail_url, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnail_url = thumbnail_url;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
