package com.dao.homework.dao;

import com.dao.homework.db.Storage;
import com.dao.homework.lib.Dao;
import com.dao.homework.model.Car;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class CarDaoImpl implements CarDao {
    @Override
    public Car create(Car car) {
        Storage.addCar(car);
        return car;
    }

    @Override
    public Optional<Car> getById(Long carId) {
        return getAll().stream()
                .filter(m -> Objects.equals(m.getId(), carId))
                .findFirst();
    }

    @Override
    public Car update(Car car) {
        IntStream.range(0, Storage.cars.size())
                .filter(i -> Storage.cars.get(i).getId().equals(car.getId()))
                .findFirst()
                .ifPresent(i -> Storage.cars.set(i, car));
        return car;
    }

    @Override
    public boolean delete(Long carId) {
        return getAll().removeIf(m -> m.getId().equals(carId));
    }

    @Override
    public boolean delete(Car car) {
        return Storage.cars.remove(car);
    }

    @Override
    public List<Car> getAll() {
        return Storage.cars;
    }
}
