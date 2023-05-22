package view;
import model.PVPanels;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

public class PVPanelsController {
    @FXML
    private TableView<PVPanels> pvPanelsTable;
    @FXML
    private TableColumn<PVPanels, Date> date;
    @FXML
    private TableColumn<PVPanels, Time> time;
    @FXML
    private TableColumn<PVPanels, Integer> id;
    @FXML
    private TableColumn<PVPanels, Integer> panel_id;
    @FXML
    private TableColumn<PVPanels, Float> voltage;
    @FXML
    private TableColumn<PVPanels, Float> current;
    @FXML
    private TableColumn<PVPanels, Integer> solar_flux;
    @FXML
    private TableColumn<PVPanels, Float> power_out;
    @FXML
    private TableColumn<PVPanels, Float> efficiency;
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
        voltage.setCellValueFactory(new PropertyValueFactory<>("voltage"));
        current.setCellValueFactory(new PropertyValueFactory<>("current"));
        solar_flux.setCellValueFactory(new PropertyValueFactory<>("solar_flux"));
        power_out.setCellValueFactory(new PropertyValueFactory<>("power_out"));
        efficiency.setCellValueFactory(new PropertyValueFactory<>("efficiency"));
        pvPanelsTable.setItems(viewHandler.getConnection().retrievePVPanels());
    }
    public void handleMainLoad(ActionEvent e) {
        viewHandler.changeScene(ViewHandler.MAIN_PAGE_SCENE);
    }
}