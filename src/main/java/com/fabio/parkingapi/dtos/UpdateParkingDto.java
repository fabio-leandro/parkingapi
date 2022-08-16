package com.fabio.parkingapi.dtos;

import com.fabio.parkingapi.entities.enums.PeriodType;

import java.io.Serializable;

public class UpdateParkingDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String license;
    private String model;
    private String color;
    private PeriodType periodType;

    public UpdateParkingDto(){}

    public UpdateParkingDto(Long id, String license, String model, String color, PeriodType periodType) {
        this.id = id;
        this.license = license;
        this.model = model;
        this.color = color;
        this.periodType = periodType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }
}
