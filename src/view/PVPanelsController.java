package view;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.DateInterval;
import model.PVPanels;
import javafx.fxml.FXML;

import java.sql.*;
import java.time.LocalDate;


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
        voltage.setCellValueFactory(new PropertyValueFactory<>("voltage"));
        current.setCellValueFactory(new PropertyValueFactory<>("current"));
        solar_flux.setCellValueFactory(new PropertyValueFactory<>("solar_flux"));
        power_out.setCellValueFactory(new PropertyValueFactory<>("power_out"));
        efficiency.setCellValueFactory(new PropertyValueFactory<>("efficiency"));
        pvPanelsTable.setItems(viewHandler.getConnection().filterByDate(initialDatePicker.getValue(),
            finalDatePicker.getValue()));
    }
}