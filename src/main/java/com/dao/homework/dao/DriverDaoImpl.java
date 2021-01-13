package com.dao.homework.dao;

import com.dao.homework.db.Storage;
import com.dao.homework.lib.Dao;
import com.dao.homework.model.Driver;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class DriverDaoImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        Storage.addDriver(driver);
        return driver;
    }

    @Override
    public Optional<Driver> getById(Long driverId) {
        return getAll().stream()
                .filter(m -> Objects.equals(m.getId(), driverId))
                .findFirst();
    }

    @Override
    public Driver update(Driver driver) {
        IntStream.range(0, Storage.drivers.size())
                .filter(i -> Storage.drivers.get(i).getId().equals(driver.getId()))
                .findFirst()
                .ifPresent(i -> Storage.drivers.set(i, driver));
        return driver;
    }

    @Override
    public boolean delete(Long driverId) {
        return getAll().removeIf(m -> m.getId().equals(driverId));
    }

    @Override
    public boolean delete(Driver driver) {
        return Storage.drivers.remove(driver);
    }

    @Override
    public List<Driver> getAll() {
        return Storage.drivers;
    }
}
