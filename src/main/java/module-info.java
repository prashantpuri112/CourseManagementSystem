module com.example.coursemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens prashantpuri_2059631.coursemanagementsystem to javafx.fxml;
    opens prashantpuri_2059631.coursemanagementsystem.user_interface.instructor_frontend to javafx.base;
    exports prashantpuri_2059631.coursemanagementsystem;
    exports prashantpuri_2059631.coursemanagementsystem.structure;
    opens prashantpuri_2059631.coursemanagementsystem.structure to javafx.fxml;
}