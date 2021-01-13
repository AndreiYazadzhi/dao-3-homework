package com.dao.homework.db;

import com.dao.homework.model.Car;
import com.dao.homework.model.Driver;
import com.dao.homework.model.Manufacturer;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Manufacturer> manufacturers = new ArrayList<>();
    public static final List<Car> cars = new ArrayList<>();
    public static final List<Driver> drivers = new ArrayList<>();
    private static long manufacturerId = 0;
    private static long carId = 0;
    private static long driverId = 0;

    public static void addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(manufacturerId++);
        manufacturers.add(manufacturer);
    }

    public static void addCar(Car car) {
        car.setId(carId++);
        cars.add(car);
    }

    public static void addDriver(Driver driver) {
        driver.setId(driverId++);
        drivers.add(driver);
    }
}
