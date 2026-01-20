package com.example.rentcarsportalee.servlet;

import com.example.rentcarsportalee.model.Rental;
import com.example.rentcarsportalee.service.RentalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet (value = "/rentals")
public class RentalsServlet extends HttpServlet {

        private RentalService rentalService = new RentalService();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<Rental> allRentals = rentalService.getAllRentals();

            req.setAttribute("rentals", allRentals);
            req.getRequestDispatcher("/WEB-INF/rentals.jsp").forward(req,resp);
        }
}
