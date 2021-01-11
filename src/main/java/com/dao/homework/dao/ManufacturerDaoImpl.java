package com.dao.homework.dao;

import com.dao.homework.db.Storage;
import com.dao.homework.lib.Dao;
import com.dao.homework.model.Manufacturer;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Dao
public class ManufacturerDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        Storage.addManufacturer(manufacturer);
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> getById(Long manufacturerId) {
        return getAllManufacturers().stream()
                .filter(m -> Objects.equals(m.getId(), manufacturerId))
                .findFirst();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        if (!getAllManufacturers().contains(manufacturer)) {
            throw new RuntimeException("Can`t find this element!");
        }
        manufacturer.setId(manufacturer.getId());
        manufacturer.setCountry(manufacturer.getCountry());
        return manufacturer;
    }

    @Override
    public boolean delete(Manufacturer manufacturer) {
        if (!getAllManufacturers().contains(manufacturer)) {
            throw new RuntimeException("Can`t find this element!");
        }
        Storage.manufacturers.remove(manufacturer.getId().intValue());
        return true;
    }

    @Override
    public boolean deleteById(Long manufacturerId) {
        if (!getAllManufacturers().contains(getById(manufacturerId).get())) {
            throw new RuntimeException("Can`t find this element!");
        }
        Storage.manufacturers.remove(manufacturerId);
        return true;
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return Storage.manufacturers;
    }
}
