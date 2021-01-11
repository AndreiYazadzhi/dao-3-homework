package com.dao.homework.service;

import com.dao.homework.dao.ManufacturerDao;
import com.dao.homework.lib.Inject;
import com.dao.homework.lib.Service;
import com.dao.homework.model.Manufacturer;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Inject
    private ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return manufacturerDao.create(manufacturer);
    }

    @Override
    public Manufacturer get(Long id) {
        return manufacturerDao.getById(id).get();
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerDao.getAllManufacturers();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return manufacturerDao.update(manufacturer);
    }

    @Override
    public boolean deleteById(Long id) {
        return manufacturerDao.deleteById(id);
    }
}
