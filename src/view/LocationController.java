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
import java.text.DecimalFormat;

public class LocationController
{

  private static final DecimalFormat df = new DecimalFormat("0.00");
  private static final DecimalFormat df1 = new DecimalFormat("0.0000");
  @FXML
  private Button button;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
  }
  public void seePV(ActionEvent e){
    Button clickedButton = (Button)e.getSource();
    int row = GridPane.getRowIndex(clickedButton);
    int column = GridPane.getColumnIndex(clickedButton);
    Location location = new Location(row, column);
    PVPanels pvPanels = viewHandler.getConnection().retrievePVPanel(location);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Panel's data");
    alert.setHeaderText("Performance data for the selected panel");
    alert.setContentText("Date:" + pvPanels.getDate() + "\n" +
        "Time:" + pvPanels.getTime() + "\n" +
        "Panel id:" + pvPanels.getPanel_id() + "\n" +
        "Voltage: " + df.format(pvPanels.getVoltage()) + "\n" +
        "Current: " + df.format(pvPanels.getCurrent()) + "\n" +
        "Solar Flux: " + pvPanels.getSolar_flux() + "\n" +
        "Power Output: " + df.format(pvPanels.getPower_out()) + "\n" +
        "Efficiency: " + df1.format(pvPanels.getEfficiency()));
    alert.showAndWait();
  }
  public void seeTH(ActionEvent e){
    Button clickedButton = (Button)e.getSource();
    int row = GridPane.getRowIndex(clickedButton);
    int column = GridPane.getColumnIndex(clickedButton);
    Location location = new Location(row, column);
    THPanels thPanels = viewHandler.getConnection().retrieveTHPanel(location);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Panel's data");
    alert.setHeaderText("Performance data for the selected panel");
    alert.setContentText("Date:" + thPanels.getDate() + "\n" +
        "Time:" + thPanels.getTime() + "\n" +
        "Panel id:" + thPanels.getPanel_id() + "\n" +
        "Ambient temperature: " + df.format(thPanels.getA_temperature()) + "\n" +
        "Water in temperature: " + df.format(thPanels.getWater_in_temp()) + "\n" +
        "Water out temperature: " + df.format(thPanels.getWater_out_temp()) + "\n" +
        "Efficiency: " + df.format(thPanels.getEfficiency()));
    alert.showAndWait();
  }
  public void handleGoBack(ActionEvent e) {
    viewHandler.changeScene(ViewHandler.PANELS);
  }

}
