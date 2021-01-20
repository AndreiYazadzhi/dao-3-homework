package com.dao.homework.controllers;

import com.dao.homework.lib.Injector;
import com.dao.homework.service.CarService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.dao.homework");
    private final CarService carService = (CarService) injector
            .getInstance(CarService.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long carId = Long.valueOf(request.getParameter("carId"));
        carService.delete(carId);
        response.sendRedirect("/cars");
    }
}
