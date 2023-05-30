package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Manufacturer;
import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ManufacturerController
{
  //private DatabaseConnector connection = new DatabaseConnector();
  @FXML private TableView<Manufacturer> manufacturerTable;
  @FXML private TableColumn<Manufacturer, Integer> id;
  @FXML private TableColumn<Manufacturer, String> name;
  @FXML private TableColumn<Manufacturer, String> country;
  @FXML private TableColumn<Manufacturer, String> phone;
  @FXML private TableColumn<Manufacturer, String> email;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
    //        this.connection = new DatabaseConnector();
    //        connection.connect("balarama.db.elephantsql.com", 5432, "oodjpfdu", "OVr7iGfVftIao0lHjvnXthVvIDXdturJ");
  }

  public void updateView()
  {
    this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    country.setCellValueFactory(new PropertyValueFactory<>("country"));
    phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));

    manufacturerTable.setItems(
        viewHandler.getConnection().retrieveManufacturer());
  }

  public void handleMainLoad(ActionEvent e)
  {
    viewHandler.changeScene(ViewHandler.MAIN_PAGE_SCENE);
  }

  public void handleChangeName(ActionEvent e)
  {
    try
    {
      TextInputDialog dialog = new TextInputDialog();
      dialog.setTitle("Manufacturer ID");
      dialog.setHeaderText(
          "Type the ID of manufacturer you want to change name");
      dialog.setContentText("ID:");
      Optional<String> result = dialog.showAndWait();

      if (result.isPresent())
      {
        int input = Integer.parseInt(
            String.valueOf(dialog.getEditor().getText()));
        TextInputDialog dialog1 = new TextInputDialog();
        dialog1.setTitle("Change name");
        dialog1.setHeaderText("Type the new name of manufacturer:");
        dialog1.setContentText("Change name:");
        Optional<String> result1 = dialog1.showAndWait();

        if (result1.isPresent())
        {
          String name = String.valueOf(dialog1.getEditor().getText());
          if (name.equalsIgnoreCase(""))
          {
            throw new Exception("You have to enter name");
          }
          if (input == 0)
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText("ID = 0 does not exist");
            alert.showAndWait();
          }
          viewHandler.getConnection().UpdateManufacturersName(name, input);
          viewHandler.changeScene(ViewHandler.MANUFACTURER);
        }
      }
    }
    catch (Exception l)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setAlertType(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Something went wrong");
      alert.setContentText(l.getMessage());
      alert.showAndWait();
    }

  }

  public void handleChangeCountry(ActionEvent e)
  {
    try
    {
      TextInputDialog dialog = new TextInputDialog();
      dialog.setTitle("Manufacturer ID");
      dialog.setHeaderText(
          "Type the ID of manufacturer you want to change country");
      dialog.setContentText("ID:");
      Optional<String> result = dialog.showAndWait();

      if (result.isPresent())
      {
        int input = Integer.parseInt(
            String.valueOf(dialog.getEditor().getText()));
        if (input < 1 || input > 4)
        {
          throw new Exception("Given ID does not exist");
        }
        TextInputDialog dialog1 = new TextInputDialog();
        dialog1.setTitle("Change country");
        dialog1.setHeaderText("Type the new country of manufacturer:");
        dialog1.setContentText("Change country");
        Optional<String> result1 = dialog1.showAndWait();

        if (result1.isPresent())
        {
          String country = String.valueOf(dialog1.getEditor().getText());
          if (country.equalsIgnoreCase(""))
          {
            throw new Exception("You have to enter the name of the country");
          }
          viewHandler.getConnection()
              .UpdateManufacturersCountry(country, input);
          viewHandler.changeScene(ViewHandler.MANUFACTURER);
        }
      }
    }
    catch (Exception l)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setAlertType(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Something went wrong");
      alert.setContentText(l.getMessage());
      alert.showAndWait();
    }

  }

  public void handleChangePhone(ActionEvent e)
  {
    try
    {
      TextInputDialog dialog = new TextInputDialog();
      dialog.setTitle("Manufacturer ID");
      dialog.setHeaderText(
          "Type the ID of manufacturer you want to change phone");
      dialog.setContentText("ID:");
      Optional<String> result = dialog.showAndWait();

      if (result.isPresent())
      {
        int input = Integer.parseInt(result.get());

        if (input < 1 || input > 4)
        {
          throw new Exception("Given ID does not exist");
        }

        TextInputDialog dialog1 = new TextInputDialog();
        dialog1.setTitle("Change phone number");
        dialog1.setHeaderText("Type the new phone number of manufacturer:");
        dialog1.setContentText("Change phone number");
        Optional<String> phoneResult = dialog1.showAndWait();

        if (phoneResult.isPresent())
        {
          String phone = phoneResult.get();

          if (phone.equalsIgnoreCase(""))
          {
            throw new Exception("You have to enter phone number");
          }

          String response = viewHandler.getConnection()
              .UpdateManufacturersPhone(phone, input);

          if (response.equalsIgnoreCase(
              "Error trying to update model.Manufacturer table"))
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText(
                "Phone number has to start either with '+' or '00'.");
            alert.showAndWait();
          }

          //                viewHandler.getConnection().UpdateManufacturersPhone(phone, input);
          viewHandler.changeScene(ViewHandler.MANUFACTURER);
          // viewHandler.getConnection().close();
        }
      }
    }
    catch (Exception l)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setAlertType(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Something went wrong");
      alert.setContentText(l.getMessage());
      alert.showAndWait();
    }
  }

  public void handleChangeEmail(ActionEvent e)
  {
    try
    {
      TextInputDialog dialog = new TextInputDialog();
      dialog.setTitle("Manufacturer ID");
      dialog.setHeaderText(
          "Type the ID of manufacturer you want to change e-mail");
      dialog.setContentText("ID:");
      Optional<String> result = dialog.showAndWait();

      if (result.isPresent())
      {
        int input = Integer.parseInt(result.get());

        if (input < 1 || input > 4)
        {
          throw new Exception("Given ID does not exist");
        }

        TextInputDialog dialog1 = new TextInputDialog();
        dialog1.setTitle("Change e-mail");
        dialog1.setHeaderText("Type the new e-mail of manufacturer:");
        dialog1.setContentText("Change e-mail");
        Optional<String> emailResult = dialog1.showAndWait();

        if (emailResult.isPresent())
        {
          String email = emailResult.get();

          if (email.equalsIgnoreCase(""))
          {
            throw new Exception("You have to enter e-mail");
          }

          String response = viewHandler.getConnection()
              .UpdateManufacturersEmail(email, input);

          if (response.equalsIgnoreCase(
              "Error trying to update model.Manufacturer table"))
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText("E-mail has to contain '@' sign.");
            alert.showAndWait();
          }

          //                viewHandler.getConnection().UpdateManufacturersEmail(email, input);
          viewHandler.changeScene(ViewHandler.MANUFACTURER);
          // viewHandler.getConnection().close();
        }
      }
    }
    catch (Exception l)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setAlertType(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Something went wrong");
      alert.setContentText(l.getMessage());
      alert.showAndWait();
    }
  }

  public void handleSearchByID(ActionEvent e)
  {
    try
    {
      TextInputDialog dialog = new TextInputDialog();
      dialog.setTitle("Manufacturer's ID");
      dialog.setHeaderText("Search by following ID:");
      dialog.setContentText("ID value:");
      Optional<String> result = dialog.showAndWait();

      if (result.isPresent())
      {
        int input1 = Integer.parseInt(
            String.valueOf(dialog.getEditor().getText()));
        if (input1 < 1 || input1 > 4)
        {
          throw new Exception("Given ID does not exist");
        }
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        manufacturerTable.setItems(
            viewHandler.getConnection().searchByID(input1));
      }
    }
    catch (Exception l)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setAlertType(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Something went wrong");
      alert.setContentText("You forgot to enter ID.");
      alert.showAndWait();
    }

  }

  public void handleSearchByName(ActionEvent e)
  {
    try
    {
      TextInputDialog dialog = new TextInputDialog();
      dialog.setTitle("Manufacturer's name");
      dialog.setHeaderText("Search by following name:");
      dialog.setContentText("Name:");
      Optional<String> result = dialog.showAndWait();

      if (result.isPresent())
      {
        String name = String.valueOf(dialog.getEditor().getText());
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        manufacturerTable.setItems(
            viewHandler.getConnection().searchByName(name));
      }
    }
    catch (Exception l)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setAlertType(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Something went wrong");
      alert.setContentText("You forgot to enter name.");
      alert.showAndWait();
    }
  }

  public void handleSearchByCountry(ActionEvent e)
  {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Manufacturer's country");
    dialog.setHeaderText("Search by following country:");
    dialog.setContentText("Country:");
    Optional<String> result = dialog.showAndWait();

    if (result.isPresent())
    {
      String country = String.valueOf(dialog.getEditor().getText());
      this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
      this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
      this.country.setCellValueFactory(new PropertyValueFactory<>("country"));
      phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
      email.setCellValueFactory(new PropertyValueFactory<>("email"));

      manufacturerTable.setItems(
          viewHandler.getConnection().searchByCountry(country));
    }
  }

  public void handleSearchByPhone(ActionEvent e)
  {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Manufacturer's phone number");
    dialog.setHeaderText("Search by following phone number:");
    dialog.setContentText("Phone number:");
    Optional<String> result = dialog.showAndWait();

    if (result.isPresent())
    {
      String phone = String.valueOf(dialog.getEditor().getText());
      this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
      this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
      this.country.setCellValueFactory(new PropertyValueFactory<>("country"));
      this.phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
      email.setCellValueFactory(new PropertyValueFactory<>("email"));

      manufacturerTable.setItems(
          viewHandler.getConnection().searchByPhone(phone));
    }
  }

  public void handleSearchByEmail(ActionEvent e)
  {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Manufacturer's e-mail");
    dialog.setHeaderText("Search by following e-mail:");
    dialog.setContentText("E-mail:");
    Optional<String> result = dialog.showAndWait();

    if (result.isPresent())
    {
      String email = String.valueOf(dialog.getEditor().getText());
      this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
      this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
      this.country.setCellValueFactory(new PropertyValueFactory<>("country"));
      this.phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
      this.email.setCellValueFactory(new PropertyValueFactory<>("email"));

      manufacturerTable.setItems(
          viewHandler.getConnection().searchByEmail(email));
    }
  }

  public void displayManufacturerHistory(ActionEvent e)
  {
    viewHandler.changeScene(ViewHandler.MANUFACTURER_HISTORY);
  }
}
