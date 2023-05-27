package view;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.DateInterval;
import model.PVPanels;
import model.THPanels;
import javafx.fxml.FXML;

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
    public void handleFiltering() {
        Dialog<DateInterval> dialog = new Dialog<>();
        dialog.setTitle("Select Date Interval");
        dialog.setHeaderText("Please enter the initial and final dates to check performance.");

        // Create the content layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        // Create the date pickers
        DatePicker initialDatePicker = new DatePicker();
        DatePicker finalDatePicker = new DatePicker();

        // Add the components to the layout
        gridPane.add(new javafx.scene.control.Label("Initial Date:"), 0, 0);
        gridPane.add(initialDatePicker, 1, 0);
        gridPane.add(new javafx.scene.control.Label("Final Date:"), 0, 1);
        gridPane.add(finalDatePicker, 1, 1);

        // Set the content of the dialog
        dialog.getDialogPane().setContent(gridPane);

        // Set the buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.showAndWait();
        //		System.out.println("view.AverageSpeedsController Update View called");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        panel_id.setCellValueFactory(new PropertyValueFactory<>("panel_id"));
        a_temperature.setCellValueFactory(new PropertyValueFactory<>("a_temperature"));
        water_in_temp.setCellValueFactory(new PropertyValueFactory<>("water_in_temp"));
        water_out_temp.setCellValueFactory(new PropertyValueFactory<>("water_out_temp"));
        efficiency.setCellValueFactory(new PropertyValueFactory<>("efficiency"));
        thPanelsTable.setItems(viewHandler.getConnection().filterTHByDate(initialDatePicker.getValue(),
            finalDatePicker.getValue()));
    }
}

