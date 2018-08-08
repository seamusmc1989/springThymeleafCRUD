package com.springbootuserdemo.service;

import com.springbootuserdemo.model.Car;

import java.util.List;

public interface CarService {
    void saveCar(Car car);
    List<Car> findAllCars();
    Car findCarById(long carId);
    void deleteCar(long carId);
}
