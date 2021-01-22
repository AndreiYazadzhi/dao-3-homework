package com.dao.homework.controllers.cars;

import com.dao.homework.lib.Injector;
import com.dao.homework.model.Car;
import com.dao.homework.model.Manufacturer;
import com.dao.homework.service.CarService;
import com.dao.homework.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.dao.homework");
    private final CarService carService = (CarService) injector.getInstance(CarService.class);
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/cars/createCar.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String carModel = request.getParameter("model");
        Long manufacturerId = Long.valueOf(request.getParameter("manufacturer_id"));
        Manufacturer manufacturer = manufacturerService.get(manufacturerId);
        carService.create(new Car(carModel, manufacturer));
        response.sendRedirect("/cars");
    }
}
