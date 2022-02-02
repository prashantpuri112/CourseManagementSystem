package prashantpuri_2059631.coursemanagementsystem.user_interface.admin_frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import prashantpuri_2059631.coursemanagementsystem.Main;
import prashantpuri_2059631.coursemanagementsystem.structure.Admin;
import prashantpuri_2059631.coursemanagementsystem.structure.Db_connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminPanel {
    // dashboard
    public static void adminDashboard(){
        Stage admin_stage = new Stage();
        BorderPane borderLayout = new BorderPane();
        GridPane admin_layout = new GridPane();
        HBox hbox = new HBox();
        borderLayout.setCenter(admin_layout);
        Image herald_logo = new Image(Main.class.getResource("img.png").toExternalForm());
        ImageView logo = new ImageView(herald_logo);
        logo.setFitWidth(200);
        logo.setPreserveRatio(true);
        hbox.getChildren().add(logo);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        borderLayout.setTop(hbox);
        admin_layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(borderLayout, 700, 600);
        admin_stage.setTitle("Admin Dashboard");
        admin_layout.setPadding(new Insets(20));
        scene.getStylesheets().add(Main.class.getResource("common.css").toExternalForm());
        admin_layout.setVgap(20);
        admin_layout.setHgap(20);
        Button view_all_student_button = new Button("View all student");
        Button view_student_report_button = new Button("View student report");
        Button view_all_course_button = new Button("View all course");
        Button add_instructor_button = new Button("Add instructor");
        Button add_course_button = new Button("Add course");
        Button edit_instructor_button = new Button("Edit instructor");
        Button edit_course_button = new Button("Edit course");
        Button delete_instructor_button = new Button("Delete instructor");
        Button add_module_button = new Button("Add module");
        Button edit_module_button = new Button("Edit module");
        Button delete_module_button = new Button("Delete module");
        Button view_all_instructor_button = new Button("View all instructor");
        Button view_all_module_button = new Button("View all module");
        admin_layout.add(view_all_student_button, 0, 0);
        admin_layout.add(view_student_report_button, 1, 0);
        admin_layout.add(view_all_course_button, 0, 1);
        admin_layout.add(add_instructor_button, 1, 1);
        admin_layout.add(add_course_button, 0, 2);
        admin_layout.add(edit_instructor_button, 1, 2);
        admin_layout.add(edit_course_button, 0, 3);
        admin_layout.add(delete_instructor_button, 1, 3);
        admin_layout.add(add_module_button, 0, 4);
        admin_layout.add(edit_module_button, 1, 4);
        admin_layout.add(delete_module_button, 0, 5);
        admin_layout.add(view_all_instructor_button, 1, 5);
        admin_layout.add(view_all_module_button, 0, 6);
        admin_stage.setScene(scene);
        admin_stage.show();
        view_all_instructor_button.setOnAction(e -> {
            admin_stage.setScene(AdminPanel.viewAllInstructor(admin_stage, scene));
                });
        view_all_student_button.setOnAction(e -> {
            viewAllStudent(admin_stage, scene);
        });
        view_student_report_button.setOnAction(e -> {
            admin_stage.setScene(AdminPanel.viewStudentReport(admin_stage, scene));
                });
        add_instructor_button.setOnAction(e -> {
            admin_stage.setScene(LecturerActivities.addInstructor(admin_stage, scene));
        });
        edit_instructor_button.setOnAction(e -> {
            admin_stage.setScene(LecturerActivities.editInstructor(admin_stage, scene));
        });
        add_course_button.setOnAction(e -> {
            admin_stage.setScene(CourseActivities.addCourse(admin_stage, scene));
        });
        edit_course_button.setOnAction(e -> {
            admin_stage.setScene(CourseActivities.editCourse(admin_stage, scene));
        });
        add_module_button.setOnAction(e -> {
            admin_stage.setScene(ModuleActivities.addModule(admin_stage, scene));
        });
        edit_module_button.setOnAction(e -> {
            admin_stage.setScene(ModuleActivities.editModule(admin_stage, scene));
        });
        view_all_module_button.setOnAction(e -> {
            admin_stage.setScene(ModuleActivities.viewAllModule(admin_stage, scene));
                });
        view_all_course_button.setOnAction(e -> {
            admin_stage.setScene(CourseActivities.viewAllCourse(admin_stage, scene));
                });

        delete_module_button.setOnAction(e -> {
           Stage delete_module_stage = new Stage();
           GridPane delete_module_layout = new GridPane();
           Scene delete_module_scene = new Scene(delete_module_layout, 400, 400);
           delete_module_stage.setTitle("Delete Module");
           delete_module_layout.setPadding(new Insets(10, 10, 10, 10));
           delete_module_layout.setVgap(10);
           delete_module_layout.setHgap(10);
           Text moduleCode = new Text("Module Code");
           TextField moduleCode_field = new TextField();
           Button confirmdeleteButton = new Button("Delete");
           confirmdeleteButton.setOnAction(ex -> {
               if(Admin.checkModule(moduleCode_field.getText()) && Admin.deleteModule(moduleCode_field.getText())){
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Success");
                   alert.setHeaderText("Deleted Module");
                   alert.setContentText("Module deleted successfully");
                   alert.showAndWait();
               } else {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setHeaderText("Error");
                   alert.setContentText("Module not found");
                   alert.showAndWait();
               }
           });
           delete_module_layout.add(moduleCode, 0, 0);
           delete_module_layout.add(moduleCode_field, 1, 0);
           delete_module_layout.add(confirmdeleteButton, 1, 1);
           delete_module_stage.setScene(delete_module_scene);
           delete_module_stage.show();
        });
        delete_instructor_button.setOnAction(e -> {
           Stage deleteLecturerStage = new Stage();
           GridPane deleteInstructor = new GridPane();
           admin_stage.setTitle("Delete Lecturer");
           Scene delete_module_scene = new Scene(deleteInstructor, 400, 400);
           deleteLecturerStage.setTitle("Delete Module");
           deleteInstructor.setPadding(new Insets(20));
           deleteInstructor.setVgap(20);
           deleteInstructor.setHgap(20);

           Text instructor_username = new Text("Instructor Username");
           TextField instructor_username_field = new TextField();
           Button confirmdeleteButton = new Button("Delete");
           confirmdeleteButton.setOnAction(ex -> {
               if(Admin.checkInstructor(instructor_username_field.getText()) && Admin.deleteInstructor(instructor_username_field.getText())){
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Success");
                   alert.setHeaderText("Deleted Instructor");
                   alert.setContentText("Instructor deleted successfully");
                   alert.showAndWait();
               } else {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setHeaderText("Error");
                   alert.setContentText("Instructor not found");
                   alert.showAndWait();
               }
           });
           deleteInstructor.add(instructor_username, 0, 0);
           deleteInstructor.add(instructor_username_field, 1, 0);
           deleteInstructor.add(confirmdeleteButton, 1, 1);
           deleteLecturerStage.setScene(delete_module_scene);
           deleteLecturerStage.show();
        });

    }
    static void viewAllStudent(Stage admin_stage, Scene prevScene){
        GridPane admin_layout = new GridPane();
        Scene scene = new Scene(admin_layout, 800, 600);
        scene.getStylesheets().add(Main.class.getResource("common.css").toExternalForm());
        admin_stage.setTitle("View all student");
        admin_layout.setPadding(new Insets(20));
        admin_layout.setVgap(30);
        admin_layout.setHgap(30);
        Text name = new Text("Name");
        Text email = new Text("Email");
        Text phone = new Text("Phone");
        Text address = new Text("Address");
        Text user_name = new Text("User name");
        name.getStyleClass().add("table_header");
        email.getStyleClass().add("table_header");
        phone.getStyleClass().add("table_header");
        address.getStyleClass().add("table_header");
        user_name.getStyleClass().add("table_header");
        admin_layout.add(name, 0, 0);
        admin_layout.add(email, 1, 0);
        admin_layout.add(phone, 2, 0);
        admin_layout.add(address, 3, 0);
        admin_layout.add(user_name, 4, 0);

        try{
            Statement statement = Db_connection.get_statement();
            System.out.println(statement);
            ResultSet res = statement.executeQuery("SELECT * FROM student");
            int i = 1;
            while(res.next()){
                String std_name = res.getString("name");
                String std_email = res.getString("email");
                String std_phone = res.getString("phone");
                String std_address = res.getString("address");
                String std_user_name = res.getString("user_name");
                admin_layout.add(new Text(std_name), 0, i);
                admin_layout.add(new Text(std_email), 1, i);
                admin_layout.add(new Text(String.valueOf(std_phone)), 2, i);
                admin_layout.add(new Text(std_address), 3, i);
                admin_layout.add(new Text(std_user_name), 4, i);
                i++;

            }

            Button backButton = new Button("Back");
            backButton.setOnAction(ex -> {
                admin_stage.setScene(prevScene);
                    });
            admin_layout.add(backButton, 0, i, 2, 1);
            }catch (SQLException ex) {
            System.out.println("Error");
        }
        admin_stage.setScene(scene);
    }
    public static Scene viewAllInstructor(Stage admin_stage, Scene prevScene){
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid);
        scene.getStylesheets().add(Main.class.getResource("common.css").toExternalForm());

        admin_stage.setTitle("View all instructor");
        Text title = new Text(" all instructor");
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(title, 0, 0);

        Text name = new Text("Name");
        Text email = new Text("Email");
        Text user_name = new Text("User name");
        Text address = new Text("Address");
        Text phone = new Text("Phone");



        grid.add(name, 0, 1);
        grid.add(email, 1, 1);
        grid.add(user_name, 2, 1);
        grid.add(address, 3, 1);
        grid.add(phone, 4, 1);

        ArrayList<HashMap<String, String>> instructors = Admin.viewAllInstructor();
        for(int i = 0; i < instructors.size(); i++){
            HashMap<String, String> instructor = instructors.get(i);
            Text name_text = new Text(instructor.get("name"));
            Text email_text = new Text(instructor.get("email"));
            Text user_name_text = new Text(instructor.get("user_name"));
            Text address_text = new Text(instructor.get("address"));
            Text phone_text = new Text(instructor.get("phone"));
            grid.add(name_text, 0, i+2);
            grid.add(email_text, 1, i+2);
            grid.add(user_name_text, 2, i+2);
            grid.add(address_text, 3, i+2);
            grid.add(phone_text, 4, i+2);
        }
        Button backButton = new Button("Back");
        backButton.setOnAction(ex -> {
            admin_stage.setScene(prevScene);
        });
        grid.add(backButton, 0, instructors.size()+2, 2, 1);

        return scene;
    }

    public static Scene viewStudentReport(Stage admin_stage, Scene prevScene){
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 600, 600);
        TextField searchStudent = new TextField();
        admin_stage.setTitle("View student report");
        Button search = new Button("Search");
        searchStudent.setPromptText("Enter student username");
        Text moduleText = new Text("Module Code");
        Text marks = new Text("Marks");
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(searchStudent, 0, 0);
        grid.add(search, 1, 0);
        grid.add(moduleText, 0, 1);
        grid.add(marks, 1, 1);

        search.setOnAction(ex -> {
            if (!searchStudent.getText().equals("")) {
                Text title = new Text("Student Report for " + searchStudent.getText());
                ArrayList<HashMap<String, String>> moduleMark = Admin.viewStudentReport(searchStudent.getText());
                if (moduleMark != null) {
                    grid.getChildren().clear();
                    grid.add(searchStudent, 0, 0);
                    grid.add(search, 1, 0);
                    grid.add(moduleText, 0, 2);
                    grid.add(marks, 1, 2);
                    grid.add(title, 0, 1);
                    int i = 3;
                    for (HashMap<String, String> module : moduleMark) {
                        Text module_text = new Text(module.get("moduleCode"));
                        Text mark_text = new Text(module.get("mark"));
                        grid.add(module_text, 0, i);
                        grid.add(mark_text, 1, i);
                        i++;
                    }
                }
            }
        });
                    Button backButton = new Button("Back");
                    backButton.setOnAction(ex1 -> {
                        admin_stage.setScene(prevScene);
                    });
                    grid.add(backButton, 3, 3);
        return scene;
    }

}
