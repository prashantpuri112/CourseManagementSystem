package prashantpuri_2059631.coursemanagementsystem.user_interface.admin_frontend;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import prashantpuri_2059631.coursemanagementsystem.Main;
import prashantpuri_2059631.coursemanagementsystem.structure.Admin;
import prashantpuri_2059631.coursemanagementsystem.structure.Module;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleActivities {
    public static Scene addModule(Stage stage, Scene prevScene) {
        stage.setTitle("Add Module");
        GridPane gridPane = new GridPane();
        Text text = new Text("Add Module");
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);
        gridPane.add(text, 0, 0);
        Scene scene = new Scene(gridPane, 600, 300);
        scene.getStylesheets().add(Main.class.getResource("common.css").toExternalForm());
        // fields for input
        Label moduleCodeLabel = new Label("Module Code");
        Label moduleNameLabel = new Label("Module Name");
        Label moduleLevelLabel = new Label("Module Level");
        Label moduleLeaderLabel = new Label("Module Leader");
        Label courseCodeLabel = new Label("Course Code");
        Label isOptionalLabel = new Label("Is Optional");
        TextField moduleCodeField = new TextField();
        TextField moduleNameField = new TextField();
        TextField moduleLeaderField = new TextField();
        TextField courseCodeField = new TextField();
        TextField moduleLevelField = new TextField();
        CheckBox isOptionalField = new CheckBox();
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
        gridPane.add(moduleLevelLabel, 0, 3);
        gridPane.add(moduleLevelField, 1, 3);
        gridPane.add(moduleLeaderLabel, 0, 4);
        gridPane.add(moduleLeaderField, 1, 4);
        gridPane.add(courseCodeLabel, 0, 5);
        gridPane.add(courseCodeField, 1, 5);
        gridPane.add(isOptionalLabel, 0, 6);
        gridPane.add(isOptionalField, 1, 6);
        gridPane.add(addModuleButton, 1, 7);
        gridPane.add(goBack, 0, 7);

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
                int moduleLevel = Integer.parseInt(moduleLevelField.getText());
                String moduleLeader = moduleLeaderField.getText();
                String courseCode = courseCodeField.getText();
                boolean isOptional = isOptionalField.isSelected();
                if(Admin.checkInstructor(moduleLeader) && Admin.checkCourseCode(courseCode)) {
                    Module module = new Module(moduleCode, moduleName, moduleLevel, moduleLeader, courseCode, isOptional);
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
        stage.setTitle("Edit Module");
        GridPane gridPane = new GridPane();
        Text text = new Text("Edit Module");
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);
        gridPane.add(text, 0, 0);
        Scene scene = new Scene(gridPane, 300, 300);
        // search fields
        TextField searchField = new TextField();
        searchField.setPromptText("Enter Module Code");
        Button searchButton = new Button("Search");
        // fields for input

        Label moduleNameLabel = new Label("Module Name");
        Label moduleLevelLabel = new Label("Module Level");
        Label moduleLeaderLabel = new Label("Module Leader");
        Label courseCodeLabel = new Label("Course Code");
        Label isOptionalLabel = new Label("Is Optional");
        TextField moduleNameField = new TextField();
        TextField moduleLevelField = new TextField();
        TextField moduleLeaderField = new TextField();
        TextField courseCodeField = new TextField();
        CheckBox isOptionalField = new CheckBox();
        moduleNameField.setPromptText("Enter Module Name");
        moduleLeaderField.setPromptText("Enter Module Leader");
        courseCodeField.setPromptText("Enter Course Code");
        Button editModuleButton = new Button("Edit Module");
        Button goBack = new Button("Go Back");
        gridPane.add(searchField, 0, 1);
        gridPane.add(searchButton, 1, 1);
        gridPane.add(moduleNameLabel, 0, 2);
        gridPane.add(moduleNameField, 1, 2);
        gridPane.add(moduleLevelLabel, 0, 3);
        gridPane.add(moduleLevelField, 1, 3);
        gridPane.add(moduleLeaderLabel, 0, 4);
        gridPane.add(moduleLeaderField, 1, 4);
        gridPane.add(courseCodeLabel, 0, 5);
        gridPane.add(courseCodeField, 1, 5);
        gridPane.add(isOptionalLabel, 0, 6);
        gridPane.add(isOptionalField, 1, 6);
        gridPane.add(editModuleButton, 0, 7);
        gridPane.add(goBack, 1, 7);

        goBack.setOnAction(e -> {
            stage.setScene(prevScene);
        });
        ArrayList<String> currentModule = new ArrayList<>();
        searchButton.setOnAction(e -> {
            currentModule.clear();
            currentModule.add(searchField.getText());
            HashMap<String, String> moduleDetail = Admin.getModuleDetail(currentModule.get(0));
            moduleNameField.setText(moduleDetail.get("moduleName"));
            moduleLevelField.setText(moduleDetail.get("moduleLevel"));
            moduleLeaderField.setText(moduleDetail.get("moduleLeader"));
            System.out.println(Boolean.parseBoolean(moduleDetail.get("isOptional")));
            courseCodeField.setText(moduleDetail.get("courseCode"));
            isOptionalField.setSelected(Boolean.parseBoolean(moduleDetail.get("isOptional")));
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
                int moduleLevel = Integer.parseInt(moduleLevelField.getText());
                String moduleLeader = moduleLeaderField.getText();
                String courseCode = courseCodeField.getText();
                boolean isOptional = isOptionalField.isSelected();
                if(Admin.checkInstructor(moduleLeader) && Admin.checkCourseCode(courseCode)) {
                    Module module = new Module(currentModule.get(0), moduleName, moduleLevel, moduleLeader, courseCode, isOptional);
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
    public static Scene viewAllModule(Stage stage, Scene prevScene) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);
        Scene scene = new Scene(gridPane);
        stage.setTitle("View All Modules");
        Text title = new Text("View All Modules");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridPane.add(title, 0, 0);
        Text moduleCodeLabel = new Text("Module Code");
        Text moduleNameLabel = new Text("Module Name");
        Text moduleLeaderLabel = new Text("Module Leader");
        Text courseCodeLabel = new Text("Course Code");
        Text moduleLevelLabel = new Text("Module Level");
        Text isOptionalLabel = new Text("Is Optional");
        gridPane.add(moduleCodeLabel, 0, 1);
        gridPane.add(moduleNameLabel, 1, 1);
        gridPane.add(moduleLeaderLabel, 2, 1);
        gridPane.add(courseCodeLabel, 3, 1);
        gridPane.add(moduleLevelLabel, 4, 1);
        gridPane.add(isOptionalLabel, 5, 1);


        ArrayList<HashMap<String, String>> moduleList = Admin.getAllModules();

        for (int i = 0; i < moduleList.size(); i++) {
            Text moduleCode = new Text(moduleList.get(i).get("moduleCode"));
            Text moduleName = new Text(moduleList.get(i).get("moduleName"));
            Text moduleLeader = new Text(moduleList.get(i).get("moduleLeader"));
            Text courseCode = new Text(moduleList.get(i).get("courseCode"));
            Text moduleLevel = new Text(moduleList.get(i).get("moduleLevel"));
            Text isOptional = new Text(moduleList.get(i).get("isOptional"));
            gridPane.add(moduleCode, 0, i+2);
            gridPane.add(moduleName, 1, i+2);
            gridPane.add(moduleLeader, 2, i+2);
            gridPane.add(courseCode, 3, i+2);
            gridPane.add(moduleLevel, 4, i+2);
            gridPane.add(isOptional, 5, i+2);
        }
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            stage.setScene(prevScene);
        });
        gridPane.add(backButton, 0, moduleList.size()+2);

        return scene;
    }
}
