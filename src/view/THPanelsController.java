package view;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.DateInterval;
import model.PVPanels;
import model.THPanels;
import javafx.fxml.FXML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
//    public void handleFiltering() {
//        Dialog<DateInterval> dialog = new Dialog<>();
//        dialog.setTitle("Select Date Interval");
//        dialog.setHeaderText("Please enter the initial and final dates to check performance.");
//
//        // Create the content layout
//        GridPane gridPane = new GridPane();
//        gridPane.setHgap(10);
//        gridPane.setVgap(10);
//        gridPane.setPadding(new Insets(20, 150, 10, 10));
//
//        // Create the date pickers
//        DatePicker initialDatePicker = new DatePicker();
//        DatePicker finalDatePicker = new DatePicker();
//
//        // Add the components to the layout
//        gridPane.add(new javafx.scene.control.Label("Initial Date:"), 0, 0);
//        gridPane.add(initialDatePicker, 1, 0);
//        gridPane.add(new javafx.scene.control.Label("Final Date:"), 0, 1);
//        gridPane.add(finalDatePicker, 1, 1);
//
//        // Set the content of the dialog
//        dialog.getDialogPane().setContent(gridPane);
//
//        // Set the buttons
//        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//
//        dialog.showAndWait();
//        //		System.out.println("view.AverageSpeedsController Update View called");
//        date.setCellValueFactory(new PropertyValueFactory<>("date"));
//        time.setCellValueFactory(new PropertyValueFactory<>("time"));
//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        panel_id.setCellValueFactory(new PropertyValueFactory<>("panel_id"));
//        a_temperature.setCellValueFactory(new PropertyValueFactory<>("a_temperature"));
//        water_in_temp.setCellValueFactory(new PropertyValueFactory<>("water_in_temp"));
//        water_out_temp.setCellValueFactory(new PropertyValueFactory<>("water_out_temp"));
//        efficiency.setCellValueFactory(new PropertyValueFactory<>("efficiency"));
//        thPanelsTable.setItems(viewHandler.getConnection().filterTHByDate(initialDatePicker.getValue(),
//            finalDatePicker.getValue()));
//    }
public void handleFiltering() {
    Dialog<ButtonType> dialog = new Dialog<>();
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
    initialDatePicker.setValue(LocalDate.now());
    finalDatePicker.setValue(LocalDate.now());

    // Add the components to the layout
    gridPane.add(new Label("Initial Date:"), 0, 0);
    gridPane.add(initialDatePicker, 1, 0);
    gridPane.add(new Label("Final Date:"), 0, 1);
    gridPane.add(finalDatePicker, 1, 1);

    // Set the content of the dialog
    dialog.getDialogPane().setContent(gridPane);

    // Set the buttons
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    Optional<ButtonType> result = dialog.showAndWait();

    if (result.isPresent() && result.get() == ButtonType.OK)
    {
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

        // Filter the data and get the filtered list
        ObservableList<THPanels> filteredData = viewHandler.getConnection()
            .filterTHByDate(initialDatePicker.getValue(), finalDatePicker.getValue());
        savePerformanceDataAsReport(filteredData);
    }

    // Prepare the report content
//    StringBuilder reportBuilder = new StringBuilder();
//    reportBuilder.append("Filtered Performance Data Report\n\n");
//
//    for (THPanels performanceDataTH : filteredData) {
//        reportBuilder.append("Date: ").append(performanceDataTH.getDate()).append("\n");
//        reportBuilder.append("Time: ").append(performanceDataTH.getTime()).append("\n");
//        reportBuilder.append("ID: ").append(performanceDataTH.getId()).append("\n");
//        reportBuilder.append("Panel ID: ").append(performanceDataTH.getPanel_id()).append("\n");
//        reportBuilder.append("A_temperature: ").append(performanceDataTH.getA_temperature()).append("\n");
//        reportBuilder.append("Water_in_temp: ").append(performanceDataTH.getWater_in_temp()).append("\n");
//        reportBuilder.append("Water_out_temp: ").append(performanceDataTH.getWater_out_temp()).append("\n");
//        reportBuilder.append("Efficiency: ").append(performanceDataTH.getEfficiency()).append("\n");
//        reportBuilder.append("\n");
//    }
//
//    // Define the file name and location
//    String fileName = "filtered_performance_report.txt";
//
//    String filePath = "C:\\Users\\Wojtek Z Petworld\\Desktop\\jpjp\\" + fileName;
//
//    try {
//        // Create the file and write the report content
//        File file = new File(filePath);
//        FileWriter writer = new FileWriter(file);
//        writer.write(reportBuilder.toString());
//        writer.close();
//
//        System.out.println("Filtered performance data report saved successfully.");
//
//    } catch (IOException e) {
//        System.out.println("Error occurred while saving the filtered performance data report.");
//        e.printStackTrace();
//    }
}
    public void savePerformanceDataAsReport(List<THPanels> performanceDataList) {
        // Prepare the report content
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Performance Data Report\n\n");

        for (THPanels performanceDataTH : performanceDataList) {
            reportBuilder.append("Date: ").append(performanceDataTH.getDate()).append("\n");
            reportBuilder.append("Time: ").append(performanceDataTH.getTime()).append("\n");
            reportBuilder.append("Measure id: ").append(performanceDataTH.getId()).append("\n");
            reportBuilder.append("Panel id: ").append(performanceDataTH.getId()).append("\n");
            reportBuilder.append("A_temperature: ").append(performanceDataTH.getA_temperature()).append("\n");
            reportBuilder.append("Water_in_temp: ").append(performanceDataTH.getWater_in_temp()).append("\n");
            reportBuilder.append("Water_out_temp: ").append(performanceDataTH.getWater_out_temp()).append("\n");
            reportBuilder.append("Efficiency: ").append(performanceDataTH.getEfficiency()).append("\n");
            reportBuilder.append("\n");
        }

        // Define the file name and location
        String fileName = "Thermal panels performance report from " + performanceDataList.get(0).getDate() + " to " + performanceDataList.get(performanceDataList.size()-1).getDate() + ".txt";
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
    public void refresh(){
        viewHandler.changeScene(ViewHandler.TH_PANELS);
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

