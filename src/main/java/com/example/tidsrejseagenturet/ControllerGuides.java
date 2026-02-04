package com.example.tidsrejseagenturet;

import database.GuideRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Guide;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerGuides {
    private final GuideRepository guideRepo = new GuideRepository();
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
        SceneNavigator.switchTo(event, "Guides.fxml");
    }
    @FXML
    public void onClickBooking(ActionEvent event) throws IOException {
        SceneNavigator.switchTo(event, "Booking.fxml");
    }
    /// ----------------------------------------------------------------

    @FXML
    private TableView<Guide> guideTableView;

    @FXML
    private TableColumn<Guide, Integer> guideIdTableColumn;

    @FXML
    private TableColumn<Guide, String> guideNameTableColumn;

    @FXML
    private TableColumn<Guide, String> guideSpecialityColumn;

    private final ObservableList<Guide> guideObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException {
        handlePopulateGuideList();
        handlePopulateTableView();
    }

    public void handlePopulateGuideList() throws SQLException {
        guideObservableList.addAll(guideRepo.initializeGuide());
        guideTableView.setItems(guideObservableList);
    }
    public void handlePopulateTableView(){
        guideIdTableColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        guideNameTableColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        guideSpecialityColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSpeciality()));
    }
}
