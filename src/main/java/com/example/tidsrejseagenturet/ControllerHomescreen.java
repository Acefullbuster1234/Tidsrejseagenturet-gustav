package com.example.tidsrejseagenturet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.BookingRow;
import model.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerHomescreen {

    // ---------------- Scene Navigator ----------------
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
    // -------------------------------------------------

    private final ObservableList<BookingRow.PersonRow> rows = FXCollections.observableArrayList();

    @FXML private TableView<BookingRow.PersonRow> tableView;

    @FXML private TableColumn<BookingRow.PersonRow, Number> columnID;
    @FXML private TableColumn<BookingRow.PersonRow, String> columnName;
    @FXML private TableColumn<BookingRow.PersonRow, String> columnEmail;
    @FXML private TableColumn<BookingRow.PersonRow, Void> columnBook;

    private final List<Integer> idList = new ArrayList<>();
    private final List<String> nameList = new ArrayList<>();
    private final List<String> emailList = new ArrayList<>();

    @FXML
    public void initialize(){
        showUserTable();
    }

    public void showUserTable() {
        tableView.setItems(rows);


        columnID.setCellValueFactory(cd -> cd.getValue().idProperty());
        columnName.setCellValueFactory(cd -> cd.getValue().nameProperty());
        columnEmail.setCellValueFactory(cd -> cd.getValue().emailProperty());


        columnBook.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Book");

            {
                btn.setOnAction(e -> {
                    BookingRow.PersonRow row = getTableView().getItems().get(getIndex());
                    int id = row.getId();
                    String name = row.getName();
                    String email = row.getEmail();
                    Customer bookingCustomer = new Customer(id, name, email);
                    try {
                        SceneNavigator.switchTo(e, "Booking.fxml", (ControllerBooking c) -> {
                            c.setCustomer(bookingCustomer);
                        });
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        rows.clear();

        idList.clear(); nameList.clear(); emailList.clear();

        idList.add(1); idList.add(2); idList.add(3);
        nameList.add("Admin"); nameList.add("Jonas"); nameList.add("Tidsalder");
        emailList.add("hej@gmail.com"); emailList.add("med@gmail.com"); emailList.add("dig@gmail.com");

        int n = Math.min(idList.size(), Math.min(nameList.size(), emailList.size()));
        for (int i = 0; i < n; i++) {
            rows.add(new BookingRow.PersonRow(idList.get(i), nameList.get(i), emailList.get(i)));
        }
    }
}
