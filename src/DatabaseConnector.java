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
  public void UpdateManufacturersName(String newName){
    String sql = "UPDATE solarpanels.manufacturer SET name = ? WHERE manufacturer_id = 1;";

    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1,newName);
      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated > 0) {
        System.out.println("Manufacturer name updated successfully.");
      } else {
        System.out.println("No manufacturer found with the specified ID.");
      }
      statement.close();
      connection.close();
    } catch (SQLException e) {
      System.out.println("Error trying to update Manufacturer table");
      e.printStackTrace();
    }

  }
}
