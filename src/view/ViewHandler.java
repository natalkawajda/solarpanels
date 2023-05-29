package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ViewHandler
{

  private Stage primaryStage;
  private Scene mainPageScene;
  private Scene manufacturerScene;
  private Scene pvPanelsScene;
  private Scene thPanelsScene;
  private Scene panelsScene;
  private Scene manufacturerHistoryScene;
  private Scene locationScene;
  private LocationController locationController;
  private THPanelsController thPanelsController;
  private PanelsController panelsController;
  private PVPanelsController pvPanelsController;
  private MainPageController mainPageController;
  private ManufacturerController manufacturerController;
  private ManufacturerHistoryController manufacturerHistoryController;
  public static final String MAIN_PAGE_SCENE = "MAIN_PAGE_SCENE";
  public static final String PV_PANELS = "PV_PANELS";
  public static final String TH_PANELS = "TH_PANELS";
  public static final String MANUFACTURER = "MANUFACTURER";
  public static final String PANELS = "PANELS";
  public static final String MANUFACTURER_HISTORY = "MANUFACTURER_HISTORY";
  public static final String LOCATION = "LOCATION";
  private DatabaseConnector connection;
  private PVMeasurementsData pvMeasurementsData;
  private THMeasurementsData thMeasurementsData;

  public ViewHandler(Stage primaryStage, DatabaseConnector connection)
  {
    this.primaryStage = primaryStage;
    this.connection = connection;
    this.pvMeasurementsData = new PVMeasurementsData(connection);
    this.thMeasurementsData = new THMeasurementsData(connection);

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("MainPage.fxml"));
    try
    {
      mainPageScene = new Scene(loader.load());
      mainPageController = loader.getController();
      mainPageController.init(this);
      //            pvMeasurementsData.generateData();
      //            thMeasurementsData.generateData();
    }
    catch (IOException e)
    {
      System.out.println("Failed to load MainPage.fxml");
      System.exit(1);
    }
    loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Manufacturer.fxml"));
    try
    {
      manufacturerScene = new Scene(loader.load());
      manufacturerController = loader.getController();
      manufacturerController.init(this);
    }
    catch (IOException e)
    {
      System.out.println("Failed to load Manufacturer.fxml");
      System.exit(1);
    }
    loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("PVPanels.fxml"));
    try
    {
      pvPanelsScene = new Scene(loader.load());
      pvPanelsController = loader.getController();
      pvPanelsController.init(this);
    }
    catch (IOException e)
    {
      System.out.println("Failed to load PVPanels.fxml");
      System.exit(1);
    }
    loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("THPanels.fxml"));
    try
    {
      thPanelsScene = new Scene(loader.load());
      thPanelsController = loader.getController();
      thPanelsController.init(this);
    }
    catch (IOException e)
    {
      System.out.println("Failed to load THPanels.fxml");
      System.exit(1);
    }
    loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Panel.fxml"));
    try
    {
      panelsScene = new Scene(loader.load());
      panelsController = loader.getController();
      panelsController.init(this);
    }
    catch (IOException e)
    {
      System.out.println("Failed to load Panel.fxml");
      System.exit(1);
    }
    loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("ManufacturerHistory.fxml"));
    try
    {
      manufacturerHistoryScene = new Scene(loader.load());
      manufacturerHistoryController = loader.getController();
      manufacturerHistoryController.init(this);
    }
    catch (IOException e)
    {
      System.out.println("Failed to load ManufacturerHistory.fxml");
      System.exit(1);
    }
    loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Location.fxml"));
    try
    {
      locationScene = new Scene(loader.load());
      locationController = loader.getController();
      locationController.init(this);
    }
    catch (IOException e)
    {
      System.out.println("Failed to load Location.fxml");
      System.exit(1);
    }
    changeScene("MAIN_PAGE_SCENE");
  }

  public void changeScene(String sceneName)
  {
    if (MAIN_PAGE_SCENE.equals(sceneName))
    {
      primaryStage.setTitle("Main Page");
      primaryStage.setScene(mainPageScene);
      primaryStage.show();
    }
    else if (MANUFACTURER.equals(sceneName))
    {
      primaryStage.setTitle("Manufacturer");
      primaryStage.setScene(manufacturerScene);
      primaryStage.show();
      manufacturerController.updateView();
    }
    else if (PANELS.equals(sceneName))
    {
      primaryStage.setTitle("Panels");
      primaryStage.setScene(panelsScene);
      primaryStage.show();
      panelsController.updateView();
    }
    else if (PV_PANELS.equals(sceneName))
    {
      primaryStage.setTitle("Photovoltaic Panels");
      primaryStage.setScene(pvPanelsScene);
      primaryStage.show();
      pvPanelsController.updateView();
    }
    else if (TH_PANELS.equals(sceneName))
    {
      primaryStage.setTitle("Thermo Panels");
      primaryStage.setScene(thPanelsScene);
      primaryStage.show();
      thPanelsController.updateView();
    }
    else if (MANUFACTURER_HISTORY.equals(sceneName))
    {
      primaryStage.setTitle("Manufacturer History");
      primaryStage.setScene(manufacturerHistoryScene);
      primaryStage.show();
      manufacturerHistoryController.updateView();
    }
    else if (LOCATION.equals(sceneName))
    {
      primaryStage.setTitle("Location");
      primaryStage.setScene(locationScene);
      primaryStage.show();
    }

    primaryStage.setOnCloseRequest((e) -> {
      System.exit(1);
    });
  }

  public DatabaseConnector getConnection()
  {
    return connection;
  }
}
