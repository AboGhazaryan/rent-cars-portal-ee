package com.example.rentcarsportalee.servlet;


import com.example.rentcarsportalee.service.RentalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = ("/deleteRental"))
public class DeleteRentalServlet extends HttpServlet {
    private RentalService rentalService = new RentalService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        rentalService.deleteRental(id);
        resp.sendRedirect("/rentals");

    }
}
