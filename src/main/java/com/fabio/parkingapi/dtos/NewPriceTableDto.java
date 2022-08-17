package com.fabio.parkingapi.dtos;

import java.io.Serializable;

public class NewPriceTableDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String description;
    private Double price;

    public NewPriceTableDto(){}

    public NewPriceTableDto(String description, Double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
