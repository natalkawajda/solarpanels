package view;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class ViewHandler {
    private Stage primaryStage;
    private Scene mainPageScene;
    private MainPageController mainPageController;
    public static final String MAIN_PAGE_SCENE = "MAIN_PAGE_SCENE";
    private DatabaseConnector connection;

    public ViewHandler (Stage primaryStage,DatabaseConnector connection){
        this.primaryStage = primaryStage;
        this.connection = connection;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainPage.fxml"));
        try
        {
            mainPageScene = new Scene(loader.load());
            mainPageController = loader.getController();
            mainPageController.init(this);
        }
        catch (IOException e)
        {
            System.out.println("Failed to load MainPage.fxml");
            System.exit(1);
        }

    }
}
