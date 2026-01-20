package com.example.rentcarsportalee.service;

import com.example.rentcarsportalee.db.DBConnectionProvider;
import com.example.rentcarsportalee.model.Car;
import com.example.rentcarsportalee.model.CarStatus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarService {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();


    public void addCar(Car car){

        String sql = "INSERT INTO car(brand,model,year,daily_Rate,status) VALUES (?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getYear());
            preparedStatement.setDouble(4, car.getDailyRate());
            preparedStatement.setString(5, car.getCarStatus().name());
            preparedStatement.executeUpdate();
            ResultSet resultkeys = preparedStatement.getGeneratedKeys();
            if (resultkeys.next()){
                car.setId(resultkeys.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Car getCarById(int id){
        String sql = "SELECT * FROM car WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Car car = new Car();
               car.setId(resultSet.getInt("id"));
               car.setBrand(resultSet.getString("brand"));
               car.setModel(resultSet.getString("model"));
               car.setYear(resultSet.getInt("year"));
               car.setDailyRate(resultSet.getDouble("daily_Rate"));
               car.setCarStatus(CarStatus.valueOf(resultSet.getString("status")));
               return car;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void changeCar(Car car){

        String sql = "UPDATE car SET brand = ?, model = ?, year=?, daily_Rate = ?, status = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getYear());
            preparedStatement.setDouble(4, car.getDailyRate());
            preparedStatement.setString(5, car.getCarStatus().name());
            preparedStatement.setInt(6,car.getId());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteCar(int id){
        String sql = "DELETE FROM car WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Car> getAllCars(){
        String sql = "SELECT * FROM car";
        List<Car> cars = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setYear(resultSet.getInt("year"));
                car.setDailyRate(resultSet.getDouble("daily_Rate"));
                car.setCarStatus(CarStatus.valueOf(resultSet.getString("status").toUpperCase()));
                cars.add(car);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return cars;
    }

    public List<Car> getAvailableCars(){
        String sql = "SELECT * FROM car WHERE status = 'AVAILABLE'";
        List<Car> cars = new ArrayList<>();
        try(PreparedStatement pr = connection.prepareStatement(sql)){
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setYear(resultSet.getInt("year"));
                car.setDailyRate(resultSet.getDouble("daily_Rate"));
                car.setCarStatus(CarStatus.valueOf(resultSet.getString("status").toUpperCase()));
                cars.add(car);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cars;
    }
}
