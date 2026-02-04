package com.example.tidsrejseagenturet;

import database.TimePeriodRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.TimePeriod;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

public class ControllerTidsalder {
    private final TimePeriodRepository timeRepo = new TimePeriodRepository();
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
    /// ----------------------------------------------------------------
    private final ObservableList<TimePeriod> timePeriodObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<TimePeriod> timePeriodTableView;
    @FXML
    private TableColumn<TimePeriod, Integer> timeperiodIdTableColumn;
    @FXML
    private TableColumn<TimePeriod, String> timePeriodNameColumn;
    @FXML
    private TableColumn<TimePeriod, String> timePeriodDescriptionColumn;

    @FXML
    public void initialize() throws SQLException{
        populateTimePeriodList();
        populateTimePeriodColumns();

    }

    public void populateTimePeriodList() throws SQLException {
        timePeriodObservableList.addAll(timeRepo.initializeTimeperiod());
        timePeriodTableView.setItems(timePeriodObservableList);
    }
    public void populateTimePeriodColumns(){
        timeperiodIdTableColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        timePeriodNameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        timePeriodDescriptionColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescription()));
    }


}
