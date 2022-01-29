package prashantpuri_2059631.coursemanagementsystem.user_interface;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import prashantpuri_2059631.coursemanagementsystem.user_interface.admin_frontend.AdminPanel;

public class Login{
    public static Scene loginScene(Stage primaryStage){
        primaryStage.setTitle("Login");
        GridPane loginPanel = new GridPane();
        Scene login_scene = new Scene(loginPanel, 400, 450);
        TextField user_id = new TextField();
        TextField passwd = new TextField();
        Label user_id_label = new Label("User ID");
        Label passwd_label = new Label("Password");
        user_id_label.setLabelFor(user_id);
        passwd_label.setLabelFor(passwd);
        Button login_button = new Button("Login");
        Button register_button = new Button("Register");
        login_button.setOnAction(e -> {
            primaryStage.close();
            AdminPanel.adminDashboard();
                });
        register_button.setOnAction(e -> {
           primaryStage.setScene(Register.registerScene(primaryStage));
        });

        loginPanel.add(user_id_label, 0, 0);
        loginPanel.add(user_id, 1, 0);
        loginPanel.add(passwd_label, 0, 1);
        loginPanel.add(passwd, 1, 1);
        loginPanel.add(login_button, 0, 2);
        loginPanel.add(register_button, 1, 2);
        loginPanel.setHgap(10);
        loginPanel.setVgap(10);
        return login_scene;
    }

}
