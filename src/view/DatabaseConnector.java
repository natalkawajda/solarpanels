package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Location;
import model.Manufacturer;
import model.ManufacturerHistory;
import model.PVPanels;
import model.Panels;
import model.THPanels;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

public class DatabaseConnector
{
  //private Connection connection;

  private String host;
  private int portNo;
  private String userName;
  private String password;

  public DatabaseConnector(String host, int portNo, String userName,
      String password)
  {
    this.host = host;
    this.portNo = portNo;
    this.userName = userName;
    this.password = password;
  }

  public synchronized Connection getConnection()
  {
    // Establishing a PostgreSQL database connection
    String databaseUrl =
        "jdbc:postgresql://" + host + ":" + portNo + "/" + userName;

    try
    {
      return DriverManager.getConnection(databaseUrl, userName, password);
      //System.out.println("Connection established to: " + databaseUrl);
    }
    catch (Exception exception)
    {
      System.out.println("Connection failed");
      exception.printStackTrace();
    }
    return null;
  }

  public void UpdateManufacturersName(String newName, int id)
  {
    String sql = "UPDATE solarpanels.manufacturer SET name = ? WHERE manufacturer_id = ?;";
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, newName);
      statement.setInt(2, id);
      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated > 0)
      {
        System.out.println("model.Manufacturer name updated successfully.");
      }
      else
      {
        System.out.println("No manufacturer found with the specified ID.");
      }
      //statement.close();
    }
    catch (SQLException e)
    {
      System.out.println("Error trying to update model.Manufacturer table");
      e.printStackTrace();
    }
  }

  public ObservableList<Manufacturer> retrieveManufacturer()
  {
    ObservableList<Manufacturer> result = FXCollections.observableArrayList();

    String sql = "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer GROUP BY manufacturer_ID;";

    try (Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          sql); // use the executeQuery() function when a result is expected

      while (resultSet.next())
      { // Goes to the next row of data if available
        Manufacturer manufacturer = new Manufacturer(resultSet.getInt(1),
            resultSet.getString(2), resultSet.getString(3),
            resultSet.getString(4), resultSet.getString(5));
        result.add(manufacturer);
      }

    }
    catch (SQLException e)
    {
      System.out.println("Error trying to generate table of manufacturers");
      e.printStackTrace();
    }

    return result;
  }

  public ObservableList<Panels> retrievePanels()
  {
    ObservableList<Panels> result = FXCollections.observableArrayList();

    String sql = "select panel_id, manufacturer.name, type, row, \"column\", installation_date FROM solarpanels.Panel JOIN solarpanels.Manufacturer  ON solarpanels.manufacturer.manufacturer_ID = solarpanels.Panel.manufacturer_ID;";

    try (Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          sql); // use the executeQuery() function when a result is expected

      while (resultSet.next())
      { // Goes to the next row of data if available
        Panels panels = new Panels(resultSet.getInt(1), resultSet.getString(2),
            resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5),
            resultSet.getDate(6));
        result.add(panels);
      }

    }
    catch (SQLException e)
    {
      System.out.println("Error trying to generate table of panels");
      e.printStackTrace();
    }

    return result;
  }

  public ObservableList<PVPanels> retrievePVPanels()
  {
    ObservableList<PVPanels> result = FXCollections.observableArrayList();

    String sql = "select timestamp::date as date, timestamp::time as time, measure_ID, panel_ID, round(voltage, 2) as voltage, round(current, 2) as current, solar_flux, round(power_out, 2) as power_out, round(efficiency, 4) as efficiency FROM solarpanels.measure_pv order by measure_ID desc;";

    try (Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          sql); // use the executeQuery() function when a result is expected

      while (resultSet.next())
      { // Goes to the next row of data if available
        PVPanels pvpanels = new PVPanels(resultSet.getDate(1),
            resultSet.getTime(2), resultSet.getInt(3), resultSet.getInt(4),
            resultSet.getDouble(5), resultSet.getDouble(6), resultSet.getInt(7),
            resultSet.getDouble(8), resultSet.getDouble(9));
        result.add(pvpanels);
      }

    }
    catch (SQLException e)
    {
      System.out.println("Error trying to generate table of pvpanels");
      e.printStackTrace();
    }

    return result;
  }

  public ObservableList<THPanels> retrieveTHPanels()
  {
    ObservableList<THPanels> result = FXCollections.observableArrayList();

    String sql = "select timestamp::date as date, timestamp::time as time, measure_ID, panel_ID, round(a_temperature, 2) as a_temperature, round(water_in_temp, 2) as water_in_temp, round(water_out_temp, 2) as water_out_temp, round(efficiency, 2) as efficiency FROM solarpanels.measure_th order by measure_ID desc;";

    try (Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          sql); // use the executeQuery() function when a result is expected

      while (resultSet.next())
      { // Goes to the next row of data if available
        THPanels thpanels = new THPanels(resultSet.getDate(1),
            resultSet.getTime(2), resultSet.getInt(3), resultSet.getInt(4),
            resultSet.getDouble(5), resultSet.getDouble(6),
            resultSet.getDouble(7), resultSet.getDouble(8));
        result.add(thpanels);
      }

    }
    catch (SQLException e)
    {
      System.out.println("Error trying to generate table of thpanels");
      e.printStackTrace();
    }

    return result;
  }

  public ObservableList<ManufacturerHistory> retrieveManufacturerHistory()
  {
    ObservableList<ManufacturerHistory> result = FXCollections.observableArrayList();

    String sql = "select history_id, timestamp::date as date, timestamp::time as time, manufacturer_ID, name, country, phone, email FROM solarpanels.manufacturer_history GROUP BY history_id;";

    try (Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          sql); // use the executeQuery() function when a result is expected

      while (resultSet.next())
      { // Goes to the next row of data if available
        ManufacturerHistory manufacturerHistory = new ManufacturerHistory(
            resultSet.getInt(1), resultSet.getDate(2), resultSet.getTime(3),
            resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6),
            resultSet.getString(7), resultSet.getString(8));
        result.add(manufacturerHistory);
      }

    }
    catch (SQLException e)
    {
      System.out.println("Error trying to generate table of thpanels");
      e.printStackTrace();
    }

    return result;
  }

  public void UpdateManufacturersCountry(String newCountry, int id)
  {
    String sql = "UPDATE solarpanels.manufacturer SET country = ? WHERE manufacturer_id = ?;";

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, newCountry);
      statement.setInt(2, id);
      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated > 0)
      {
        System.out.println("model.Manufacturer country updated successfully.");
      }
      else
      {
        System.out.println("No manufacturer found with the specified ID.");
      }
      statement.close();
      //      connection.close();
    }
    catch (SQLException e)
    {
      System.out.println("Error trying to update model.Manufacturer table");
      e.printStackTrace();
    }

  }

  public String UpdateManufacturersPhone(String newPhone, int id)
  {
    String sql = "UPDATE solarpanels.manufacturer SET phone = ? WHERE manufacturer_id = ?;";

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, newPhone);
      statement.setInt(2, id);
      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated > 0)
      {
        System.out.println("model.Manufacturer phone updated successfully.");
      }
      else
      {
        System.out.println("No manufacturer found with the specified ID.");
      }
      statement.close();
      //      connection.close();
    }
    catch (SQLException e)
    {
      System.out.println("Error trying to update model.Manufacturer table");
      return "Error trying to update model.Manufacturer table";
      //e.printStackTrace();
    }
    return "Phone number updated successfully";
  }

  public String UpdateManufacturersEmail(String newEmail, int id)
  {
    String sql = "UPDATE solarpanels.manufacturer SET email = ? WHERE manufacturer_id = ?;";

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, newEmail);
      statement.setInt(2, id);
      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated > 0)
      {
        System.out.println("model.Manufacturer e-mail updated successfully.");
      }
      else
      {
        System.out.println("No manufacturer found with the specified ID.");
      }
      statement.close();
      //      connection.close();
    }
    catch (SQLException e)
    {
      System.out.println("Error trying to update model.Manufacturer table");
      return "Error trying to update model.Manufacturer table";
    }
    return "E-mail updated successfully.";
  }

  public ObservableList<Manufacturer> searchByID(int id)
  {
    ObservableList<Manufacturer> result = FXCollections.observableArrayList();
    String sql =
        "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer WHERE manufacturer_id = "
            + id + ";";

    try (Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          sql); // use the executeQuery() function when a result is expected

      while (resultSet.next())
      { // Goes to the next row of data if available
        Manufacturer manufacturer = new Manufacturer(resultSet.getInt(1),
            resultSet.getString(2), resultSet.getString(3),
            resultSet.getString(4), resultSet.getString(5));
        result.add(manufacturer);
      }

    }
    catch (SQLException e)
    {
      System.out.println("Error trying to search by id");
      e.printStackTrace();
    }
    return result;
  }

  public ObservableList<Manufacturer> searchByName(String name)
  {
    ObservableList<Manufacturer> result = FXCollections.observableArrayList();
    String sql =
        "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer WHERE name ILIKE '%"
            + name + "%';";

    try (Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          sql); // use the executeQuery() function when a result is expected

      while (resultSet.next())
      { // Goes to the next row of data if available
        Manufacturer manufacturer = new Manufacturer(resultSet.getInt(1),
            resultSet.getString(2), resultSet.getString(3),
            resultSet.getString(4), resultSet.getString(5));
        result.add(manufacturer);
      }

    }
    catch (SQLException e)
    {
      System.out.println("Error trying to search by name");
      e.printStackTrace();
    }
    return result;
  }

  public ObservableList<Manufacturer> searchByCountry(String country)
  {
    ObservableList<Manufacturer> result = FXCollections.observableArrayList();
    String sql =
        "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer WHERE country ILIKE '"
            + country + "';";

    try (Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          sql); // use the executeQuery() function when a result is expected

      while (resultSet.next())
      { // Goes to the next row of data if available
        Manufacturer manufacturer = new Manufacturer(resultSet.getInt(1),
            resultSet.getString(2), resultSet.getString(3),
            resultSet.getString(4), resultSet.getString(5));
        result.add(manufacturer);
      }

    }
    catch (SQLException e)
    {
      System.out.println("Error trying to search by country");
      e.printStackTrace();
    }
    return result;
  }

  public ObservableList<Manufacturer> searchByPhone(String phone)
  {
    ObservableList<Manufacturer> result = FXCollections.observableArrayList();
    String sql =
        "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer WHERE phone ILIKE '%"
            + phone + "%';";

    try (Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          sql); // use the executeQuery() function when a result is expected

      while (resultSet.next())
      { // Goes to the next row of data if available
        Manufacturer manufacturer = new Manufacturer(resultSet.getInt(1),
            resultSet.getString(2), resultSet.getString(3),
            resultSet.getString(4), resultSet.getString(5));
        result.add(manufacturer);
      }

    }
    catch (SQLException e)
    {
      System.out.println("Error trying to search by phone");
      e.printStackTrace();
    }
    return result;
  }

  public ObservableList<Manufacturer> searchByEmail(String email)
  {
    ObservableList<Manufacturer> result = FXCollections.observableArrayList();
    String sql =
        "SELECT manufacturer_ID, name, country, phone, email FROM solarpanels.Manufacturer WHERE email ILIKE '%"
            + email + "%';";

    try(Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          sql); // use the executeQuery() function when a result is expected

      while (resultSet.next())
      { // Goes to the next row of data if available
        Manufacturer manufacturer = new Manufacturer(resultSet.getInt(1),
            resultSet.getString(2), resultSet.getString(3),
            resultSet.getString(4), resultSet.getString(5));
        result.add(manufacturer);
      }

    }
    catch (SQLException e)
    {
      System.out.println("Error trying to search by email");
      e.printStackTrace();
    }
    return result;
  }

  public ObservableList<PVPanels> filterByDate(LocalDate initialDate,
      LocalDate finalDate)
  {
    ObservableList<PVPanels> result = FXCollections.observableArrayList();
    String sql =
        "select timestamp::date as date, timestamp::time as time, measure_ID, panel_ID, round(voltage, 2) as voltage, round(current, 2) as current, solar_flux, round(power_out, 2) as power_out, round(efficiency, 4) as efficiency FROM solarpanels.measure_pv WHERE timestamp::date >= '"
            + initialDate + "' AND timestamp::date <= '" + finalDate + "';";

    try (Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          sql); // use the executeQuery() function when a result is expected

      while (resultSet.next())
      { // Goes to the next row of data if available
        PVPanels pvpanels = new PVPanels(resultSet.getDate(1),
            resultSet.getTime(2), resultSet.getInt(3), resultSet.getInt(4),
            resultSet.getDouble(5), resultSet.getDouble(6), resultSet.getInt(7),
            resultSet.getDouble(8), resultSet.getDouble(9));
        result.add(pvpanels);
      }

    }
    catch (SQLException e)
    {
      System.out.println("Error trying to filter by date");
      e.printStackTrace();
    }
    return result;
  }


  public PVPanels retrievePVPanel(Location location)
  {
    PVPanels pvPanels = null;
    String sql = "select timestamp::date as date, timestamp::time as time, measure_ID, panel_ID, round(voltage, 2) as voltage, round(current, 2) as current, solar_flux, round(power_out, 2) as power_out, round(efficiency, 4) as efficiency FROM solarpanels.measure_pv WHERE panel_id = (Select panel_ID from solarpanels.Panel WHERE row = ? AND \"column\" = ?) order by timestamp desc;";

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, location.getRow());
      statement.setInt(2, location.getColumn());
      ResultSet resultSet = statement.executeQuery();

      //while if i want all of them not the first only
      if (resultSet.next())
      {
        Date date = resultSet.getDate("date");
        Time time = resultSet.getTime("time");
        int panel_id = resultSet.getInt("panel_id");
        double voltage = resultSet.getFloat("voltage");
        double current = resultSet.getFloat("current");
        int solarFlux = resultSet.getInt("solar_flux");
        double powerOut = resultSet.getFloat("power_out");
        double efficiency = resultSet.getFloat("efficiency");

        pvPanels = new PVPanels(date, time, panel_id, voltage, current,
            solarFlux, powerOut, efficiency);
      }
    }
    catch (SQLException e)
    {
      System.out.println(
          "Error retrieving performance data: " + e.getMessage());
    }

    return pvPanels;

  }

  public ObservableList<THPanels> filterTHByDate(LocalDate initialDate,
      LocalDate finalDate)
  {
    ObservableList<THPanels> result = FXCollections.observableArrayList();
    String sql =
        "select timestamp::date as date, timestamp::time as time, measure_ID, panel_ID, round(a_temperature, 2) as a_temperature, round(water_in_temp, 2) as water_in_temp, round(water_out_temp, 2) as water_out_temp, round(efficiency, 2) as efficiency FROM solarpanels.measure_th WHERE timestamp::date >= '"
            + initialDate + "' AND timestamp::date <= '" + finalDate + "';";

    try (Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          sql); // use the executeQuery() function when a result is expected

      while (resultSet.next())
      { // Goes to the next row of data if available
        THPanels thPanels = new THPanels(resultSet.getDate(1),
            resultSet.getTime(2), resultSet.getInt(3), resultSet.getInt(4),
            resultSet.getDouble(5), resultSet.getDouble(6), resultSet.getDouble(7),
            resultSet.getDouble(8));
        result.add(thPanels);
      }

    }
    catch (SQLException e)
    {
      System.out.println("Error trying to filter by date");
      e.printStackTrace();
    }
    return result;
  }

  public THPanels retrieveTHPanel(Location location)
  {
    THPanels thPanels = null;
    String sql = "select timestamp::date as date, timestamp::time as time, measure_ID, panel_ID, round(a_temperature, 2) as a_temperature, round(water_in_temp, 2) as water_in_temp, round(water_out_temp, 2) as water_out_temp, round(efficiency, 2) as efficiency FROM solarpanels.measure_th WHERE panel_id = (Select panel_ID from solarpanels.Panel WHERE row = ? AND \"column\" = ?) order by timestamp desc;";

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, location.getRow());
      statement.setInt(2, location.getColumn());
      ResultSet resultSet = statement.executeQuery();

      //while if i want all of them not the first only
      if (resultSet.next())
      {
        Date date = resultSet.getDate("date");
        Time time = resultSet.getTime("time");
        int panel_id = resultSet.getInt("panel_ID");
        double a_temperature = resultSet.getFloat("a_temperature");
        double water_in_temp = resultSet.getFloat("water_in_temp");
        double water_out_temp = resultSet.getFloat("water_out_temp");
        double efficiency = resultSet.getFloat("efficiency");

        thPanels = new THPanels(date, time, panel_id, a_temperature,
            water_in_temp, water_out_temp, efficiency);
      }
    }
    catch (SQLException e)
    {
      System.out.println(
          "Error retrieving performance data: " + e.getMessage());
    }

    return thPanels;

  }

  public void storePVMeasurements(PVPanels pvPanels)
  {
    String sql =
        "INSERT INTO solarpanels.measure_pv (timestamp, panel_ID, voltage, current, solar_flux, power_out, efficiency) VALUES ('"
            + pvPanels.getTimestamp() + "', " + pvPanels.getPanel_id() + ", "
            + pvPanels.getVoltage() + ", " + pvPanels.getCurrent() + ", "
            + pvPanels.getSolar_flux() + ", " + pvPanels.getPower_out() + ", "
            + pvPanels.getEfficiency() + ");";

    try(Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      statement.execute(sql);
    }
    catch (SQLException e)
    {
      System.out.println(
          "Error trying to insert a new measurement in measure_pv table");
      e.printStackTrace();
    }
  }

  public void storeTHMeasurements(THPanels thPanels)
  {
    String sql =
        "INSERT INTO solarpanels.measure_th (timestamp, panel_ID, a_temperature,water_in_temp,water_out_temp,efficiency) VALUES ('"
            + thPanels.getTimestamp() + "', " + thPanels.getPanel_id() + ", "
            + thPanels.getA_temperature() + ", " + thPanels.getWater_in_temp()
            + ", " + thPanels.getWater_out_temp() + ", "
            + thPanels.getEfficiency() + ");";

    try(Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      statement.execute(sql);
    }
    catch (SQLException e)
    {
      System.out.println(
          "Error trying to insert a new measurement in measure_th table");
      e.printStackTrace();
    }
  }

  public void generatePVMeasurements(PVPanels pvPanels)
  {
    String sql =
        "INSERT INTO solarpanels.measure_pv (timestamp, panel_ID, voltage, current, solar_flux, power_out, efficiency) VALUES ('"
            + convertToSqlTimestamp(pvPanels.getDate()) + "', "
            + pvPanels.getPanel_id() + ", " + pvPanels.getVoltage() + ", "
            + pvPanels.getCurrent() + ", " + pvPanels.getSolar_flux() + ", "
            + pvPanels.getPower_out() + ", " + pvPanels.getEfficiency() + ");";

    try(Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      statement.execute(sql);
    }
    catch (SQLException e)
    {
      System.out.println(
          "Error trying to insert a new measurement in measure_pv table");
      e.printStackTrace();
    }
  }

  public void generateTHMeasurements(THPanels thPanels)
  {
    String sql =
        "INSERT INTO solarpanels.measure_th (timestamp, panel_ID, a_temperature,water_in_temp,water_out_temp,efficiency) VALUES ('"
            + convertToSqlTimestamp(thPanels.getDate()) + "', "
            + thPanels.getPanel_id() + ", " + thPanels.getA_temperature() + ", "
            + thPanels.getWater_in_temp() + ", " + thPanels.getWater_out_temp()
            + ", " + thPanels.getEfficiency() + ");";

    try(Connection connection = getConnection())
    {
      Statement statement = connection.createStatement();
      statement.execute(sql);
    }
    catch (SQLException e)
    {
      System.out.println(
          "Error trying to insert a new measurement in measure_pv table");
      e.printStackTrace();
    }
  }

  private Timestamp convertToSqlTimestamp(java.util.Date uDate)
  {
    Timestamp timestamp = new Timestamp(uDate.getTime());
    return timestamp;
  }

    public void close()
    {
      // Close the connection

        System.exit(2);
    }
}