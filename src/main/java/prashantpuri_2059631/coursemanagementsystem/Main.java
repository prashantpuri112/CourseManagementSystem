package prashantpuri_2059631.coursemanagementsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import prashantpuri_2059631.coursemanagementsystem.user_interface.Login;
import prashantpuri_2059631.coursemanagementsystem.user_interface.Register;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        stage.setScene(Login.loginScene(stage));
        stage.show();

    }
}
