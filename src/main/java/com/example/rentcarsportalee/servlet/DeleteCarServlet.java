package com.example.rentcarsportalee.servlet;

import com.example.rentcarsportalee.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = ("/deleteCar"))
public class DeleteCarServlet extends HttpServlet {
    private CarService carService = new CarService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        carService.deleteCar(id);
        resp.sendRedirect("/cars");

    }
}
