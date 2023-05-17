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
  public void UpdateManufacturersName(String name){
    String sql = "UPDATE solarpanels.manufacturer SET phone = '" + name + "' WHERE manufacturer_ID = 1;";

    try {
      Statement statement = connection.createStatement();
      statement.execute(sql);
    } catch (SQLException e) {
      System.out.println("Error trying to update Manufacturer table");
      e.printStackTrace();
    }
  }
}