package com.example.tidsrejseagenturet;

import database.CustomerRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerAddUser {
    private final CustomerRepository customerRepo = new CustomerRepository();
    /// ----------------------------------------------------------------
    ///  Scene Navigator
    @FXML
    public void onClickAdminPanel (ActionEvent event) throws IOException {
        SceneNavigator.switchTo(event, "AdminPanel.fxml");
    }
    @FXML
    public void onClickTidsmaskiner (ActionEvent event) throws IOException {
        SceneNavigator.switchTo(event, "Tidsmaskiner.fxml");
    }
    @FXML
    public void onClickTidsalder (ActionEvent event) throws IOException {
        SceneNavigator.switchTo(event, "Tidsalder.fxml");
    }

    @FXML
    public void onClickGuides (ActionEvent event) throws IOException {
        SceneNavigator.switchTo(event, "Guides.fxmll");
    }

    @FXML
    public void onClickHomescreen (ActionEvent event) throws IOException {
        SceneNavigator.switchTo(event, "Homescreen.fxml");
    }
    /// ----------------------------------------------------------------

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField customerEmailField;

    @FXML
    public void onClickCreateUser(ActionEvent event) throws IOException, SQLException {
        String customerName = customerNameField.getText();
        String customerEmail = customerEmailField.getText();

        if(!customerName.isEmpty() || !customerEmail.isEmpty()){
            customerRepo.saveCustomer(customerName,customerEmail);
            onClickAdminPanel(event);
        }
        else {
            upDateHUD();
        }
        customerNameField.clear();
        customerEmailField.clear();

    }
    public void upDateHUD(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error!");
        alert.setHeaderText("Field left empty");
        alert.setContentText("You need to fill out both fields");
        alert.showAndWait();
    }
}
