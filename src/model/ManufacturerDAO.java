package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDAO {
    private Connection connection;
    public ManufacturerDAO(Connection connection){
        this.connection = connection;
    }
    public List<Manufacturer> getManufacturersFromDatabase(Connection connection) {
        List<Manufacturer> manufacturers = new ArrayList<>();

        try {
            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the query to retrieve manufacturers
            ResultSet resultSet = statement.executeQuery("SELECT * FROM manufacturer");

            // Iterate over the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("manufacturer_id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");

                // Create a Manufacturer object and add it to the list
                Manufacturer manufacturer = new Manufacturer(id, name, country, phone, email);
                manufacturers.add(manufacturer);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving manufacturers from the database");
            e.printStackTrace();
        }

        return manufacturers;
    }
}


