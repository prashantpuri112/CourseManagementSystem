package prashantpuri_2059631.coursemanagementsystem.user_interface;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import prashantpuri_2059631.coursemanagementsystem.Main;
import prashantpuri_2059631.coursemanagementsystem.structure.Student;

import java.sql.SQLException;

public class Register {
    public static Scene registerScene(Stage primaryStage) {
        primaryStage.setTitle("Register");
        GridPane registerPanel = new GridPane();
        Scene registerScene = new Scene(registerPanel, 400, 400);
        registerPanel.setPadding(new Insets(10));
        registerPanel.setVgap(10);
        registerPanel.setHgap(10);
        TextField name = new TextField();
        TextField email = new TextField();
        TextField password = new TextField();
        TextField phone = new TextField();
        TextField address = new TextField();
        TextField user_name = new TextField();
        ChoiceBox choiceBox = new ChoiceBox();
        registerScene.getStylesheets().add(Main.class.getResource("login.css").toExternalForm());
        choiceBox.getItems().addAll(Student.getAllCourseCodes());
        Label nameLabel = new Label("Name");
        Label emailLabel = new Label("Email");
        Label passwordLabel = new Label("Password");
        Label phoneLabel = new Label("Phone");
        Label addressLabel = new Label("Address");
        Label user_idLabel = new Label("User Name");
        Label courseLabel = new Label("Course");
        Image herald_logo = new Image(Main.class.getResource("img.png").toExternalForm());
        ImageView logo = new ImageView(herald_logo);
        logo.setFitWidth(200);
        logo.setPreserveRatio(true);
        registerPanel.add(logo, 0, 0, 2, 1);
        registerPanel.add(nameLabel, 0, 1);
        registerPanel.add(name, 1, 1);
        registerPanel.add(emailLabel, 0, 2);
        registerPanel.add(email, 1, 2);
        registerPanel.add(passwordLabel, 0, 3);
        registerPanel.add(password, 1, 3);
        registerPanel.add(phoneLabel, 0, 4);
        registerPanel.add(phone, 1, 4);
        registerPanel.add(addressLabel, 0, 5);
        registerPanel.add(address, 1, 5);
        registerPanel.add(user_idLabel, 0, 6);
        registerPanel.add(user_name, 1, 6);
        registerPanel.add(courseLabel, 0, 7);
        registerPanel.add(choiceBox, 1, 7);
        Button registerButton = new Button("Previous");
        registerButton.setOnAction(e -> {
            primaryStage.setScene(Login.loginScene(primaryStage));
        });
        registerPanel.add(registerButton, 0, 8);

        Button registerButton1 = new Button("Register");
        registerPanel.add(registerButton1, 1, 8);
       registerButton1.setOnAction(e -> {
           Student student = new Student(name.getText(), email.getText(), password.getText(), phone.getText(), address.getText(), user_name.getText());
           try {
               student.register();
               student.registerCourse(choiceBox.getValue().toString());
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Success");
               alert.setHeaderText("Registration Successful");
               alert.setContentText("You can now login");
               alert.showAndWait();
           } catch (SQLException ex) {
               ex.printStackTrace();
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Registration Failed");
               alert.setContentText("Please try again");
               alert.showAndWait();
           }

       });

        return registerScene;
    }
}
