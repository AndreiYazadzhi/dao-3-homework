package com.dao.homework;

import com.dao.homework.lib.Injector;
import com.dao.homework.service.CarService;

public class Application {
    private static final Injector injector = Injector.getInstance("com.dao.homework");

    public static void main(String[] args) {
        CarService carService = (CarService) injector.getInstance(CarService.class);
        System.out.println(carService.getAllByDriver(1L));
    }
}
