package com.dao.homework.controllers.manufacturer;

import com.dao.homework.lib.Injector;
import com.dao.homework.model.Manufacturer;
import com.dao.homework.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateManufacturerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.dao.homework");
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/manufacturer/createManufacturer.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String manufacturerName = request.getParameter("name");
        String manufacturerCountry = request.getParameter("country");
        manufacturerService.create(new Manufacturer(manufacturerName, manufacturerCountry));
        response.sendRedirect("/manufacturers");
    }
}
