package view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ManufacturerHistory;

import java.sql.Time;
import java.util.Date;

public class ManufacturerHistoryController
{
  @FXML
  private TableView<ManufacturerHistory> manufacturerHistoryTable;
  @FXML
  private TableColumn<ManufacturerHistory, Integer> id;
  @FXML
  private TableColumn<ManufacturerHistory, Date> date;
  @FXML
  private TableColumn<ManufacturerHistory, Time> time;
  @FXML
  private TableColumn<ManufacturerHistory, Integer> manufacturer_id;
  @FXML
  private TableColumn<ManufacturerHistory, String> name;
  @FXML
  private TableColumn<ManufacturerHistory, String> country;
  @FXML
  private TableColumn<ManufacturerHistory, String> phone;
  @FXML
  private TableColumn<ManufacturerHistory, String> email;
  private ViewHandler viewHandler;
  public void init(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
  }
  public void updateView() {
    //		System.out.println("view.AverageSpeedsController Update View called");
    this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
    date.setCellValueFactory(new PropertyValueFactory<>("date"));
    time.setCellValueFactory(new PropertyValueFactory<>("time"));
    manufacturer_id.setCellValueFactory(new PropertyValueFactory<>("manufacturer_id"));
    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    country.setCellValueFactory(new PropertyValueFactory<>("country"));
    phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));

    manufacturerHistoryTable.setItems(viewHandler.getConnection().retrieveManufacturerHistory());
  }
  public void handleGoBack(ActionEvent e) {
    viewHandler.changeScene(ViewHandler.MANUFACTURER);
  }
}
