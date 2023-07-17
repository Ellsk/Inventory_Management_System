module com.example.invenotory_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.invenotory_management_system to javafx.fxml;
    exports com.example.invenotory_management_system;
}