package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

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
        System.out.println("model.Manufacturer country updated successfully.");
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
        System.out.println("model.Manufacturer phone updated successfully.");
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
        System.out.println("model.Manufacturer e-mail updated successfully.");
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
    String sql = "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer WHERE name ILIKE '%" + name + "%';";

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
    String sql = "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer WHERE country ILIKE '" + country + "';";

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
    String sql = "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer WHERE phone ILIKE '%" + phone + "%';";

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
    String sql = "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer WHERE email ILIKE '%" + email + "%';";

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
  public ObservableList<PVPanels> filterByDate(LocalDate initialDate, LocalDate finalDate) {
    ObservableList<PVPanels> result = FXCollections.observableArrayList();
    String sql = "select timestamp::date as date, timestamp::time as time, measure_ID, panel_ID, voltage, current, solar_flux, power_out, efficiency FROM solarpanels.measure_pv WHERE timestamp::date >= '" + initialDate + "' AND timestamp::date <= '" + finalDate + "';";

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql); // use the executeQuery() function when a result is expected

      while (resultSet.next()) { // Goes to the next row of data if available
        PVPanels pvpanels = new PVPanels(resultSet.getDate(1),resultSet.getTime(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getFloat(5), resultSet.getFloat(6), resultSet.getInt(7), resultSet.getFloat(8), resultSet.getFloat(9));
        result.add(pvpanels);
      }

    } catch (SQLException e) {
      System.out.println("Error trying to filter by date");
      e.printStackTrace();
    }
    return result;
  }
//  public ObservableList<PVPanels> retrievePVPanel(Location location)
//  {
//    ObservableList<PVPanels> result = FXCollections.observableArrayList();
//    String sql =
//        "select timestamp::date as date, timestamp::time as time, measure_ID, panel_ID, voltage, current, solar_flux, power_out, efficiency FROM solarpanels.measure_pv WHERE panel_id = (Select panel_ID from solarpanels.Panel WHERE row = "
//            + location.getRow() + " AND \"column\" = " + location.getColumn() + ");";
//
//    try
//    {
//      Statement statement = connection.createStatement();
//      ResultSet resultSet = statement.executeQuery(sql); // use the executeQuery() function when a result is expected
//
//      while (resultSet.next()) { // Goes to the next row of data if available
//      PVPanels pvpanels = new PVPanels(resultSet.getDate(1),resultSet.getTime(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getFloat(5), resultSet.getFloat(6), resultSet.getInt(7), resultSet.getFloat(8), resultSet.getFloat(9));
//      result.add(pvpanels);
//    }
//    }
//    catch (SQLException e)
//    {
//      System.out.println("Error trying to generate table of pvpanels");
//      e.printStackTrace();
//    }
//
//    return result;
//  }
public PVPanels retrievePVPanel(Location location)
{
  PVPanels pvPanels = null;
  String sql = "select timestamp::date as date, timestamp::time as time, measure_ID, panel_ID, voltage, current, solar_flux, power_out, efficiency FROM solarpanels.measure_pv WHERE panel_id = (Select panel_ID from solarpanels.Panel WHERE row = ? AND \"column\" = ?);";

  try (PreparedStatement statement = connection.prepareStatement(sql)) {
    statement.setInt(1, location.getRow());
    statement.setInt(2, location.getColumn());
    ResultSet resultSet = statement.executeQuery();

    //while if i want all of them not the first only
    if (resultSet.next()) {
      Date date = resultSet.getDate("date");
      Time time = resultSet.getTime("time");
      int panel_id = resultSet.getInt("panel_id");
      float voltage = resultSet.getFloat("voltage");
      float current = resultSet.getFloat("current");
      int solarFlux = resultSet.getInt("solar_flux");
      float powerOut = resultSet.getFloat("power_out");
      float efficiency = resultSet.getFloat("efficiency");

      pvPanels = new PVPanels(date, time, panel_id, voltage, current, solarFlux, powerOut, efficiency);
    }
  } catch (SQLException e) {
    System.out.println("Error retrieving performance data: " + e.getMessage());
  }

  return pvPanels;

}
  public ObservableList<THPanels> filterTHByDate(LocalDate initialDate, LocalDate finalDate) {
    ObservableList<THPanels> result = FXCollections.observableArrayList();
    String sql = "select timestamp::date as date, timestamp::time as time, measure_ID, panel_ID, a_temperature, water_in_temp, water_out_temp, efficiency FROM solarpanels.measure_th WHERE timestamp::date >= '" + initialDate + "' AND timestamp::date <= '" + finalDate + "';";

    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql); // use the executeQuery() function when a result is expected

      while (resultSet.next()) { // Goes to the next row of data if available
        THPanels thPanels = new THPanels(resultSet.getDate(1),resultSet.getTime(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getFloat(5), resultSet.getFloat(6), resultSet.getFloat(7), resultSet.getFloat(8));
        result.add(thPanels);
      }

    } catch (SQLException e) {
      System.out.println("Error trying to filter by date");
      e.printStackTrace();
    }
    return result;
  }
  public THPanels retrieveTHPanel(Location location)
  {
    THPanels thPanels = null;
    String sql = "select timestamp::date as date, timestamp::time as time, measure_ID, panel_ID, a_temperature, water_in_temp, water_out_temp, efficiency FROM solarpanels.measure_th WHERE panel_id = (Select panel_ID from solarpanels.Panel WHERE row = ? AND \"column\" = ?);";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, location.getRow());
      statement.setInt(2, location.getColumn());
      ResultSet resultSet = statement.executeQuery();

      //while if i want all of them not the first only
      if (resultSet.next()) {
        Date date = resultSet.getDate("date");
        Time time = resultSet.getTime("time");
        int panel_id = resultSet.getInt("panel_ID");
        float a_temperature = resultSet.getFloat("a_temperature");
        float water_in_temp = resultSet.getFloat("water_in_temp");
        float water_out_temp = resultSet.getFloat("water_out_temp");
        float efficiency = resultSet.getFloat("efficiency");

        thPanels = new THPanels(date, time, panel_id, a_temperature, water_in_temp, water_out_temp, efficiency);
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving performance data: " + e.getMessage());
    }

    return thPanels;

  }
}