package com.dao.homework.dao;

import com.dao.homework.db.Storage;
import com.dao.homework.lib.Dao;
import com.dao.homework.model.Manufacturer;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

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
        IntStream.range(0, Storage.manufacturers.size())
                .filter(i -> Storage.manufacturers.get(i).getId().equals(manufacturer.getId()))
                .findFirst()
                .ifPresent(i -> Storage.manufacturers.set(i, manufacturer));
        return manufacturer;
    }

    @Override
    public boolean delete(Long manufacturerId) {
        return getAllManufacturers().removeIf(m -> m.getId().equals(manufacturerId));
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return Storage.manufacturers;
    }
}
