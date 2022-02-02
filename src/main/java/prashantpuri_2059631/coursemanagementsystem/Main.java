package prashantpuri_2059631.coursemanagementsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import prashantpuri_2059631.coursemanagementsystem.user_interface.Login;
import prashantpuri_2059631.coursemanagementsystem.user_interface.Register;
import prashantpuri_2059631.coursemanagementsystem.user_interface.admin_frontend.AdminPanel;
import prashantpuri_2059631.coursemanagementsystem.user_interface.student_frontend.StudentPanel;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
//        StudentPanel.studentView();
//        AdminPanel.adminDashboard();
        // ask user if he is admin or not
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Course Management System");
        alert.setHeaderText("Welcome to Course Management System");
        alert.setContentText("Are you an admin?");
        ButtonType yes = new ButtonType("yes");
        ButtonType no = new ButtonType("no");
        alert.getButtonTypes().setAll(yes, no);
        alert.showAndWait().ifPresent(type -> {
            if (type == yes) {
                stage.close();
                AdminPanel.adminDashboard();
            } else if (type == no) {
                stage.setScene(Login.loginScene(stage));
                stage.show();
            }
                });


    }
}
