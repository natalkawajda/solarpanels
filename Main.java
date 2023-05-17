public class Main
{
  public static void setConnection(){
    DatabaseConnector connection = new DatabaseConnector();
    connection.connect("localhost", 5432, "postgres", "Olszyna12");
    connection.UpdateManufacturersName("chuj");
  }

  public static void main(String[] args)
  {
    Main.setConnection();
  }
}
