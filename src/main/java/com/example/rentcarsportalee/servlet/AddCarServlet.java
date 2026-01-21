package com.example.rentcarsportalee.servlet;

import com.example.rentcarsportalee.model.Car;
import com.example.rentcarsportalee.model.CarStatus;
import com.example.rentcarsportalee.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

@WebServlet(value="/addCar")
@MultipartConfig(maxFileSize = 10*1024*1024, maxRequestSize = 50 * 1024*1024, fileSizeThreshold =1024*1024)
public class AddCarServlet extends HttpServlet {
    private CarService carService = new CarService();
    private static final String IMAGE_UPLOAD_PATH = "C:\\Users\\Admin\\IdeaProjects\\rent-cars-portal-ee\\upload-images\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addCar.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        int year =Integer.parseInt(req.getParameter("year"));
        double dailyRate = Double.parseDouble(req.getParameter("daily_Rate"));
        CarStatus carStatus = CarStatus.valueOf(req.getParameter("status").toUpperCase());

        Part image = req.getPart("image");
        String pictureName = System.currentTimeMillis()+"_" + image.getSubmittedFileName();
        image.write(IMAGE_UPLOAD_PATH + pictureName);
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setYear(year);
        car.setDailyRate(dailyRate);
        car.setCarStatus(carStatus);
        car.setPictureName(pictureName);
        carService.addCar(car);

        resp.sendRedirect("/cars");


    }
}
