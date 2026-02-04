module com.example.tidsrejseagenturet {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires jdk.compiler;
    requires java.desktop;

    opens com.example.tidsrejseagenturet to javafx.fxml;
    exports com.example.tidsrejseagenturet;
}