package com.dao.homework.dao;

import com.dao.homework.model.Driver;
import java.util.List;
import java.util.Optional;

public interface DriverDao {
    Driver create(Driver driver);

    Optional<Driver> getById(Long driverId);

    Driver update(Driver driver);

    boolean delete(Long driverId);

    boolean delete(Driver driver);

    List<Driver> getAll();
}
