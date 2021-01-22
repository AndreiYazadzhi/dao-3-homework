package com.dao.homework.controllers.cars;

import com.dao.homework.lib.Injector;
import com.dao.homework.model.Car;
import com.dao.homework.service.CarService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCarsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.dao.homework");
    private final CarService carService = (CarService) injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Car> allCars = carService.getAll();
        request.setAttribute("cars", allCars);
        request.getRequestDispatcher("/WEB-INF/views/cars/cars.jsp").forward(request, response);
    }
}
