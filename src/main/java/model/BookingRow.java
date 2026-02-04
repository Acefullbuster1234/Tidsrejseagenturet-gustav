package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookingRow {

    public static class PersonRow {
        private final IntegerProperty id = new SimpleIntegerProperty();
        private final StringProperty name = new SimpleStringProperty();
        private final StringProperty email = new SimpleStringProperty();

        public PersonRow(int id, String name, String email) {
            this.id.set(id);
            this.name.set(name);
            this.email.set(email);
        }

        public IntegerProperty idProperty() { return id; }
        public StringProperty nameProperty() { return name; }
        public StringProperty emailProperty() { return email; }

        public int getId() { return id.get(); }
        public String getName() { return name.get(); }
        public String getEmail() { return email.get(); }
    }

}
