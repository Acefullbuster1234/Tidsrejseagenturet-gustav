package com.example.tidsrejseagenturet;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.*;

public final class  SceneNavigator {

    // Standardbredde og -højde for alle scener i vinduet.
    private static final int DEFAULT_WIDTH = 900;
    private static final int DEFAULT_HEIGHT = 600;

    // Privat constructor for at forhindre, at klassen instantieres (kun statiske metoder).
    private SceneNavigator() {}

    // Simpel version af sceneskift:
    // - Loader FXML'en
    // - Skifter scene i det nuværende Stage
    public static void switchTo(ActionEvent event, String fxmlName) throws IOException {
        // Loader FXML-ressourcen ud fra filnavnet, relativt til Main-klassen.
        FXMLLoader loader = new FXMLLoader(
                Objects.requireNonNull(Main.class.getResource(fxmlName))
        );
        Parent root = loader.load();

        // Finder det nuværende Stage (vindue) ud fra den knap/Node, der udløste eventet.
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        // Opretter en ny Scene med det loadede UI og viser den på Stage.
        Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    // Generisk version af sceneskift:
    // - Loader FXML
    // - Henter controlleren
    // - Lader kalderen initialisere controlleren via en lambda (fx sætte Inventory/Shop)
    // - Skifter scene i det nuværende Stage
    public static <T> void switchTo(
            ActionEvent event,
            String fxmlName,
            Consumer<T> controllerInitializer) throws IOException {

        // Loader FXML-filen for den nye scene.
        FXMLLoader loader = new FXMLLoader(
                Objects.requireNonNull(Main.class.getResource(fxmlName)));
        Parent root = loader.load();

        // Henter controller-objektet, som er knyttet til FXML'en.
        T controller = loader.getController();

        // Giver kalderen mulighed for at initialisere controlleren
        // (fx controller.setInventory(inventory), controller.setShop(shop)).
        controllerInitializer.accept(controller);

        // Finder det nuværende Stage (vindue) fra den Node, der triggere eventet.
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        // Opretter en ny Scene med det loadede UI og viser den på Stage.
        Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}