package view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextInputDialog;

import javax.swing.*;

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
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Manufacturer ID");
        dialog.setHeaderText("Type the ID of manufacturer you want to change name");
        dialog.setContentText("ID:");
        dialog.showAndWait();
        int input = Integer.parseInt(String.valueOf(dialog.getEditor().getText()));
        TextInputDialog dialog1 = new TextInputDialog();
        dialog1.setTitle("Change name");
        dialog1.setHeaderText("Type the new name of manufacturer:");
        dialog1.setContentText("Change name");
        dialog1.showAndWait();
        String name = String.valueOf(dialog1.getEditor().getText());
        viewHandler.getConnection().UpdateManufacturersName(name, input);
        viewHandler.changeScene(ViewHandler.MANUFACTURER);
    }
    public void handleAddManufacturer(ActionEvent e){
        String name = JOptionPane.showInputDialog(null, "Enter the name of the new manufacturer:", "Enter a name", JOptionPane.QUESTION_MESSAGE);
        String country = JOptionPane.showInputDialog(null, "Enter the country of the new manufacturer:", "Enter country", JOptionPane.QUESTION_MESSAGE);
        String phone = JOptionPane.showInputDialog(null, "Enter the phone number of the new manufacturer:", "Enter phone number", JOptionPane.QUESTION_MESSAGE);
        String email = JOptionPane.showInputDialog(null, "Enter the e-mail of the new manufacturer:", "Enter e-mail", JOptionPane.QUESTION_MESSAGE);
        Manufacturer manufacturer = new Manufacturer(name, country, phone, email);
        viewHandler.getConnection().addManufacturer(manufacturer);
        viewHandler.changeScene(ViewHandler.MANUFACTURER);
    }
    public void handleChangeCountry(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Manufacturer ID");
        dialog.setHeaderText("Type the ID of manufacturer you want to change country");
        dialog.setContentText("ID:");
        dialog.showAndWait();
        int input = Integer.parseInt(String.valueOf(dialog.getEditor().getText()));
        TextInputDialog dialog1 = new TextInputDialog();
        dialog1.setTitle("Change country");
        dialog1.setHeaderText("Type the new country of manufacturer:");
        dialog1.setContentText("Change country");
        dialog1.showAndWait();
        String country = String.valueOf(dialog1.getEditor().getText());
        viewHandler.getConnection().UpdateManufacturersCountry(country, input);
        viewHandler.changeScene(ViewHandler.MANUFACTURER);
    }
    public void handleChangePhone(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Manufacturer ID");
        dialog.setHeaderText("Type the ID of manufacturer you want to change phone number");
        dialog.setContentText("ID:");
        dialog.showAndWait();
        int input1 = Integer.parseInt(String.valueOf(dialog.getEditor().getText()));
        TextInputDialog dialog1 = new TextInputDialog();
        dialog1.setTitle("Change phone number");
        dialog1.setHeaderText("Type the new phone number of manufacturer:");
        dialog1.setContentText("Phone number:");
        dialog1.showAndWait();
        String phone1 = String.valueOf(dialog1.getEditor().getText());
        viewHandler.getConnection().UpdateManufacturersPhone(phone1, input1);
        viewHandler.changeScene(ViewHandler.MANUFACTURER);
    }
    public void handleChangeEmail(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Manufacturer ID");
        dialog.setHeaderText("Type the ID of manufacturer you want to change e-mail");
        dialog.setContentText("ID:");
        dialog.showAndWait();
        int input = Integer.parseInt(String.valueOf(dialog.getEditor().getText()));
        TextInputDialog dialog1 = new TextInputDialog();
        dialog1.setTitle("Change e-mail");
        dialog1.setHeaderText("Type the new e-mail of manufacturer:");
        dialog1.setContentText("Change e-mail");
        dialog1.showAndWait();
        String email = String.valueOf(dialog1.getEditor().getText());
        viewHandler.getConnection().UpdateManufacturersEmail(email, input);
        viewHandler.changeScene(ViewHandler.MANUFACTURER);
    }
    public void handleDeleteManufacturer(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Manufacturer ID");
        dialog.setHeaderText("Type the ID of manufacturer you want to delete");
        dialog.setContentText("ID:");
        dialog.showAndWait();
        int input = Integer.parseInt(String.valueOf(dialog.getEditor().getText()));
        viewHandler.getConnection().deleteManufacturer(input);
        viewHandler.changeScene(ViewHandler.MANUFACTURER);
    }
    public void handleSearchByID(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Manufacturer's ID");
        dialog.setHeaderText("Search by following ID:");
        dialog.setContentText("ID value:");
        dialog.showAndWait();
        int input1 = Integer.parseInt(String.valueOf(dialog.getEditor().getText()));
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        manufacturerTable.setItems(viewHandler.getConnection().searchByID(input1));
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
