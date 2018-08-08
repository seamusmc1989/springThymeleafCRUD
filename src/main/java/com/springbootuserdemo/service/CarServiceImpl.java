package com.springbootuserdemo.service;

import com.springbootuserdemo.controller.CarController;
import com.springbootuserdemo.model.Car;
import com.springbootuserdemo.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{


    @Autowired
    private CarRepository carRepository;

    public static final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);


    public void saveCar(Car car) {
        log.info("saveCar with make and model of: " + car.getMake() + " model: " + car.getModel());
        carRepository.save(car);
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public Car findCarById(long carId) {
        return carRepository.findOne(carId);
    }

    public void deleteCar(long carId) {
        carRepository.delete(carId);
    }
}
