package prashantpuri_2059631.coursemanagementsystem.user_interface.admin_frontend;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import prashantpuri_2059631.coursemanagementsystem.structure.Admin;
import prashantpuri_2059631.coursemanagementsystem.structure.Db_connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminPanel {

    // dashboard
    public static void adminDashboard(){
        Stage admin_stage = new Stage();
        GridPane admin_layout = new GridPane();
        Scene scene = new Scene(admin_layout, 400, 400);
        admin_stage.setTitle("Admin Dashboard");
        admin_layout.setPadding(new Insets(10, 10, 10, 10));
        admin_layout.setVgap(10);
        admin_layout.setHgap(10);
        Button view_all_student_button = new Button("View all student");
        Button view_student_report_button = new Button("View student report");
        Button view_all_course_button = new Button("View all course");
        Button add_instructor_button = new Button("Add instructor");
        Button add_course_button = new Button("Add course");
        Button edit_instructor_button = new Button("Edit instructor");
        Button edit_course_button = new Button("Edit course");
        Button delete_lecturer_button = new Button("Delete lecturer");
        Button add_module_button = new Button("Add module");
        Button edit_module_button = new Button("Edit module");
        Button delete_module_button = new Button("Delete module");
        admin_layout.add(view_all_student_button, 0, 0);
        admin_layout.add(view_student_report_button, 1, 0);
        admin_layout.add(view_all_course_button, 0, 1);
        admin_layout.add(add_instructor_button, 1, 1);
        admin_layout.add(add_course_button, 0, 2);
        admin_layout.add(edit_instructor_button, 1, 2);
        admin_layout.add(edit_course_button, 0, 3);
        admin_layout.add(delete_lecturer_button, 1, 3);
        admin_layout.add(add_module_button, 0, 4);
        admin_layout.add(edit_module_button, 1, 4);
        admin_layout.add(delete_module_button, 0, 5);
        admin_stage.setScene(scene);
        admin_stage.show();

        view_all_student_button.setOnAction(e -> {
            viewAllStudent(admin_stage);
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
        delete_lecturer_button.setOnAction(e -> {
           Stage deleteLecturerStage = new Stage();
           GridPane deletelecturerLayout = new GridPane();
           Scene delete_module_scene = new Scene(deletelecturerLayout, 400, 400);
           deleteLecturerStage.setTitle("Delete Module");
           deletelecturerLayout.setPadding(new Insets(10, 10, 10, 10));
           deletelecturerLayout.setVgap(10);
           deletelecturerLayout.setHgap(10);
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
           deletelecturerLayout.add(instructor_username, 0, 0);
           deletelecturerLayout.add(instructor_username_field, 1, 0);
           deletelecturerLayout.add(confirmdeleteButton, 1, 1);
           deleteLecturerStage.setScene(delete_module_scene);
           deleteLecturerStage.show();
        });

    }
    static void viewAllStudent(Stage admin_stage){
        GridPane admin_layout = new GridPane();
        Scene scene = new Scene(admin_layout, 400, 400);
        admin_stage.setTitle("View all student");
        admin_layout.setPadding(new Insets(10, 10, 10, 10));
        admin_layout.setVgap(10);
        admin_layout.setHgap(10);
        Text name = new Text("Name");
        Text email = new Text("Email");
        Text phone = new Text("Phone");
        Text address = new Text("Address");
        Text user_name = new Text("User name");
        Text course_id = new Text("Course id");
        Text year = new Text("Year");
        Text semester = new Text("Semester");
        admin_layout.add(name, 0, 0);
        admin_layout.add(email, 1, 0);
        admin_layout.add(phone, 2, 0);
        admin_layout.add(address, 3, 0);
        admin_layout.add(user_name, 4, 0);
        admin_layout.add(course_id, 5, 0);
        admin_layout.add(year, 6, 0);
        admin_layout.add(semester, 7, 0);

        try{
            Statement statement = Db_connection.get_statement();
            System.out.println(statement);
            ResultSet res = statement.executeQuery("SELECT * FROM student");
            int i = 1;
            while(res.next()){
                String std_name = res.getString("name");
                String std_email = res.getString("email");
                int std_phone = res.getInt("phone");
                String std_address = res.getString("address");
                String std_user_name = res.getString("user_name");
                String std_course_id = res.getString("course_id");
                int std_year = res.getInt("year");
                int std_semester = res.getInt("semester");
                admin_layout.add(new Text(std_name), 0, i);
                admin_layout.add(new Text(std_email), 1, i);
                admin_layout.add(new Text(String.valueOf(std_phone)), 2, i);
                admin_layout.add(new Text(std_address), 3, i);
                admin_layout.add(new Text(std_user_name), 4, i);
                admin_layout.add(new Text(std_course_id), 5, i);
                admin_layout.add(new Text(String.valueOf(std_year)), 6, i);
                admin_layout.add(new Text(String.valueOf(std_semester)), 7, i);
                i++;

            }

            }catch (SQLException ex) {
            System.out.println("Error");
        }

        admin_stage.setScene(scene);
    }


    // view student report


    // add lecturer


    // edit lecturer


    // add course


    // edit course

    // add module


    // edit module


    // edit student ( level up )
}
