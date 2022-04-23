package prashantpuri_2059631.coursemanagementsystem.user_interface.student_frontend;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import prashantpuri_2059631.coursemanagementsystem.Main;
import prashantpuri_2059631.coursemanagementsystem.CurrentStudent;
import prashantpuri_2059631.coursemanagementsystem.user_interface.Login;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentPanel {
    public static void studentView(){
        Stage studentStage = new Stage();
        studentStage.setTitle("Student Panel");
        GridPane studentGrid = new GridPane();
        Scene studentScene = new Scene(studentGrid, 600, 600);
        studentScene.getStylesheets().add(Main.class.getResource("common.css").toExternalForm());
        studentStage.setScene(studentScene);
        studentGrid.setPadding(new Insets(10, 10, 10, 10));
        studentGrid.setVgap(20);
        studentGrid.setHgap(20);
        Text welcomeText = new Text("Welcome, " + CurrentStudent.getCurrentStudent().getName());
        Button enrollModules = new Button("Enroll Modules");
        Button viewReport = new Button("View Report");
        Button viewLecturer = new Button("View Lecturer");
        welcomeText.setStyle("-fx-font-size: 20px;");
        studentGrid.add(welcomeText, 0, 0);
        studentGrid.add(enrollModules, 0, 1);
        studentGrid.add(viewReport, 1, 1);
        studentGrid.add(viewLecturer, 0, 2);


        Button back_to_login_button = new Button("Back to login");
        studentGrid.add(back_to_login_button, 1, 6);
        back_to_login_button.setOnAction(e -> {
            studentStage.close();
            Stage primaryStage = new Stage();
            primaryStage.show();
            primaryStage.setScene(Login.loginScene(primaryStage));
        });




        enrollModules.setOnAction(e -> {
            studentStage.setScene(enrollModulesView(studentStage, studentScene));
                });
        viewReport.setOnAction(e -> {
            studentStage.setScene(viewReportScene(studentStage, studentScene));
                });
        viewLecturer.setOnAction(e -> {
            studentStage.setScene(viewLecturersScene(studentStage, studentScene));
                });
        studentStage.show();
    }

    static Scene enrollModulesView(Stage stage, Scene previousScene){
        GridPane enrollModulesGrid = new GridPane();
        enrollModulesGrid.setPadding(new Insets(10, 10, 10, 10));
        enrollModulesGrid.setVgap(20);
        enrollModulesGrid.setHgap(20);
        Scene enrollModulesScene = new Scene(enrollModulesGrid, 300, 300);
        enrollModulesScene.getStylesheets().add(Main.class.getResource("common.css").toExternalForm());
        ArrayList<HashMap<String, String>> modules = CurrentStudent.getCurrentStudent().getAvailableModules();
        Text enrollTitle = new Text("Enroll Modules");
        enrollTitle.setStyle("-fx-font-size: 20px;");
        enrollModulesGrid.add(enrollTitle, 0, 0);

        ArrayList<String> selectedModules = new ArrayList<>();
        int i = 1;
        for (HashMap<String, String> module : modules){
            Label moduleCodeLabel = new Label(module.get("moduleCode"));
            CheckBox moduleCheckBox = new CheckBox();
            System.out.println(Boolean.parseBoolean(module.get("isOptional")));
            if (!Boolean.parseBoolean(module.get("isOptional"))){
                moduleCheckBox.setDisable(true);
                moduleCheckBox.setSelected(true);
                selectedModules.add(module.get("moduleCode"));
            }
            moduleCheckBox.setOnAction(e -> {
                if (moduleCheckBox.isSelected()){
                    selectedModules.add(module.get("moduleCode"));
                } else {
                    selectedModules.remove(module.get("moduleCode"));
                }
                    });
            enrollModulesGrid.add(moduleCodeLabel, 0, i);
            enrollModulesGrid.add(moduleCheckBox, 1, i);
            i++;
        }
        Button enrollButton = new Button("Enroll");
        enrollButton.setOnAction(e -> {
            if(CurrentStudent.getCurrentStudent().enrollModules(selectedModules)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Enroll Modules");
                alert.setHeaderText("Enroll Modules");
                alert.setContentText("Modules enrolled successfully");
                stage.setScene(previousScene);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Enroll Modules");
                alert.setHeaderText("Enroll Modules");
                alert.setContentText("Modules not enrolled");
                alert.showAndWait();
            }
        });
        if (modules.size() > 0){
            enrollModulesGrid.add(enrollButton, 1, i);
        }
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            stage.setScene(previousScene);
        });
        enrollModulesGrid.add(backButton, 0, i);
        return enrollModulesScene;

    }


    public static Scene viewReportScene(Stage stage, Scene previousScene){
        GridPane viewReportGrid = new GridPane();
        viewReportGrid.setPadding(new Insets(10, 10, 10, 10));
        viewReportGrid.setVgap(20);
        viewReportGrid.setHgap(20);
        Scene viewReportScene = new Scene(viewReportGrid, 400, 400);
        ArrayList<ArrayList<String>> modulesMark = CurrentStudent.getCurrentStudent().getReport();
        System.out.println(modulesMark);
        Text viewReportTitle = new Text("View Report of " + CurrentStudent.getCurrentStudent().getName());
        viewReportTitle.setStyle("-fx-font-size: 20px;");
        viewReportGrid.add(viewReportTitle, 0, 0);
        Text moduleCodeLabel = new Text("Module Code");
        Text markLabel = new Text("Mark");
        viewReportGrid.add(moduleCodeLabel, 0, 1);
        viewReportGrid.add(markLabel, 1, 1);
        int i = 2;
        for (ArrayList<String> module : modulesMark){
            Text moduleCode = new Text(module.get(0));
            Text mark = new Text(module.get(1));
            viewReportGrid.add(moduleCode, 0, i);
            viewReportGrid.add(mark, 1, i);
            i++;
        }
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            stage.setScene(previousScene);
        });
        viewReportGrid.add(backButton, 0, i);
        return viewReportScene;
    }

    public static Scene viewLecturersScene(Stage stage, Scene previousScene){
        GridPane viewLecturersGrid = new GridPane();
        viewLecturersGrid.setPadding(new Insets(10, 10, 10, 10));
        viewLecturersGrid.setVgap(20);
        viewLecturersGrid.setHgap(20);
        Scene viewLecturersScene = new Scene(viewLecturersGrid, 600, 600);
        ArrayList<HashMap<String, String>> lecturers = CurrentStudent.getCurrentStudent().getLecturers();
        Text viewLecturersTitle = new Text("View Lecturers");
        viewLecturersTitle.setStyle("-fx-font-size: 20px;");
        viewLecturersGrid.add(viewLecturersTitle, 0, 0);
        Text lecturerNameLabel = new Text("Lecturer Name");
        Text lecturerUsernameLabel = new Text("Lecturer Username");
        Text lecturerEmailLabel = new Text("Lecturer Email");
        Text lecturerPhoneLabel = new Text("Lecturer Phone");
        viewLecturersGrid.add(lecturerNameLabel, 0, 1);
        viewLecturersGrid.add(lecturerUsernameLabel, 1, 1);
        viewLecturersGrid.add(lecturerEmailLabel, 2, 1);
        viewLecturersGrid.add(lecturerPhoneLabel, 3, 1);
        int i = 2;
        for (HashMap<String, String> lecturer : lecturers){
            Text lecturerName = new Text(lecturer.get("name"));
            Text lecturerUsername = new Text(lecturer.get("username"));
            Text lecturerEmail = new Text(lecturer.get("email"));
            Text lecturerPhone = new Text(lecturer.get("phone"));
            viewLecturersGrid.add(lecturerName, 0, i);
            viewLecturersGrid.add(lecturerUsername, 1, i);
            viewLecturersGrid.add(lecturerEmail, 2, i);
            viewLecturersGrid.add(lecturerPhone, 3, i);
            i++;
        }
        Button backButton = new Button("Back");
        viewLecturersGrid.add(backButton, 0, i);
        backButton.setOnAction(e -> {
            stage.setScene(previousScene);
        });
        return viewLecturersScene;
    }

}
