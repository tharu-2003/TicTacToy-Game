module com.ijse.gdse72.tictactoy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ijse.gdse72.tictactoy.controller to javafx.fxml;
    exports com.ijse.gdse72.tictactoy;
}