package com.example.rentcarsportalee.servlet;

import com.example.rentcarsportalee.model.Car;
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
import java.util.List;



@WebServlet (value = "/changeRental")
public class ChangeRentalServlet extends HttpServlet {
    private RentalService rentalService = new RentalService();
    private CarService carService = new CarService();
    private CustomerService customerService = new CustomerService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Rental rental = rentalService.getRentalById(id);
        List<Car> cars = carService.getAvailableCars();
        boolean exists = false;
        for(Car car : cars){
            if(car.getId() == rental.getCar().getId()){
                exists = true;
                break;
            }
        }
        if(!exists){
            cars.add(rental.getCar());
        }
        req.setAttribute("cars",cars);
        req.setAttribute("customers",customerService.getAllCustomer());
        req.setAttribute("rental",rentalService.getRentalById(id));
        req.getRequestDispatcher("/WEB-INF/changeRental.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Rental rental = rentalService.getRentalById(id);

        int carId = Integer.parseInt(req.getParameter("car_id"));
        int customerId = Integer.parseInt(req.getParameter("customer_id"));
        Date startDate = Date.valueOf(req.getParameter("start_date"));
        Date endDate = Date.valueOf(req.getParameter("end_date"));
        RentalStatus rentalStatus = RentalStatus.valueOf(req.getParameter("status").toUpperCase());
        rental.setCar(carService.getCarById(carId));
        rental.setCustomer(customerService.getCustomerById(customerId));
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        rental.setRentalStatus(rentalStatus);
        rentalService.changeRental(rental);

        resp.sendRedirect("/rentals");


    }

}

