package com.dao.homework.service;

import com.dao.homework.model.Manufacturer;
import java.util.List;

public interface ManufacturerService {
    Manufacturer create(Manufacturer manufacturer);

    Manufacturer get(Long id);

    List<Manufacturer> getAllManufacturers();

    Manufacturer update(Manufacturer manufacturer);

    boolean deleteById(Long id);
}
