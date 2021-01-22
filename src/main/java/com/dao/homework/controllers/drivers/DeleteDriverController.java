package com.dao.homework.controllers.drivers;

import com.dao.homework.lib.Injector;
import com.dao.homework.service.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.dao.homework");
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long driverId = Long.valueOf(request.getParameter("driver_id"));
        driverService.delete(driverId);
        response.sendRedirect("/drivers");
    }
}
