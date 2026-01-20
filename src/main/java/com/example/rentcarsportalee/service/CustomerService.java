package com.example.rentcarsportalee.service;

import com.example.rentcarsportalee.db.DBConnectionProvider;
import com.example.rentcarsportalee.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();


    public void addCustomer(Customer customer){

        String sql = "INSERT INTO customer(name,surname,license_number,phone,email) VALUES (?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getSurname());
            preparedStatement.setString(3,customer.getLicenseNumber());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.executeUpdate();
            ResultSet resultkeys = preparedStatement.getGeneratedKeys();
            if (resultkeys.next()){
                customer.setId(resultkeys.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Customer getCustomerById(int id){
        String sql = "SELECT * FROM customer WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setSurname(resultSet.getString("surname"));
                customer.setLicenseNumber(resultSet.getString("license_number"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                return   customer;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void changeCustomer(Customer customer){
        String sql = "UPDATE customer SET name = ?, surname = ?, license_number=?, phone = ?, email = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getSurname());
            preparedStatement.setString(3,customer.getLicenseNumber());
            preparedStatement.setString(4,customer.getPhone());
            preparedStatement.setString(5,customer.getEmail());
            preparedStatement.setInt(6,customer.getId());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int id){
        String sql = "DELETE FROM customer WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomer(){
        String sql = "SELECT * FROM customer";
        List<Customer> customers = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setSurname(resultSet.getString("surname"));
                customer.setLicenseNumber(resultSet.getString("license_number"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customers.add(customer);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return customers;
    }

}
