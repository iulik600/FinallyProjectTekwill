module org.example.finallyprojecttekwill {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens org.example.finallyprojecttekwill to javafx.fxml;
    exports org.example.finallyprojecttekwill;

   // opens org.example.finallyprojecttekwill.images to javafx.fxml;
}