module UI {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;
    requires com.fasterxml.jackson.databind;


    opens UI to javafx.fxml;
    exports UI;
    opens Artist to javafx.fxml;
    exports Artist;
}