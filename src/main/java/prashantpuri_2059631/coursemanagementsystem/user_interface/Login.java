package prashantpuri_2059631.coursemanagementsystem.user_interface;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import prashantpuri_2059631.coursemanagementsystem.Main;
import prashantpuri_2059631.coursemanagementsystem.structure.User;
import prashantpuri_2059631.coursemanagementsystem.user_interface.admin_frontend.AdminPanel;
import prashantpuri_2059631.coursemanagementsystem.user_interface.instructor_frontend.InstructorPanel;
import prashantpuri_2059631.coursemanagementsystem.user_interface.student_frontend.StudentPanel;

public class Login{
    public static Scene loginScene(Stage primaryStage){
        primaryStage.setTitle("Login");
        VBox loginLayout = new VBox();
        HBox loginHeader = new HBox();
        Text welcomeText = new Text("Welcome to Course Management System");
        loginHeader.getChildren().add(welcomeText);
        loginLayout.getChildren().add(loginHeader);
        GridPane loginPanel = new GridPane();
        loginLayout.getChildren().add(loginPanel);
        Scene login_scene = new Scene(loginLayout, 300, 300);
        loginPanel.setPadding(new Insets(20));
        loginPanel.setHgap(10);
        loginPanel.setVgap(10);
        Image herald_logo = new Image(Main.class.getResource("img.png").toExternalForm());
        ImageView logo = new ImageView(herald_logo);
        logo.setFitWidth(200);
        logo.setPreserveRatio(true);
        loginPanel.add(logo, 0, 0, 2, 1);
        login_scene.getStylesheets().add(Main.class.getResource("login.css").toExternalForm());
        String[] roles = {"Student", "Instructor"};
        Label roleLabel = new Label("Role");
        ChoiceBox<String> role = new ChoiceBox<>(FXCollections.observableArrayList(roles));
        TextField user_id = new TextField();
        PasswordField passwd = new PasswordField();
        role.setValue("Student");
        Label user_id_label = new Label("User ID");
        Label passwd_label = new Label("Password");
        user_id_label.setLabelFor(user_id);
        passwd_label.setLabelFor(passwd);
        Button login_button = new Button("Login");
        Button register_button = new Button("Register");
        user_id.setPromptText("Enter your username");
        passwd.setPromptText("Enter your password");
        login_button.setOnAction(e -> {
            if (User.login(user_id.getText(), passwd.getText(), role.getValue())) {
                System.out.println(role.getValue());
                primaryStage.close();
                if (role.getValue().equalsIgnoreCase("student")) {
                    StudentPanel.studentView();
                } else {
                    InstructorPanel.instructorDashboard();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Successful");
                alert.setHeaderText("Welcome " + user_id.getText());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText("Invalid Credentials");
                alert.showAndWait();
            }
                });
        register_button.setOnAction(e -> {
           primaryStage.setScene(Register.registerScene(primaryStage));
        });
        Button admin_button = new Button("Go to Admin");
        loginPanel.add(roleLabel, 0, 1);
        loginPanel.add(role, 1, 1);
        loginPanel.add(user_id_label, 0, 2);
        loginPanel.add(user_id, 1, 2);
        loginPanel.add(passwd_label, 0, 3);
        loginPanel.add(passwd, 1, 3);
        loginPanel.add(login_button, 0, 4);
        loginPanel.add(register_button, 1, 4);
        loginPanel.add(admin_button, 0, 5);
        loginPanel.setHgap(10);
        loginPanel.setVgap(10);
        return login_scene;
    }

}
