package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.*;
public class DatabaseConnector
{
  private Connection connection;

  public void connect(String host, int portNo, String userName, String password)
  {
    // Establishing a PostgreSQL database connection
    String databaseUrl = "jdbc:postgresql://" + host + ":" + portNo + "/" + userName;

    try
    {
      connection = DriverManager.getConnection(databaseUrl, userName, password);
      //System.out.println("Connection established to: " + databaseUrl);
    }
    catch (Exception exception)
    {
      System.out.println("Connection failed");
      exception.printStackTrace();
    }
  }
  public void UpdateManufacturersName(String newName, int id){
    String sql = "UPDATE solarpanels.manufacturer SET name = ? WHERE manufacturer_id = ?;";

    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1,newName);
      statement.setInt(2, id);
      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("model.Manufacturer name updated successfully.");
      } else {
        System.out.println("No manufacturer found with the specified ID.");
      }
      statement.close();
//      connection.close();
    } catch (SQLException e) {
      System.out.println("Error trying to update model.Manufacturer table");
      e.printStackTrace();
    }

  }

  public ObservableList<Manufacturer> retrieveManufacturer()
  {
    ObservableList<Manufacturer> result = FXCollections.observableArrayList();

    String sql = "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer GROUP BY manufacturer_ID;";

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql); // use the executeQuery() function when a result is expected

      while (resultSet.next()) { // Goes to the next row of data if available
        Manufacturer manufacturer = new Manufacturer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
        result.add(manufacturer);
      }

    } catch (SQLException e) {
      System.out.println("Error trying to generate table of manufacturers");
      e.printStackTrace();
    }


    return result;
  }
  public ObservableList<Panels> retrievePanels()
  {
    ObservableList<Panels> result = FXCollections.observableArrayList();

    String sql = "select panel_id, manufacturer.name, type, row, \"column\", installation_date FROM solarpanels.Panel JOIN solarpanels.Manufacturer  ON solarpanels.manufacturer.manufacturer_ID = solarpanels.Panel.manufacturer_ID;";

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql); // use the executeQuery() function when a result is expected

      while (resultSet.next()) { // Goes to the next row of data if available
        Panels panels = new Panels(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getDate(6));
        result.add(panels);
      }

    } catch (SQLException e) {
      System.out.println("Error trying to generate table of panels");
      e.printStackTrace();
    }


    return result;
  }
  public ObservableList<PVPanels> retrievePVPanels()
  {
    ObservableList<PVPanels> result = FXCollections.observableArrayList();

    String sql = "select timestamp::date as date, timestamp::time as time, measure_ID, panel_ID, voltage, current, solar_flux, power_out, efficiency FROM solarpanels.measure_pv;";

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql); // use the executeQuery() function when a result is expected

      while (resultSet.next()) { // Goes to the next row of data if available
        PVPanels pvpanels = new PVPanels(resultSet.getDate(1),resultSet.getTime(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getFloat(5), resultSet.getFloat(6), resultSet.getInt(7), resultSet.getFloat(8), resultSet.getFloat(9));
        result.add(pvpanels);
      }

    } catch (SQLException e) {
      System.out.println("Error trying to generate table of pvpanels");
      e.printStackTrace();
    }


    return result;
  }
  public ObservableList<THPanels> retrieveTHPanels()
  {
    ObservableList<THPanels> result = FXCollections.observableArrayList();

    String sql = "select timestamp::date as date, timestamp::time as time, measure_ID, panel_ID, a_temperature, water_in_temp, water_out_temp, efficiency FROM solarpanels.measure_th;";

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql); // use the executeQuery() function when a result is expected

      while (resultSet.next()) { // Goes to the next row of data if available
        THPanels thpanels = new THPanels(resultSet.getDate(1),resultSet.getTime(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getFloat(5), resultSet.getFloat(6), resultSet.getFloat(7), resultSet.getFloat(8));
        result.add(thpanels);
      }

    } catch (SQLException e) {
      System.out.println("Error trying to generate table of thpanels");
      e.printStackTrace();
    }


    return result;
  }
  public void addManufacturer(Manufacturer manufacturer)
  {
    String sql = "INSERT INTO solarpanels.Manufacturer(name, country, phone, email) VALUES(?, ?, ?, ?);";

    try
    {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, manufacturer.getName());
      statement.setString(2, manufacturer.getCountry());
      statement.setString(3, manufacturer.getPhone());
      statement.setString(4, manufacturer.getEmail());
      statement.executeUpdate();
      statement.close();
//      connection.close();
    }
    catch (SQLException e)
    {
      System.out.println("Error trying to update model.Manufacturer table");
      e.printStackTrace();
    }
  }
  public ObservableList<ManufacturerHistory> retrieveManufacturerHistory()
  {
    ObservableList<ManufacturerHistory> result = FXCollections.observableArrayList();

    String sql = "select history_id, timestamp::date as date, timestamp::time as time, manufacturer_ID, name, country, phone, email FROM solarpanels.manufacturer_history GROUP BY history_id;";

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql); // use the executeQuery() function when a result is expected

      while (resultSet.next()) { // Goes to the next row of data if available
        ManufacturerHistory manufacturerHistory = new ManufacturerHistory(resultSet.getInt(1),resultSet.getDate(2), resultSet.getTime(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
        result.add(manufacturerHistory);
      }

    } catch (SQLException e) {
      System.out.println("Error trying to generate table of thpanels");
      e.printStackTrace();
    }


    return result;
  }
  public void UpdateManufacturersCountry(String newCountry, int id){
    String sql = "UPDATE solarpanels.manufacturer SET country = ? WHERE manufacturer_id = ?;";

    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1,newCountry);
      statement.setInt(2, id);
      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("model.Manufacturer name updated successfully.");
      } else {
        System.out.println("No manufacturer found with the specified ID.");
      }
      statement.close();
