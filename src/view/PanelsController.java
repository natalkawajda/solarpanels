package view;
import javafx.event.ActionEvent;
import model.Manufacturer;
import model.Panels;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PanelsController {
    @FXML
    private TableView<Panels> panelsTable;
    @FXML
    private TableColumn<Panels, Integer> id;
    @FXML
    private TableColumn<Panels, String> manufacturer;
    @FXML
    private TableColumn<Panels, String> type;
    @FXML
    private TableColumn<Panels, Integer> row;
    @FXML
    private TableColumn<Panels, Integer> column;
    @FXML
    private TableColumn<Panels, Date> installation_date;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
    }
    public void updateView() {
        //		System.out.println("view.AverageSpeedsController Update View called");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        manufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        row.setCellValueFactory(new PropertyValueFactory<>("row"));
        column.setCellValueFactory(new PropertyValueFactory<>("column"));
        installation_date.setCellValueFactory(new PropertyValueFactory<>("installation_date"));

        panelsTable.setItems(viewHandler.getConnection().retrievePanels());
    }
    public void handleMainLoad(ActionEvent e) {
        viewHandler.changeScene(ViewHandler.MAIN_PAGE_SCENE);
    }
}