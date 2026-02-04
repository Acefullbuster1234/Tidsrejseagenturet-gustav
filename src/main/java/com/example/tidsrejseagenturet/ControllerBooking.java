package com.example.tidsrejseagenturet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import service.BookingService;

import java.io.IOException;

public class ControllerBooking {

    private Customer customer;

    private int timemachineID;
    private int timePeriodID;
    private int guideID;

    private final BookingService bookingService = new BookingService();

    @FXML private Label bookingTitle;
    @FXML private Label chosenTidsmaskine;
    @FXML private Label chosenTidsalder;
    @FXML private Label chosenGuide;

    @FXML private MenuButton menuTidsmaskine;
    @FXML private MenuButton menuTidsalder;
    @FXML private MenuButton menuGuide;

    @FXML
    private void initialize() {
        loadMenus();
    }
    private void loadMenus() {

        menuGuide.getItems().clear();
        menuTidsmaskine.getItems().clear();
        menuTidsalder.getItems().clear();

        for (Guide guide : bookingService.getGuides()) {
            MenuItem item = new MenuItem(guide.getName());
            item.setOnAction(e -> {
                chosenGuide.setText(guide.getName());
                guideID = guide.getId();
            });
            menuGuide.getItems().add(item);
        }

        for (Timemachine tm : bookingService.getAvailableTimeMachines()) {
            MenuItem item = new MenuItem(tm.getName());
            item.setOnAction(e -> {
                chosenTidsmaskine.setText(tm.getName());
                timemachineID = tm.getId();
            });
            menuTidsmaskine.getItems().add(item);
        }

        for (TimePeriod tp : bookingService.getTimePeriods()) {
            MenuItem item = new MenuItem(tp.getName());
            item.setOnAction(e -> {
                chosenTidsalder.setText(tp.getName());
                timePeriodID = tp.getId();
            });
            menuTidsalder.getItems().add(item);
        }
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
        bookingTitle.setText("Booking for " + customer.getName());
    }

    @FXML
    public void onClickBack(ActionEvent event) throws IOException {
        SceneNavigator.switchTo(event, "Homescreen.fxml");
    }

    @FXML
    public void onClickBooking () {
        try {
            bookingService.CreateBooking(
                    customer.getId(),
                    timemachineID,
                    timePeriodID,
                    guideID
            );
            showInfo(
                    "New Booking",
                    "Booking created for: " + customer.getName()
            );
        } catch (IllegalStateException e) {
            showError(e.getMessage());
        }
    }
    private void showInfo(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
