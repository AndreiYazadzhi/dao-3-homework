package com.dao.homework.controllers;

import com.dao.homework.lib.Inject;
import com.dao.homework.lib.Injector;
import com.dao.homework.model.Driver;
import com.dao.homework.service.DriverService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetDriversController extends HttpServlet {
    @Inject
    private static final Injector injector = Injector.getInstance("com.dao.homework");
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Driver> allDrivers = driverService.getAll();
        request.setAttribute("drivers", allDrivers);
        request.getRequestDispatcher("/WEB-INF/views/drivers.jsp").forward(request, response);
    }
}
