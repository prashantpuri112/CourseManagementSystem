package prashantpuri_2059631.coursemanagementsystem.user_interface.admin_frontend;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import prashantpuri_2059631.coursemanagementsystem.structure.Admin;
import prashantpuri_2059631.coursemanagementsystem.structure.Course;

import java.util.ArrayList;
import java.util.HashMap;

public class CourseActivities {
    public static Scene addCourse(Stage stage, Scene prevScene) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);
        Text text = new Text("Add Course");
        gridPane.add(text, 0, 0);
        Scene scene = new Scene(gridPane, 300, 300);
        // add course fields
        Label courseCode = new Label("Course Code");
        TextField courseCodeField = new TextField();
        courseCode.setLabelFor(courseCodeField);
        courseCodeField.setPromptText("Enter Course Code");

        Label courseName = new Label("Course Name");
        TextField courseNameField = new TextField();
        courseName.setLabelFor(courseNameField);
        courseNameField.setPromptText("Enter Course Name");

        Label courseDuration = new Label("Course Duration");
        TextField courseDurationField = new TextField();
        courseDuration.setLabelFor(courseDurationField);
        courseDurationField.setPromptText("Enter Course Duration");

        Label courseStartLevel = new Label("Course Start Level");
        TextField courseStartLevelField = new TextField();
        courseStartLevel.setLabelFor(courseStartLevelField);
        courseStartLevelField.setPromptText("Enter Course Start Level");

        Label courseIsActive = new Label("Course Is Active");
        CheckBox courseIsActiveField = new CheckBox();
        courseIsActive.setLabelFor(courseIsActiveField);

        Button addCourseButton = new Button("Add Course");
        addCourseButton.setOnAction(e -> {
            if (courseCodeField.getText().isEmpty() || courseNameField.getText().isEmpty() || courseDurationField.getText().isEmpty() || courseStartLevelField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Please fill all the fields");
                alert.showAndWait();
            } else{

            Course c = new Course(courseCodeField.getText(), courseNameField.getText(),Integer.parseInt(courseDurationField.getText()), Integer.parseInt(courseStartLevelField.getText()), courseIsActiveField.isSelected());
            if(Admin.addCourse(c)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Course Added Successfully");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Course Already Exists");
                alert.showAndWait();
            }
            }
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(prevScene));
        gridPane.add(courseCode, 0, 1);
        gridPane.add(courseCodeField, 1, 1);
        gridPane.add(courseName, 0, 2);
        gridPane.add(courseNameField, 1, 2);
        gridPane.add(courseDuration, 0, 3);
        gridPane.add(courseDurationField, 1, 3);
        gridPane.add(courseStartLevel, 0, 4);
        gridPane.add(courseStartLevelField, 1, 4);
        gridPane.add(courseIsActive, 0, 5);
        gridPane.add(courseIsActiveField, 1, 5);
        gridPane.add(addCourseButton, 0, 6);
        gridPane.add(backButton, 1, 6);

        return scene;
    }

    public static Scene editCourse(Stage stage, Scene prevScene) {
        GridPane gridPane = new GridPane();
        Text text = new Text("Edit Course");
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.add(text, 0, 0);
        Scene scene = new Scene(gridPane, 300, 300);

        // search field
        TextField searchField = new TextField();
        searchField.setPromptText("Enter Course Code");
        Button searchButton = new Button("Search");


        // field to edit
        Label courseName = new Label("Course Name");
        TextField courseNameField = new TextField();
        courseName.setLabelFor(courseNameField);
        courseNameField.setPromptText("Enter Course Name");

        Label courseDuration = new Label("Course Duration");
        TextField courseDurationField = new TextField();
        courseDuration.setLabelFor(courseDurationField);
        courseDurationField.setPromptText("Enter Course Duration");

        Label courseStartLevel = new Label("Course Start Level");
        TextField courseStartLevelField = new TextField();
        courseStartLevel.setLabelFor(courseStartLevelField);
        courseStartLevelField.setPromptText("Enter Course Start Level");

        Label courseIsActive = new Label("Course Is Active");
        CheckBox courseIsActiveField = new CheckBox();
        courseIsActive.setLabelFor(courseIsActiveField);

        Button editCourseButton = new Button("Edit Course");

        ArrayList<String> currentCourse = new ArrayList<>();
        searchButton.setOnAction(e -> {
            currentCourse.clear();
            currentCourse.add(searchField.getText());
            HashMap<String, String> courseDetail = Admin.getCourseDetail(searchField.getText());
            courseNameField.setText(courseDetail.get("courseName"));
            courseDurationField.setText(courseDetail.get("courseDuration"));
            courseStartLevelField.setText(courseDetail.get("courseStartLevel"));
            courseIsActiveField.setSelected(Boolean.parseBoolean(courseDetail.get("isActive")));
                });
        editCourseButton.setOnAction(e -> {
            if (courseNameField.getText().isEmpty() || courseDurationField.getText().isEmpty() || courseStartLevelField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please Fill All Fields");
                alert.showAndWait();
            } else {

            Course c = new Course(currentCourse.get(0), courseNameField.getText(), Integer.parseInt(courseDurationField.getText()), Integer.parseInt(courseStartLevelField.getText()), courseIsActiveField.isSelected());

            if (Admin.editCourse(c)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Course Edited Successfully");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Course Already Exists");
                alert.showAndWait();
            }
            }
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(prevScene));
        gridPane.add(searchField, 0, 1);
        gridPane.add(searchButton, 1, 1);
        gridPane.add(courseName, 0, 2);
        gridPane.add(courseNameField, 1, 2);
        gridPane.add(courseDuration, 0, 3);
        gridPane.add(courseDurationField, 1, 3);
        gridPane.add(courseStartLevel, 0, 4);
        gridPane.add(courseStartLevelField, 1, 4);
        gridPane.add(courseIsActive, 0, 5);
        gridPane.add(courseIsActiveField, 1, 5);
        gridPane.add(editCourseButton, 1, 6);
        gridPane.add(backButton, 0, 6);

        return scene;
    }

    public static Scene viewAllCourse(Stage stage, Scene prevScene) {
        stage.setTitle("View All Courses");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);
        Text text = new Text("View All Courses");
        gridPane.add(text, 0, 0);
        Scene scene = new Scene(gridPane);
        Text courseCode = new Text("Course Code");
        Text courseNameText = new Text("Course Name");
        Text courseDurationText = new Text("Course Duration");
        Text courseStartLevelText = new Text("Course Start Level");
        Text courseIsActiveText = new Text("Course Is Active");
        gridPane.add(courseCode, 0, 1);
        gridPane.add(courseNameText, 1, 1);
        gridPane.add(courseDurationText, 2, 1);
        gridPane.add(courseStartLevelText, 3, 1);
        gridPane.add(courseIsActiveText, 4, 1);
        ArrayList<HashMap<String, String>> courseList = Admin.getAllCourse();
        for (int i = 0; i < courseList.size(); i++) {
            Text courseCodeText = new Text(courseList.get(i).get("courseCode"));
            Text courseName = new Text(courseList.get(i).get("courseName"));
            Text courseDuration = new Text(courseList.get(i).get("courseDuration"));
            Text courseStartLevel = new Text(courseList.get(i).get("courseStartLevel"));
            Text courseIsActive = new Text(courseList.get(i).get("isActive"));
            gridPane.add(courseCodeText,0, i + 2);
            gridPane.add(courseName, 1, i + 2);
            gridPane.add(courseDuration, 2, i + 2);
            gridPane.add(courseStartLevel, 3, i + 2);
            gridPane.add(courseIsActive, 4, i + 2);
        }
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(prevScene));
        gridPane.add(backButton, 0, courseList.size() + 3);
        return scene;
    }
}
