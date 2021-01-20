package com.dao.homework.service;

import com.dao.homework.model.Car;
import com.dao.homework.model.Driver;
import java.util.List;

public interface CarService extends GenericService<Car, Long> {
    List<Car> getAllByDriver(Long driverId);

    void addDriverToCar(Driver driver, Car car);

    void removeDriverFromCar(Driver driver, Car car);
}
