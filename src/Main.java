import javafx.application.Application;
import javafx.stage.Stage;
import view.DatabaseConnector;
import view.ViewHandler;

public class Main extends Application {
  private ViewHandler viewHandler;
  private DatabaseConnector connection;
  public void start (Stage primaryStage)
  {
    connection = new DatabaseConnector();
    connection.connect("localhost", 5433, "postgres", "1234");
    viewHandler = new ViewHandler(primaryStage, connection);
  }


  public static void main(String[] args)
  {
    Application.launch(Main.class);
  }

}
