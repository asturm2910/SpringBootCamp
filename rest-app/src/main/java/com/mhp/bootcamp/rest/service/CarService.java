package com.mhp.bootcamp.rest.service;

import com.mhp.bootcamp.rest.repository.Car;
import com.mhp.bootcamp.rest.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;

    public Iterable<Car> findAll() {
        return carRepo.findAll();
    }

    public Car findById(long id) {
        Car result = null;
        Optional<Car> optResult = carRepo.findById(id);

        if (optResult.isPresent()) {
            result = optResult.get();
        }

        return result;
    }

    public Car save(Car car) {
        return carRepo.save(car);
    }

    public void delete (Car car) {
        carRepo.delete(car);
    }

    public void deleteJById(long id) {
        carRepo.deleteById(id);
    }

    public Car update(Car car) {
        return carRepo.save(car);
    }
}
