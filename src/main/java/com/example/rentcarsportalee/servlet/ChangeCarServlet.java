package com.example.rentcarsportalee.servlet;

import com.example.rentcarsportalee.model.Car;
import com.example.rentcarsportalee.model.CarStatus;
import com.example.rentcarsportalee.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (value = "/changeCar")
public class ChangeCarServlet extends HttpServlet {
    private CarService carService = new CarService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("car",carService.getCarById(id));
        req.getRequestDispatcher("/WEB-INF/changeCar.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Car car = carService.getCarById(id);

        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        int year =Integer.parseInt(req.getParameter("year"));
        double dailyRate = Double.parseDouble(req.getParameter("daily_Rate"));
        CarStatus carStatus = CarStatus.valueOf(req.getParameter("status").toUpperCase());

        car.setBrand(brand);
        car.setModel(model);
        car.setYear(year);
        car.setDailyRate(dailyRate);
        car.setCarStatus(carStatus);
        carService.changeCar(car);

        resp.sendRedirect("/cars");


    }
}

