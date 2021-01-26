package com.dao.homework.controllers.driver;

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
        request.getRequestDispatcher("/WEB-INF/views/driver/createDriver.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String driverName = request.getParameter("name");
        String driverLicense = request.getParameter("license_number");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Driver driver = new Driver(driverName, driverLicense, login);
        driver.setPassword(password);
        driverService.create(driver);
        response.sendRedirect("/drivers");
    }
}
