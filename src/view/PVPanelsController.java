package view;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.DateInterval;
import model.PVPanels;
import javafx.fxml.FXML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import model.THPanels;

public class PVPanelsController {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final DecimalFormat df1 = new DecimalFormat("0.0000");
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
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
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
        try
        {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Select Date Interval");
            dialog.setHeaderText(
                "Please enter the initial and final dates to check performance.");

            // Create the content layout
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(20, 150, 10, 10));

            // Create the date pickers
            DatePicker initialDatePicker = new DatePicker();
            DatePicker finalDatePicker = new DatePicker();
            initialDatePicker.setValue(LocalDate.now());
            finalDatePicker.setValue(LocalDate.now());

            // Add the components to the layout
            gridPane.add(new javafx.scene.control.Label("Initial Date:"), 0, 0);
            gridPane.add(initialDatePicker, 1, 0);
            gridPane.add(new javafx.scene.control.Label("Final Date:"), 0, 1);
            gridPane.add(finalDatePicker, 1, 1);

            // Set the content of the dialog
            dialog.getDialogPane().setContent(gridPane);

            // Set the buttons
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK)
            {
                if (finalDatePicker.getValue().isBefore(initialDatePicker.getValue()))
                {
                    throw new Exception("Final date cannot be before initial date");
                }

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
                pvPanelsTable.setItems(viewHandler.getConnection()
                    .filterByDate(initialDatePicker.getValue(), finalDatePicker.getValue()));
                ObservableList<PVPanels> filteredData = viewHandler.getConnection()
                    .filterByDate(initialDatePicker.getValue(), finalDatePicker.getValue());
                savePerformanceDataAsReport(filteredData);
            }
        }catch (Exception l)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText(
                l.getMessage());
            alert.showAndWait();
        }

    }
    public void refresh(){
        viewHandler.changeScene(ViewHandler.PV_PANELS);
    }
    public void savePerformanceDataAsReport(List<PVPanels> performanceDataList) {
        // Prepare the report content
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Performance Data Report\n\n");

        for (PVPanels performanceDataPV : performanceDataList) {
            reportBuilder.append("Date: ").append(performanceDataPV.getDate()).append("\n");
            reportBuilder.append("Time: ").append(performanceDataPV.getTime()).append("\n");
            reportBuilder.append("Measure id: ").append(performanceDataPV.getId()).append("\n");
            reportBuilder.append("Panel id: ").append(performanceDataPV.getId()).append("\n");
            reportBuilder.append("Voltage: ").append(df.format(performanceDataPV.getVoltage())).append("\n");
            reportBuilder.append("Current: ").append(df.format(performanceDataPV.getCurrent())).append("\n");
            reportBuilder.append("Solar flux: ").append(performanceDataPV.getSolar_flux()).append("\n");
            reportBuilder.append("Power out: ").append(df.format(performanceDataPV.getPower_out())).append("\n");
            reportBuilder.append("Efficiency: ").append(df1.format(performanceDataPV.getEfficiency())).append("\n");
            reportBuilder.append("\n");
        }

        // Define the file name and location
        String fileName = "Photovoltaic panels performance report from " + performanceDataList.get(0).getDate() + " to " + performanceDataList.get(performanceDataList.size()-1).getDate() + ".txt";
        String filePath = "C:\\Users\\Wojtek Z Petworld\\Desktop\\jpjp\\" + fileName;

        try {
            // Create the file and write the report content
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            writer.write(reportBuilder.toString());
            writer.close();

            System.out.println("Performance data report saved successfully.");

        } catch (IOException e) {
            System.out.println("Error occurred while saving the performance data report.");
            e.printStackTrace();
        }
    }
    public void help()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Filter by date and generate report");
        alert.setContentText("The 'Filter by date and generate report' button asks \nyou to choose the period and generates \nthe report in txt file.");
        alert.showAndWait();
    }
}