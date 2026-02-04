package com.example.tidsrejseagenturet;

import database.CustomerRepository;
import database.TimemachineRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerAddTimeMachine {

    private final TimemachineRepository timeMachineRepo = new TimemachineRepository();
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
    private TextField timeMachineNameField;

    @FXML
    private TextField timeMachineCapacityField;

    @FXML
    private TextField timeMachineStatusField;

    @FXML
    public void onClickCreateTimeMachine(ActionEvent event) throws IOException, SQLException {
        String timeMachineName = timeMachineNameField.getText();
        String timeMachineCapacity = timeMachineCapacityField.getText();
        String timeMachineStatus = timeMachineStatusField.getText();

        if(!timeMachineName.isEmpty() || !timeMachineCapacity.isEmpty() || !timeMachineStatus.isEmpty()){
            int tMC = Integer.parseInt(timeMachineCapacity);
            timeMachineRepo.saveTimemachine(timeMachineName, tMC, timeMachineStatus);
            onClickTidsmaskiner(event);
        }
        else {
            upDateHUD();
        }
        timeMachineNameField.clear();
        timeMachineCapacityField.clear();
        timeMachineStatusField.clear();

    }
    public void upDateHUD(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error!");
        alert.setHeaderText("Field left empty");
        alert.setContentText("You need to fill out both fields");
        alert.showAndWait();
    }
}
