package com.springbootuserdemo.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CAR")
public class Car extends AbstractEntityLongId {

    @Column
    @NotEmpty(message = "car make can't be empty")
    private String make;

    @Column
    @NotEmpty(message = "car model can't be empty")
    private String model;

    @Column
    @NotEmpty(message = "car engine can't be empty")
    private String engine;

    @Column
    @NotEmpty(message = "car transmission can't be empty")
    private String transmission;

    public Car() {

    }

    public Car(String make, String model, String engine, String transmission) {
        this.make = make;
        this.model = model;
        this.engine = engine;
        this.transmission = transmission;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}
