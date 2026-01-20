package com.example.rentcarsportalee.servlet;

import com.example.rentcarsportalee.model.Car;
import com.example.rentcarsportalee.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet (value = "/cars")
public class CarsServlet extends HttpServlet {

        private CarService carService = new CarService();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<Car> allCars = carService.getAllCars();

            req.setAttribute("cars", allCars);
            req.getRequestDispatcher("/WEB-INF/cars.jsp").forward(req,resp);
        }
}
