package com.fabio.parkingapi.dtos;

import java.io.Serializable;

public class UpdatePriceTableDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private Double price;

    public UpdatePriceTableDto(){}

    public UpdatePriceTableDto(Long id, String description, Double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
