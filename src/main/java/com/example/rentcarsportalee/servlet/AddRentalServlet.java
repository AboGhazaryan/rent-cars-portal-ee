package com.example.rentcarsportalee.servlet;

import com.example.rentcarsportalee.model.Rental;
import com.example.rentcarsportalee.model.RentalStatus;
import com.example.rentcarsportalee.service.CarService;
import com.example.rentcarsportalee.service.CustomerService;
import com.example.rentcarsportalee.service.RentalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


@WebServlet(value = "/addRental")
public class AddRentalServlet extends HttpServlet {
    private RentalService rentalService = new RentalService();
    private CarService carService = new CarService();
    private CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars",carService.getAvailableCars());
        req.setAttribute("customers",customerService.getAllCustomer());
        req.getRequestDispatcher("/WEB-INF/addRental.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int carId = Integer.parseInt(req.getParameter("car_id"));
        int customerId = Integer.parseInt(req.getParameter("customer_id"));
        Date startDate = Date.valueOf(req.getParameter("start_date"));
        Date endDate = Date.valueOf(req.getParameter("end_date"));
        RentalStatus rentalStatus = RentalStatus.valueOf(req.getParameter("status").toUpperCase());

        Rental rental  = new Rental();
        rental.setCar(carService.getCarById(carId));
        rental.setCustomer(customerService.getCustomerById(customerId));
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        rental.setRentalStatus(rentalStatus);
        rentalService.addRental(rental);

        resp.sendRedirect("/rentals");
    }
}
