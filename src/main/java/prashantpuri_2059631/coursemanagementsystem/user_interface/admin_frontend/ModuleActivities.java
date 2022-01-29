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
import prashantpuri_2059631.coursemanagementsystem.structure.Module;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleActivities {
    public static Scene addModule(Stage stage, Scene prevScene) {
        GridPane gridPane = new GridPane();
        Text text = new Text("Add Module");
        gridPane.add(text, 0, 0);
        Scene scene = new Scene(gridPane, 300, 300);

        // fields for input
        Label moduleCodeLabel = new Label("Module Code");
        Label moduleNameLabel = new Label("Module Name");
        Label moduleLeaderLabel = new Label("Module Leader");
        Label courseCodeLabel = new Label("Course Code");
        TextField moduleCodeField = new TextField();
        TextField moduleNameField = new TextField();
        TextField moduleLeaderField = new TextField();
        TextField courseCodeField = new TextField();
        moduleCodeField.setPromptText("Enter Module Code");
        moduleNameField.setPromptText("Enter Module Name");
        moduleLeaderField.setPromptText("Enter Module Leader");
        courseCodeField.setPromptText("Enter Course Code");
        Button addModuleButton = new Button("Add Module");
        Button goBack = new Button("Go Back");
        gridPane.add(moduleCodeLabel, 0, 1);
        gridPane.add(moduleCodeField, 1, 1);
        gridPane.add(moduleNameLabel, 0, 2);
        gridPane.add(moduleNameField, 1, 2);
        gridPane.add(moduleLeaderLabel, 0, 3);
        gridPane.add(moduleLeaderField, 1, 3);
        gridPane.add(courseCodeLabel, 0, 4);
        gridPane.add(courseCodeField, 1, 4);
        gridPane.add(addModuleButton, 1, 5);
        gridPane.add(goBack, 0, 5);
        goBack.setOnAction(e -> {
            stage.setScene(prevScene);
                });
        addModuleButton.setOnAction(e -> {
            if(moduleCodeField.getText().isEmpty() || moduleNameField.getText().isEmpty() || moduleLeaderField.getText().isEmpty() || courseCodeField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Please fill all the fields");
                alert.showAndWait();
            } else {
                String moduleCode = moduleCodeField.getText();
                String moduleName = moduleNameField.getText();
                String moduleLeader = moduleLeaderField.getText();
                String courseCode = courseCodeField.getText();
                if(Admin.checkInstructor(moduleLeader) && Admin.checkCourseCode(courseCode)) {
                    Module module = new Module(moduleCode, moduleName, moduleLeader, courseCode);
                        System.out.println("Entered");
                    if(Admin.addModule(module)){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("Success");
                        alert.setContentText("Module added successfully");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error");
                        alert.setContentText("Cannot add module");
                        alert.showAndWait();
                    }
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("Module or instructor does not exists");
                    alert.showAndWait();
                }
            }
        });
        return scene;
    }
    public static Scene editModule(Stage stage, Scene prevScene) {
        GridPane gridPane = new GridPane();
        Text text = new Text("Edit Module");
        gridPane.add(text, 0, 0);
        Scene scene = new Scene(gridPane, 300, 300);
        // search fields
        TextField searchField = new TextField();
        searchField.setPromptText("Enter Module Code");
        Button searchButton = new Button("Search");
        // fields for input

        Label moduleNameLabel = new Label("Module Name");
        Label moduleLeaderLabel = new Label("Module Leader");
        Label courseCodeLabel = new Label("Course Code");
        TextField moduleNameField = new TextField();
        TextField moduleLeaderField = new TextField();
        TextField courseCodeField = new TextField();
        moduleNameField.setPromptText("Enter Module Name");
        moduleLeaderField.setPromptText("Enter Module Leader");
        courseCodeField.setPromptText("Enter Course Code");
        Button editModuleButton = new Button("Add Module");
        Button goBack = new Button("Go Back");
        gridPane.add(searchField, 0, 1);
        gridPane.add(searchButton, 1, 1);
        gridPane.add(moduleNameLabel, 0, 2);
        gridPane.add(moduleNameField, 1, 2);
        gridPane.add(moduleLeaderLabel, 0, 3);
        gridPane.add(moduleLeaderField, 1, 3);
        gridPane.add(courseCodeLabel, 0, 4);
        gridPane.add(courseCodeField, 1, 4);
        gridPane.add(editModuleButton, 1, 5);
        gridPane.add(goBack, 0, 5);
        goBack.setOnAction(e -> {
            stage.setScene(prevScene);
        });
        ArrayList<String> currentModule = new ArrayList<>();
        searchButton.setOnAction(e -> {
            currentModule.clear();
            currentModule.add(searchField.getText());
            HashMap<String, String> moduleDetail = Admin.getModuleDetail(currentModule.get(0));
            moduleNameField.setText(moduleDetail.get("moduleName"));
            moduleLeaderField.setText(moduleDetail.get("moduleLeader"));
            courseCodeField.setText(moduleDetail.get("courseCode"));

        });
        editModuleButton.setOnAction(e -> {
            if(moduleNameField.getText().isEmpty() || moduleLeaderField.getText().isEmpty() || courseCodeField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Please fill all the fields");
                alert.showAndWait();
            } else {
                String moduleName = moduleNameField.getText();
                String moduleLeader = moduleLeaderField.getText();
                String courseCode = courseCodeField.getText();
                if(Admin.checkInstructor(moduleLeader) && Admin.checkCourseCode(courseCode)) {
                    Module module = new Module(currentModule.get(0), moduleName, moduleLeader, courseCode);
                    if(Admin.editModule(module)){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("Success");
                        alert.setContentText("Module edited successfully");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error");
                        alert.setContentText("Cannot edit module");
                        alert.showAndWait();
                    }
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("Module or instructor does not exists");
                    alert.showAndWait();
                }
            }
        });
        return scene;
    }

}
