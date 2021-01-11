package com.dao.homework;

import com.dao.homework.lib.Injector;
import com.dao.homework.model.Manufacturer;
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
        System.out.println(manufacturerService.getAllManufacturers());
        Manufacturer updatedManufacturer = manufacturerService.get(1L);
        updatedManufacturer.setName("Gold");
        updatedManufacturer.setCountry("Ukraine");
        manufacturerService.update(updatedManufacturer);
        System.out.println(manufacturerService.getAllManufacturers());
        manufacturerService.deleteById(0L);
        System.out.println(manufacturerService.get(0L));
    }
}
