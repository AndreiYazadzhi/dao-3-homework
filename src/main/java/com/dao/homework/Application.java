package com.dao.homework;

import com.dao.homework.lib.Injector;
import com.dao.homework.model.Car;
import com.dao.homework.model.Driver;
import com.dao.homework.model.Manufacturer;
import com.dao.homework.service.CarService;
import com.dao.homework.service.DriverService;
import com.dao.homework.service.ManufacturerService;

public class Application {
    private static final Injector injector = Injector.getInstance("com.dao.homework");

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService)
                injector.getInstance(ManufacturerService.class);
        Manufacturer bmwManufacturer = new Manufacturer("BMW", "Germany");
        Manufacturer astonManufacturer = new Manufacturer("Aston Martin", "England");
        manufacturerService.create(bmwManufacturer);
        manufacturerService.create(astonManufacturer);
        System.out.println(manufacturerService.getAll());
        Manufacturer updatedManufacturer = manufacturerService.get(1L);
        updatedManufacturer.setName("Gold");
        updatedManufacturer.setCountry("Ukraine");
        manufacturerService.update(updatedManufacturer);
        System.out.println(manufacturerService.getAll());
        manufacturerService.deleteById(0L);
        System.out.println(manufacturerService.get(1L));

        DriverService driverService = (DriverService) injector.getInstance(DriverService.class);
        Driver driver1 = new Driver("Nikita", "12345");
        Driver driver2 = new Driver("Stas", "12346");
        Driver driver3 = new Driver("Gena", "12347");
        Driver driver4 = new Driver("Turbo", "12348");
        driverService.create(driver1);
        driverService.create(driver2);
        driverService.create(driver3);
        driverService.create(driver4);
        driverService.delete(3L);
        System.out.println(driverService.getAll());
        Driver updatedDriver = driverService.get(0L);
        updatedDriver.setName("Dysha");
        driverService.update(updatedDriver);
        System.out.println(driverService.getAll());

        CarService carService = (CarService) injector.getInstance(CarService.class);
        Car car1 = new Car("TT", manufacturerService.get(1L));
        Car car2 = new Car("RSQ8", manufacturerService.get(1L));
        Car car3 = new Car("Model X", manufacturerService.get(1L));
        Car car4 = new Car("Model Y", manufacturerService.get(1L));
        carService.create(car1);
        carService.create(car2);
        carService.create(car3);
        carService.create(car4);
        carService.addDriverToCar(driverService.get(0L), carService.get(1L));
        carService.addDriverToCar(driverService.get(1L), carService.get(2L));
        carService.addDriverToCar(driverService.get(2L), carService.get(1L));
        System.out.println(carService.getAll());
        Car updatedCar = carService.get(1L);
        updatedCar.setModel("Nimbus 2000");
        carService.update(updatedCar);
        carService.delete(0L);
        System.out.println(carService.getAll());
        carService.removeDriverFromCar(driverService.get(0L), carService.get(1L));
        System.out.println(carService.getAllByDriver(2L));
        System.out.println(carService.getAll());
    }
}
