public class Main
{
  public static void setConnection(){
    DatabaseConnector connection = new DatabaseConnector();
    connection.connect("localhost", 5433, "postgres", "1234");
    connection.UpdateManufacturersName("0700880079");
  }

  public static void main(String[] args)
  {
    Main.setConnection();
  }
}