//      connection.close();
    } catch (SQLException e) {
      System.out.println("Error trying to update model.Manufacturer table");
      e.printStackTrace();
    }

  }
  public void UpdateManufacturersPhone(String newPhone, int id){
    String sql = "UPDATE solarpanels.manufacturer SET phone = ? WHERE manufacturer_id = ?;";

    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1,newPhone);
      statement.setInt(2, id);
      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("model.Manufacturer name updated successfully.");
      } else {
        System.out.println("No manufacturer found with the specified ID.");
      }
      statement.close();
//      connection.close();
    } catch (SQLException e) {
      System.out.println("Error trying to update model.Manufacturer table");
      e.printStackTrace();
    }

  }
  public void UpdateManufacturersEmail(String newEmail, int id){
    String sql = "UPDATE solarpanels.manufacturer SET email = ? WHERE manufacturer_id = ?;";

    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1,newEmail);
      statement.setInt(2, id);
      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("model.Manufacturer name updated successfully.");
      } else {
        System.out.println("No manufacturer found with the specified ID.");
      }
      statement.close();
//      connection.close();
    } catch (SQLException e) {
      System.out.println("Error trying to update model.Manufacturer table");
      e.printStackTrace();
    }

  }
  public void deleteManufacturer(int id) {
    String sql = "DELETE FROM solarpanels.Manufacturer WHERE manufacturer_id = " + id + ";";

    try {
      Statement statement = connection.createStatement();
      statement.execute(sql);
    } catch (SQLException e) {
      System.out.println("Error trying to delete a manufacturer from Manufacturer table");
      e.printStackTrace();
    }
  }
  public ObservableList<Manufacturer> searchByID(int id) {
    ObservableList<Manufacturer> result = FXCollections.observableArrayList();
    String sql = "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer WHERE manufacturer_id = " + id + ";";

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql); // use the executeQuery() function when a result is expected

      while (resultSet.next()) { // Goes to the next row of data if available
        Manufacturer manufacturer = new Manufacturer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
        result.add(manufacturer);
      }

    } catch (SQLException e) {
      System.out.println("Error trying to search by id");
      e.printStackTrace();
    }
    return result;
  }
  public ObservableList<Manufacturer> searchByName(String name) {
    ObservableList<Manufacturer> result = FXCollections.observableArrayList();
    String sql = "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer WHERE name = '" + name + "';";

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql); // use the executeQuery() function when a result is expected

      while (resultSet.next()) { // Goes to the next row of data if available
        Manufacturer manufacturer = new Manufacturer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
        result.add(manufacturer);
      }

    } catch (SQLException e) {
      System.out.println("Error trying to search by name");
      e.printStackTrace();
    }
    return result;
  }
  public ObservableList<Manufacturer> searchByCountry(String country) {
    ObservableList<Manufacturer> result = FXCollections.observableArrayList();
    String sql = "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer WHERE country = '" + country + "';";

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql); // use the executeQuery() function when a result is expected

      while (resultSet.next()) { // Goes to the next row of data if available
        Manufacturer manufacturer = new Manufacturer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
        result.add(manufacturer);
      }

    } catch (SQLException e) {
      System.out.println("Error trying to search by country");
      e.printStackTrace();
    }
    return result;
  }
  public ObservableList<Manufacturer> searchByPhone(String phone) {
    ObservableList<Manufacturer> result = FXCollections.observableArrayList();
    String sql = "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer WHERE phone = '" + phone + "';";

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql); // use the executeQuery() function when a result is expected

      while (resultSet.next()) { // Goes to the next row of data if available
        Manufacturer manufacturer = new Manufacturer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
        result.add(manufacturer);
      }

    } catch (SQLException e) {
      System.out.println("Error trying to search by phone");
      e.printStackTrace();
    }
    return result;
  }
  public ObservableList<Manufacturer> searchByEmail(String email) {
    ObservableList<Manufacturer> result = FXCollections.observableArrayList();
    String sql = "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer WHERE email = '" + email + "';";

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql); // use the executeQuery() function when a result is expected

      while (resultSet.next()) { // Goes to the next row of data if available
        Manufacturer manufacturer = new Manufacturer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
        result.add(manufacturer);
      }

    } catch (SQLException e) {
      System.out.println("Error trying to search by email");
      e.printStackTrace();
    }
    return result;
  }
}