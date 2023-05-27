package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.Location;
import model.PVPanels;
import javafx.event.ActionEvent;
import model.THPanels;

import java.awt.*;

public class LocationController
{
  @FXML
  private Button button;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
  }
  public void updateView(ActionEvent e){
    Button clickedButton = (Button)e.getSource();
    int row = GridPane.getRowIndex(clickedButton);
    int column = GridPane.getColumnIndex(clickedButton);
    Location location = new Location(row, column);
    PVPanels pvPanels = viewHandler.getConnection().retrievePVPanel(location);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Performance Data");
    alert.setHeaderText("Performance data for the selected date interval");
    alert.setContentText("Date:" + pvPanels.getDate() + "\n" +
        "Time:" + pvPanels.getTime() + "\n" +
        "Panel id:" + pvPanels.getPanel_id() + "\n" +
        "Voltage: " + pvPanels.getVoltage() + "\n" +
        "Current: " + pvPanels.getCurrent() + "\n" +
        "Solar Flux: " + pvPanels.getSolar_flux() + "\n" +
        "Power Output: " + pvPanels.getPower_out() + "\n" +
        "Efficiency: " + pvPanels.getEfficiency());
    alert.showAndWait();
  }
  public void seeTH(ActionEvent e){
    Button clickedButton = (Button)e.getSource();
    int row = GridPane.getRowIndex(clickedButton);
    int column = GridPane.getColumnIndex(clickedButton);
    Location location = new Location(row, column);
    THPanels thPanels = viewHandler.getConnection().retrieveTHPanel(location);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Performance Data");
    alert.setHeaderText("Performance data for the selected date interval");
    alert.setContentText("Date:" + thPanels.getDate() + "\n" +
        "Time:" + thPanels.getTime() + "\n" +
        "Panel id:" + thPanels.getPanel_id() + "\n" +
        "Ambient temperature: " + thPanels.getA_temperature() + "\n" +
        "Water in temperature: " +thPanels.getWater_in_temp() + "\n" +
        "Water out temperature: " + thPanels.getWater_out_temp() + "\n" +
        "Efficiency: " + thPanels.getEfficiency());
    alert.showAndWait();
  }

}
