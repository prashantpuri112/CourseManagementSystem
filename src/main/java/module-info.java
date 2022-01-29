module com.example.coursemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens prashantpuri_2059631.coursemanagementsystem to javafx.fxml;
    exports prashantpuri_2059631.coursemanagementsystem;
}