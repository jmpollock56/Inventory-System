module com.example.c482 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jpollock.c482 to javafx.fxml;
    exports com.jpollock.c482;
}