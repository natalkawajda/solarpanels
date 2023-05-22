package view;
import javafx.event.ActionEvent;
import model.PVPanels;
import model.THPanels;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class THPanelsController {
    @FXML
    private TableView<THPanels> thPanelsTable;
    @FXML
    private TableColumn<THPanels, Date> date;
    @FXML
    private TableColumn<THPanels, Time> time;
    @FXML
    private TableColumn<THPanels, Integer> id;
    @FXML
    private TableColumn<THPanels, Integer> panel_id;
    @FXML
    private TableColumn<THPanels, Float> a_temperature;
    @FXML
    private TableColumn<THPanels, Float> water_in_temp;
    @FXML
    private TableColumn<THPanels, Float> water_out_temp;
    @FXML
    private TableColumn<THPanels, Float> efficiency;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
    }
    public void updateView() {
        //		System.out.println("view.AverageSpeedsController Update View called");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        panel_id.setCellValueFactory(new PropertyValueFactory<>("panel_id"));
        a_temperature.setCellValueFactory(new PropertyValueFactory<>("a_temperature"));
        water_in_temp.setCellValueFactory(new PropertyValueFactory<>("water_in_temp"));
        water_out_temp.setCellValueFactory(new PropertyValueFactory<>("water_out_temp"));
        efficiency.setCellValueFactory(new PropertyValueFactory<>("efficiency"));
        thPanelsTable.setItems(viewHandler.getConnection().retrieveTHPanels());
    }
    public void handleMainLoad(ActionEvent e) {
        viewHandler.changeScene(ViewHandler.MAIN_PAGE_SCENE);
    }
}

