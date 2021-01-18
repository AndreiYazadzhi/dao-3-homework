package com.dao.homework;

import com.dao.homework.lib.Injector;
import com.dao.homework.model.Manufacturer;
import com.dao.homework.service.CarService;
import com.dao.homework.service.ManufacturerService;

public class Application {
    private static final Injector injector = Injector.getInstance("com.dao.homework");

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService)
                injector.getInstance(ManufacturerService.class);
        Manufacturer manufacturer = new Manufacturer("VAZ", "Russia");
        CarService carService = (CarService) injector.getInstance(CarService.class);
        System.out.println(carService.getAllByDriver(1L));
        System.out.println(carService.update(carService.get(1L)));
        System.out.println(carService.delete(1L));
    }
}
