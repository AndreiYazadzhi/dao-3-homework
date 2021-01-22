package com.dao.homework.controllers.drivers;

import com.dao.homework.lib.Injector;
import com.dao.homework.model.Driver;
import com.dao.homework.service.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.dao.homework");
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/drivers/createDriver.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String driverName = request.getParameter("name");
        String driverLicense = request.getParameter("license_number");
        driverService.create(new Driver(driverName, driverLicense));
        response.sendRedirect("/drivers");
    }
}
