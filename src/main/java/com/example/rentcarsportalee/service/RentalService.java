package com.example.rentcarsportalee.service;

import com.example.rentcarsportalee.db.DBConnectionProvider;
import com.example.rentcarsportalee.model.Rental;
import com.example.rentcarsportalee.model.RentalStatus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RentalService {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private CarService carService = new CarService();
    private CustomerService customerService = new CustomerService();

    public void addRental(Rental rental){
        calculateTotalCost(rental);
        String sql = "INSERT INTO rental (car_id,customer_id,start_date,end_date,total_cost,status) VALUES (?,?,?,?,?,?)";

        try(PreparedStatement pr = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pr.setInt(1,rental.getCar().getId());
            pr.setInt(2,rental.getCustomer().getId());
            pr.setTimestamp(3, new java.sql.Timestamp(rental.getStartDate().getTime()));
            pr.setTimestamp(4, new java.sql.Timestamp(rental.getEndDate().getTime()));
            pr.setDouble(5,rental.getTotalCost());
            pr.setString(6,rental.getRentalStatus().name());
            pr.executeUpdate();
            ResultSet rs = pr.getGeneratedKeys();
            if(rs.next()){
                rental.setId(rs.getInt(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteRental(int id){
        String sql = "DELETE FROM rental WHERE id = ?";
        try(PreparedStatement pr = connection.prepareStatement(sql)){
            pr.setInt(1,id);
            pr.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Rental getRentalById(int id){
        String sql = "SELECT * FROM rental WHERE id = ?";
        try(PreparedStatement pr = connection.prepareStatement(sql)){
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                Rental rental = new Rental();
                rental.setId(rs.getInt("id"));
                rental.setCar(carService.getCarById(rs.getInt("car_id")));
                rental.setCustomer(customerService.getCustomerById(rs.getInt("customer_id")));
                rental.setStartDate(rs.getTimestamp("start_date"));
                rental.setEndDate(rs.getTimestamp("end_date"));
                rental.setTotalCost(rs.getDouble("total_cost"));
                rental.setRentalStatus(RentalStatus.valueOf(rs.getString("status")));
                return rental;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void changeRental(Rental rental){
        calculateTotalCost(rental);
        String sql = "UPDATE rental SET car_id = ?,customer_id = ?,start_date = ?, end_date = ?,total_cost =?,status = ? WHERE id = ?";
        try(PreparedStatement pr = connection.prepareStatement(sql)){
            pr.setInt(1,rental.getCar().getId());
            pr.setInt(2,rental.getCustomer().getId());
            pr.setTimestamp(3, new java.sql.Timestamp(rental.getStartDate().getTime()));
            pr.setTimestamp(4, new java.sql.Timestamp(rental.getEndDate().getTime()));
            pr.setDouble(5,rental.getTotalCost());
            pr.setString(6,rental.getRentalStatus().name());
            pr.setInt(7,rental.getId());
            pr.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Rental> getAllRentals(){
        String sql = "SELECT * FROM rental";
        List<Rental> rentals = new ArrayList<>();
        try(PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                Rental rental = new Rental();
                rental.setId(rs.getInt("id"));
                rental.setCar(carService.getCarById(rs.getInt("car_id")));
                rental.setCustomer(customerService.getCustomerById(rs.getInt("customer_id")));
                rental.setStartDate(rs.getTimestamp("start_date"));
                rental.setEndDate(rs.getTimestamp("end_date"));
                rental.setTotalCost(rs.getDouble("total_cost"));
                rental.setRentalStatus(RentalStatus.valueOf(rs.getString("status")));
                rentals.add(rental);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rentals;
    }

    private static void calculateTotalCost(Rental rental) {
        if(rental.getCar() != null && rental.getStartDate() != null && rental.getEndDate() != null){
            long  diffInMillies= rental.getEndDate().getTime() - rental.getStartDate().getTime();
            long days = diffInMillies/(1000*60*60*24);
            if(days<=0)days = 1;
            double totalCost = days * rental.getCar().getDailyRate();
            rental.setTotalCost(totalCost);
        }
    }



}
