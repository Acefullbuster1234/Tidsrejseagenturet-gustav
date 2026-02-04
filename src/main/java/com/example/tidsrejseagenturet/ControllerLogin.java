package com.example.tidsrejseagenturet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerLogin {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    ///  Login metode fra Login skærm.
    /// On click -> Gyldig login -> Skift skærm.
    /// On CLick -> Ikke gyldig login -> Fejl.
    @FXML
    public void onLoginClicked (ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("a") && password.equals("a")){
            SceneNavigator.switchTo(event, "Homescreen.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("Invalid Credentials");
            alert.setContentText("Username or password is incorrect. Please try again");
            alert.showAndWait();
        }
        updateHUD();
    }

    public void updateHUD() {
        usernameField.clear();
        passwordField.clear();
    }
}
