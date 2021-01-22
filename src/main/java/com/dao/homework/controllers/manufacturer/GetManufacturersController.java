package com.dao.homework.controllers.manufacturer;

import com.dao.homework.lib.Injector;
import com.dao.homework.model.Manufacturer;
import com.dao.homework.service.ManufacturerService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetManufacturersController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.dao.homework");
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Manufacturer> allManufacturers = manufacturerService.getAll();
        request.setAttribute("manufacturers", allManufacturers);
        request.getRequestDispatcher("/WEB-INF/views/manufacturer/manufacturers.jsp")
                .forward(request, response);
    }
}
