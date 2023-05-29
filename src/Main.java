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
    connection.connect("balarama.db.elephantsql.com", 5432, "oodjpfdu", "OVr7iGfVftIao0lHjvnXthVvIDXdturJ");
    viewHandler = new ViewHandler(primaryStage, connection);
//    PVMeasurementsGenerator pvMeasurementsGenerator = new PVMeasurementsGenerator(connection);
//    pvMeasurementsGenerator.start();
//    THMeasurementsGenerator thMeasurementsGenerator = new THMeasurementsGenerator(connection);
//    thMeasurementsGenerator.start();
  }


  public static void main(String[] args)
  {
    Application.launch(Main.class);
  }

}
