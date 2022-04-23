package prashantpuri_2059631.coursemanagementsystem.user_interface.instructor_frontend;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import prashantpuri_2059631.coursemanagementsystem.CurrentInstructor;
import prashantpuri_2059631.coursemanagementsystem.structure.Instructor;
import prashantpuri_2059631.coursemanagementsystem.user_interface.Login;

import java.util.ArrayList;
import java.util.HashMap;

public class InstructorPanel {
    public static void instructorDashboard(){
        Stage instructorStage = new Stage();
        GridPane instructorGridPane = new GridPane();
        instructorGridPane.setHgap(10);
        instructorGridPane.setVgap(10);
        instructorGridPane.setPadding(new Insets(20));
        instructorStage.setTitle("Instructor Dashboard");
        Scene instructorScene = new Scene(instructorGridPane, 600, 400);
        instructorStage.setScene(instructorScene);
        Text instructorText = new Text("Welcome to Instructor Dashboard");
        instructorText.setStyle("-fx-font: normal bold 20px 'serif' ");
        instructorGridPane.add(instructorText, 0, 0);

        Text instructorText1 = new Text("Click on module buttons to add student marks");
        instructorGridPane.add(instructorText1, 0, 1);
        ArrayList<String> moduleList = CurrentInstructor.getCurrentInstructor().getMyModules();
        int i = 2;
        for(String module : moduleList){
            Button moduleButton = new Button(module);
            instructorGridPane.add(moduleButton, 0, i);
            moduleButton.setOnAction(e -> markAddView(module));
            i++;
        }

        instructorStage.show();
    }

    public static void markAddView(String moduleCode){
        Stage markAddStage = new Stage();
        BorderPane markAddBorderPane = new BorderPane();
        Scene markAddScene = new Scene(markAddBorderPane, 600, 400);
        Text markAddText = new Text("Enter marks for " + moduleCode);
        markAddText.setStyle("-fx-font: normal bold 20px 'serif' ");

        markAddBorderPane.setTop(markAddText);
        TableView<StudentMarkPrototype> markAddTableView = new TableView<>();
        markAddTableView.setPadding(new Insets(10, 10, 10, 10));
        markAddTableView.setEditable(true);
        markAddBorderPane.setCenter(markAddTableView);
        TableColumn<StudentMarkPrototype, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<StudentMarkPrototype, String> mark = new TableColumn<>("Mark");
        mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
        mark.setCellFactory(TextFieldTableCell.forTableColumn());

        markAddTableView.getColumns().addAll(usernameColumn, mark);

        ArrayList<HashMap<String, String>> students = CurrentInstructor.getCurrentInstructor().getModuleStudents(moduleCode);
        if (students != null) {
            for(HashMap<String, String> student : students){
                if (student.get("mark") == null){
                    StudentMarkPrototype studentMarkPrototype = new StudentMarkPrototype(student.get("username"), "N/A");
                    markAddTableView.getItems().add(studentMarkPrototype);
                } else {
                    StudentMarkPrototype studentMarkPrototype = new StudentMarkPrototype(student.get("username"), student.get("mark"));
                    markAddTableView.getItems().add(studentMarkPrototype);
                }
            }
        }
        mark.setOnEditCommit(
                new EventHandler<>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<StudentMarkPrototype, String> t) {
                        ((StudentMarkPrototype) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setMark(t.getNewValue());
                        String username = t.getTableView().getItems().get(t.getTablePosition().getRow()).getUsername();
                        if (CurrentInstructor.getCurrentInstructor().updateStudentMark(username, t.getNewValue(), moduleCode)) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success");
                            alert.setHeaderText("Mark updated successfully");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Mark update failed");
                            alert.showAndWait();
                        }
                    }
                }
        );

        markAddStage.setScene(markAddScene);
        markAddStage.show();
    }
}
