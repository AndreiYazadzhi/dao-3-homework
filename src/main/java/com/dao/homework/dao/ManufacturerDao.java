package com.dao.homework.dao;

import com.dao.homework.model.Manufacturer;
import java.util.List;
import java.util.Optional;

public interface ManufacturerDao {
    Manufacturer create(Manufacturer manufacturer);

    Optional<Manufacturer> getById(Long manufacturerId);

    Manufacturer update(Manufacturer manufacturer);

    boolean delete(Long manufacturerId);

    List<Manufacturer> getAllManufacturers();
}
