public class Main
{
  public static void setConnection(){
    DatabaseConnector connection = new DatabaseConnector();
    connection.connect("localhost", 5432, "postgres", "*****");
    connection.UpdateManufacturersName("0700880079");
  }

  public static void main(String[] args)
  {
    Main.setConnection();
  }
}
