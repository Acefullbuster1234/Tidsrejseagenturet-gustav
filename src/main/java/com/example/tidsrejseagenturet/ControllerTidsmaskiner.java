package com.example.tidsrejseagenturet;

import database.TimemachineRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Timemachine;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerTidsmaskiner {
    private final TimemachineRepository tmRepo = new TimemachineRepository();
    /// ----------------------------------------------------------------
    ///  Scene Navigator
    @FXML
    public void onClickAdminPanel(ActionEvent event) throws IOException {
        SceneNavigator.switchTo(event, "AdminPanel.fxml");
    }
    @FXML
    public void onClickTidsmaskiner(ActionEvent event) throws IOException {
        SceneNavigator.switchTo(event, "Tidsmaskiner.fxml");
    }
    @FXML
    public void onClickTidsalder(ActionEvent event) throws IOException {
        SceneNavigator.switchTo(event, "Tidsalder.fxml");
    }
    @FXML
    public void onClickGuides(ActionEvent event) throws IOException {
        SceneNavigator.switchTo(event, "Guides.fxml");
    }
    @FXML
    public void onClickBooking(ActionEvent event) throws IOException {
        SceneNavigator.switchTo(event, "Booking.fxml");
    }
    @FXML
    public void onClickCreateTimeMachine(ActionEvent event) throws IOException{
        SceneNavigator.switchTo(event,"AddTimeMachine.fxml");
    }
    /// ----------------------------------------------------------------
    private final ObservableList<Timemachine> timemachineObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView<Timemachine> timemachineTableView;
    @FXML
    private TableColumn<Timemachine, Integer> timemachineIdTableColumn;
    @FXML
    private TableColumn<Timemachine, String> timemachineNameColumn;
    @FXML
    private TableColumn<Timemachine, Integer> timemachineCapacityColumn;
    @FXML
    private TableColumn<Timemachine, String> timemachineStatusColumn;

    @FXML
    public void initialize() throws SQLException {
        handlePopulateTimeMachineList();
        handlePopulateTimeMachineColumns();
    }
    public void handlePopulateTimeMachineList() throws SQLException {
        timemachineObservableList.addAll(tmRepo.initializeTimeMachine());
        timemachineTableView.setItems(timemachineObservableList);
    }
    public void handlePopulateTimeMachineColumns(){
        timemachineIdTableColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        timemachineNameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        timemachineCapacityColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCapacity()).asObject());
        timemachineStatusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
    }

}
