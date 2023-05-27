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

import java.io.IOException;
import java.util.Optional;

public class ManufacturerController {
    private DatabaseConnector connection = new DatabaseConnector();
    @FXML
    private TableView<Manufacturer> manufacturerTable;
    @FXML
    private TableColumn<Manufacturer, Integer> id;
    @FXML
    private TableColumn<Manufacturer, String> name;
    @FXML
    private TableColumn<Manufacturer, String> country;
    @FXML
    private TableColumn<Manufacturer, String> phone;
    @FXML
    private TableColumn<Manufacturer, String> email;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
    }
    public void updateView() {
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        manufacturerTable.setItems(viewHandler.getConnection().retrieveManufacturer());
    }
    public void handleMainLoad(ActionEvent e) {
        viewHandler.changeScene(ViewHandler.MAIN_PAGE_SCENE);
    }
    public void handleChangeName(ActionEvent e) {
        try{
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Manufacturer ID");
            dialog.setHeaderText("Type the ID of manufacturer you want to change name");
            dialog.setContentText("ID:");
            dialog.showAndWait();
            int input = Integer.parseInt(String.valueOf(dialog.getEditor().getText()));
            TextInputDialog dialog1 = new TextInputDialog();
            dialog1.setTitle("Change name");
            dialog1.setHeaderText("Type the new name of manufacturer:");
            dialog1.setContentText("Change name:");
            dialog1.showAndWait();
            String name = String.valueOf(dialog1.getEditor().getText());
            if (name.equalsIgnoreCase(""))
            {
                throw new Exception("You have to enter a name");
            }
            if (input == 0)
            {
                throw new Exception("ID = 0 does not exist");
            }
            viewHandler.getConnection().UpdateManufacturersName(name, input);
            viewHandler.changeScene(ViewHandler.MANUFACTURER);
        }catch (Exception l)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText(l.getMessage());
            alert.showAndWait();
        }


    }

    public void handleChangeCountry(ActionEvent e) {
        try{
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Manufacturer ID");
            dialog.setHeaderText("Type the ID of manufacturer you want to change country");
            dialog.setContentText("ID:");
            dialog.showAndWait();
            int input = Integer.parseInt(String.valueOf(dialog.getEditor().getText()));
            if (input < 1 || input > 4)
            {
                throw new Exception("Given ID does not exist");
            }
            TextInputDialog dialog1 = new TextInputDialog();
            dialog1.setTitle("Change country");
            dialog1.setHeaderText("Type the new country of manufacturer:");
            dialog1.setContentText("Change country");
            dialog1.showAndWait();
            String country = String.valueOf(dialog1.getEditor().getText());
            if(country.equalsIgnoreCase(""))
            {
                throw new Exception("You have to enter the name of the country");
            }
            viewHandler.getConnection().UpdateManufacturersCountry(country, input);
            viewHandler.changeScene(ViewHandler.MANUFACTURER);
        }catch (Exception l)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText(l.getMessage());
            alert.showAndWait();
        }

    }
    public void handleChangePhone(ActionEvent e) {
        try{
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Manufacturer ID");
            dialog.setHeaderText("Type the ID of manufacturer you want to change phone number");
            dialog.setContentText("ID:");
            dialog.showAndWait();
            int input1 = Integer.parseInt(String.valueOf(dialog.getEditor().getText()));
            if (input1 < 1 || input1 > 4)
            {
                throw new Exception("Given ID does not exist");
            }
            TextInputDialog dialog1 = new TextInputDialog();
            dialog1.setTitle("Change phone number");
            dialog1.setHeaderText("Type the new phone number of manufacturer:");
            dialog1.setContentText("Phone number:");
            dialog1.showAndWait();
            String phone1 = String.valueOf(dialog1.getEditor().getText());
            if (phone1.equalsIgnoreCase(""))
            {
                throw new Exception("You have to enter phone number");
            }
            viewHandler.getConnection().UpdateManufacturersPhone(phone1, input1);
            viewHandler.changeScene(ViewHandler.MANUFACTURER);
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
    public void handleChangeEmail(ActionEvent e) {
        try{
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Manufacturer ID");
            dialog.setHeaderText("Type the ID of manufacturer you want to change e-mail");
            dialog.setContentText("ID:");
            dialog.showAndWait();
            int input = Integer.parseInt(String.valueOf(dialog.getEditor().getText()));
            if (input < 1 || input > 4)
            {
                throw new Exception("Given ID does not exist");
            }
            TextInputDialog dialog1 = new TextInputDialog();
            dialog1.setTitle("Change e-mail");
            dialog1.setHeaderText("Type the new e-mail of manufacturer:");
            dialog1.setContentText("Change e-mail");
            dialog1.showAndWait();
            String email = String.valueOf(dialog1.getEditor().getText());
            if (email.equalsIgnoreCase(""))
            {
                throw new Exception("You have to enter e-mail");
            }
            viewHandler.getConnection().UpdateManufacturersEmail(email, input);
            viewHandler.changeScene(ViewHandler.MANUFACTURER);
        }catch (Exception l)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText(l.getMessage());
            alert.showAndWait();
        }

    }

    public void handleSearchByID(ActionEvent e) {
        try{
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Manufacturer's ID");
            dialog.setHeaderText("Search by following ID:");
            dialog.setContentText("ID value:");
            dialog.showAndWait();
            int input1 = Integer.parseInt(String.valueOf(dialog.getEditor().getText()));
            if (input1 < 1 || input1 > 4)
            {
                throw new Exception("Given ID does not exist");
            }
            this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            country.setCellValueFactory(new PropertyValueFactory<>("country"));
            phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));

            manufacturerTable.setItems(viewHandler.getConnection().searchByID(input1));
        }catch (Exception l)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText(l.getMessage());
            alert.showAndWait();
        }

    }
    public void handleSearchByName(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Manufacturer's name");
        dialog.setHeaderText("Search by following name:");
        dialog.setContentText("Name:");
        dialog.showAndWait();
        String name = String.valueOf(dialog.getEditor().getText());
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        manufacturerTable.setItems(viewHandler.getConnection().searchByName(name));
    }
    public void handleSearchByCountry(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Manufacturer's country");
        dialog.setHeaderText("Search by following country:");
        dialog.setContentText("Country:");
        dialog.showAndWait();
        String country = String.valueOf(dialog.getEditor().getText());
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.country.setCellValueFactory(new PropertyValueFactory<>("country"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        manufacturerTable.setItems(viewHandler.getConnection().searchByCountry(country));
    }
    public void handleSearchByPhone(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Manufacturer's phone number");
        dialog.setHeaderText("Search by following phone number:");
        dialog.setContentText("Phone number:");
        dialog.showAndWait();
        String phone = String.valueOf(dialog.getEditor().getText());
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.country.setCellValueFactory(new PropertyValueFactory<>("country"));
        this.phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        manufacturerTable.setItems(viewHandler.getConnection().searchByPhone(phone));
    }
    public void handleSearchByEmail(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Manufacturer's e-mail");
        dialog.setHeaderText("Search by following e-mail:");
        dialog.setContentText("E-mail:");
        dialog.showAndWait();
        String email = String.valueOf(dialog.getEditor().getText());
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.country.setCellValueFactory(new PropertyValueFactory<>("country"));
        this.phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        this.email.setCellValueFactory(new PropertyValueFactory<>("email"));

        manufacturerTable.setItems(viewHandler.getConnection().searchByEmail(email));
    }
    public void displayManufacturerHistory(ActionEvent e){
        viewHandler.changeScene(ViewHandler.MANUFACTURER_HISTORY);
    }
}
