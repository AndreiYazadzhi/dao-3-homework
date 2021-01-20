package com.dao.homework.controllers;

import com.dao.homework.lib.Injector;
import com.dao.homework.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteManufacturerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.dao.homework");
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long manufacturerId = Long.valueOf(request.getParameter("manufacturerId"));
        manufacturerService.delete(manufacturerId);
        response.sendRedirect("/manufacturers");
    }
}