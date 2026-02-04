package com.example.tidsrejseagenturet;

import database.CustomerRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Customer;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerAdminPanel {

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
        SceneNavigator.switchTo(event, "Guides.fxml");
    }
    @FXML
    public void onClickHomeScreen(ActionEvent event) throws IOException{
        SceneNavigator.switchTo(event,"Homescreen.fxml");
    }
    @FXML
    public void onClickAddUser(ActionEvent event) throws IOException{
        SceneNavigator.switchTo(event, "Adduser.fxml");
    }
    @FXML
    public void onClickDeleteCustomer() throws SQLException {
        Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        if(selectedCustomer != null){
            customerObservableList.remove(selectedCustomer);
            populateList();
        }
    }
    @FXML
    public void onClickEditCustomer(){
        Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        if(selectedCustomer != null){

        }
    }
    /// ----------------------------------------------------------------

    private final ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableColumn<Customer, Integer> idTableView;

    @FXML
    private TableColumn<Customer, String> nameTableView;

    @FXML
    private TableColumn<Customer, String> emailTableView;


    @FXML
    public void initialize() throws SQLException {
        populateList();
        handleCustomerTable();
    }
    public void handleCustomerTable(){
        idTableView.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        nameTableView.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        emailTableView.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
    }
    public void populateList() throws SQLException {
        customerObservableList.addAll(customerRepo.initializeCustomer());
        customerTableView.setItems(customerObservableList);
    }
}
