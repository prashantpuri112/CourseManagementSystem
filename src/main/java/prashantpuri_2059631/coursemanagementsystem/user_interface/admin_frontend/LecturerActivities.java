package prashantpuri_2059631.coursemanagementsystem.user_interface.admin_frontend;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import prashantpuri_2059631.coursemanagementsystem.structure.Admin;
import prashantpuri_2059631.coursemanagementsystem.structure.Instructor;
import prashantpuri_2059631.coursemanagementsystem.structure.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class LecturerActivities {
    public static Scene addInstructor(Stage stage, Scene prevScene) {
        GridPane grid_pane = new GridPane();
        Scene scene = new Scene(grid_pane,300,300);
        Text text = new Text("Add Lecturer");
        grid_pane.add(text,0,0);

        // fields
        Label name = new Label("Name");
        TextField name_field = new TextField();
        name.setLabelFor(name_field);
        name_field.setPromptText("Enter Name");

        Label email = new Label("Email");
        TextField email_field = new TextField();
        email.setLabelFor(email_field);
        email_field.setPromptText("Enter Email");

        Label phone = new Label("Phone");
        TextField phone_field = new TextField();
        phone.setLabelFor(phone_field);
        phone_field.setPromptText("Enter Phone");

        Label username = new Label("Username");
        TextField username_field = new TextField();
        username.setLabelFor(username_field);
        username_field.setPromptText("Enter Username");

        Label password = new Label("Password");
        TextField password_field = new TextField();
        password.setLabelFor(password_field);
        password_field.setPromptText("Enter Password");

        Label address = new Label("Address");
        TextField address_field = new TextField();
        address.setLabelFor(address_field);
        address_field.setPromptText("Enter Address");

        Button add_button = new Button("Add");
        Button back_button = new Button("Back");
        back_button.setOnAction(e->{
            stage.setScene(prevScene);
                });
        add_button.setOnAction(e->{
                    Instructor instructor = new Instructor(name_field.getText(),email_field.getText(),password_field.getText(),phone_field.getText(),address_field.getText(),username_field.getText());
                    if(Admin.addInstructor(instructor)){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("Success");
                        alert.setContentText("Instructor added successfully");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error");
                        alert.setContentText("Instructor not added");
                        alert.showAndWait();
                    }
        });
        grid_pane.add(name,0,1);
        grid_pane.add(name_field, 1, 1);
        grid_pane.add(email, 0, 2);
        grid_pane.add(email_field, 1, 2);
        grid_pane.add(phone, 0, 3);
        grid_pane.add(phone_field, 1, 3);
        grid_pane.add(username, 0, 4);
        grid_pane.add(username_field, 1, 4);
        grid_pane.add(password, 0, 5);
        grid_pane.add(password_field, 1, 5);
        grid_pane.add(address, 0, 6);
        grid_pane.add(address_field, 1, 6);
        grid_pane.add(add_button, 1, 7);
        grid_pane.add(back_button, 0, 7);

        return scene;
    }
    public static Scene editInstructor(Stage stage, Scene prevScene) {
        GridPane grid_pane = new GridPane();
        Scene scene = new Scene(grid_pane,300,300);
        Text text = new Text("Edit Lecturer");
        grid_pane.add(text,0,0);

        // searching option for lecturer
        TextField search_field = new TextField();
        search_field.setPromptText("Enter lecturer username");
        Button search_button = new Button("Search");
        grid_pane.add(search_field,0,1);
        grid_pane.add(search_button,1,1);

        // fields
        Label name = new Label("Name");
        TextField name_field = new TextField();
        name.setLabelFor(name_field);
        name_field.setPromptText("Enter Name");

        Label email = new Label("Email");
        TextField email_field = new TextField();
        email.setLabelFor(email_field);
        email_field.setPromptText("Enter Email");

        Label password = new Label("Password");
        TextField password_field = new TextField();
        password.setLabelFor(password_field);
        password_field.setPromptText("Enter Password");

        Label phone = new Label("Phone");
        TextField phone_field = new TextField();
        phone.setLabelFor(phone_field);
        phone_field.setPromptText("Enter Phone");

        Label address = new Label("Address");
        TextField address_field = new TextField();
        address.setLabelFor(address_field);
        address_field.setPromptText("Enter Address");

        Button edit_button = new Button("Edit");
        Button back_button = new Button("Back");
        back_button.setOnAction(e->{
            stage.setScene(prevScene);
                });
        ArrayList<String> searched_username = new ArrayList<>();
        search_button.setOnAction(e->{
            searched_username.clear();
            searched_username.add(search_field.getText());
            HashMap<String,String> details = Admin.getLecturerDetails(search_field.getText());
            name_field.setText(details.get("name"));
            email_field.setText(details.get("email"));
            phone_field.setText(details.get("phone"));
            address_field.setText(details.get("address"));
            password_field.setText(details.get("password"));

                });
        edit_button.setOnAction(e->{
            User user = new User(name_field.getText(),email_field.getText(),password_field.getText(), phone_field.getText(), address_field.getText(), searched_username.get(0), "instructor");
            if(Admin.editUser(user)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Lecturer edited successfully");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Lecturer not edited");
                alert.showAndWait();
            }
        });
        grid_pane.add(name,0,2);
        grid_pane.add(name_field,1,2);
        grid_pane.add(email,0,3);
        grid_pane.add(email_field,1,3);
        grid_pane.add(password,0,4);
        grid_pane.add(password_field,1,4);
        grid_pane.add(phone,0,5);
        grid_pane.add(phone_field,1,5);
        grid_pane.add(address,0,6);
        grid_pane.add(address_field,1,6);
        grid_pane.add(edit_button,1,7);
        grid_pane.add(back_button,0,7);


        return scene;
    }
}
