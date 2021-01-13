package com.dao.homework.dao;

import com.dao.homework.model.Car;
import java.util.List;
import java.util.Optional;

public interface CarDao {
    Car create(Car car);

    Optional<Car> getById(Long carId);

    Car update(Car car);

    boolean delete(Long carId);

    boolean delete(Car car);

    List<Car> getAll();

    List<Car> getAllByDriver(Long driverId);
}
