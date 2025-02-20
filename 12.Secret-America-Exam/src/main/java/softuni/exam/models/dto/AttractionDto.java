package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


public class AttractionDto {
    @Expose
    @Size(min = 10, max = 100)
    private String description;

    @Expose
    @PositiveOrZero
    private int elevation;

    @Expose
    @Size(min = 5, max = 40)
    private String name;

    @Expose
    @Size(min = 3, max = 30)
    private String type;

    @Expose
    @SerializedName("country")
    private Long countryId;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
