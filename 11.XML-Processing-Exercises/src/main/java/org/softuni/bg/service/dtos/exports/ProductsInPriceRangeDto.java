package org.softuni.bg.service.dtos.exports;

import jakarta.xml.bind.annotation.*;
import org.softuni.bg.model.entities.Product;

import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsInPriceRangeDto {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private BigDecimal price;
    @XmlAttribute
    private String seller;
    @XmlTransient
    private Product product;

    public Product getProduct() {
        return product;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller =
                product.getSeller().getFirstName() != null
                        ? product.getSeller().getFirstName() + " " + product.getSeller().getLastName() : product.getSeller().getLastName();
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
