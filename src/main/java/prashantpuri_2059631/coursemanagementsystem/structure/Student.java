package prashantpuri_2059631.coursemanagementsystem.structure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Student extends User{
    public Student(String name, String email, String password, String phone, String address, String user_name) {
        super(name, email, password, phone, address, user_name, "student");
    }



    public void register() throws SQLException {
        Statement statement = Db_connection.get_statement();
        String query = "INSERT INTO student(user_name, name, email, password, phone, address) VALUES ('"+this.getUser_name()+"', '"+this.getName()+"', '"+this.getEmail()+"', '"+this.getPassword()+"', '"+this.getPhone()+"', '"+this.getAddress()+"')";
        statement.executeUpdate(query);


    }
    public void registerCourse(String courseCode) throws SQLException {
            Statement statement = Db_connection.get_statement();
            HashMap<String, String> course_details = getCourseDetail(courseCode);
            String query = "INSERT INTO studentcourse(username, courseCode, level) VALUES ('"+this.getUser_name()+"', '"+courseCode+"', '"+course_details.get("courseStartLevel")+"')";
            statement.executeUpdate(query);

    }
    public ArrayList<String> getAllCourseCodes(){
        ArrayList<String> course_codes = new ArrayList<>();
        try{
            Statement statement = Db_connection.get_statement();
            String query = "SELECT * FROM course";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                course_codes.add(resultSet.getString("courseCode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(course_codes);
        return course_codes;
    }
    public HashMap<String, String> getCourseDetail(String courseCode){
        HashMap<String, String> course_details = new HashMap<>();
        try{
            Statement statement = Db_connection.get_statement();
            String query = "SELECT * FROM course WHERE courseCode = '"+courseCode+"'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                course_details.put("courseCode", resultSet.getString("courseCode"));
                course_details.put("courseName", resultSet.getString("courseName"));
                course_details.put("courseDuration", resultSet.getString("courseDuration"));
                course_details.put("courseStartLevel", resultSet.getString("courseStartLevel"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course_details;
    }

    public ArrayList<String> getEnrolledModules(){
        Statement statement = Db_connection.get_statement();
        ArrayList<String> enrolled_modules = new ArrayList<>();
        try{
            String query = "SELECT * FROM modulemark where username = '"+this.getUser_name()+"'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                enrolled_modules.add(resultSet.getString("moduleCode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrolled_modules;
    }
    public ArrayList<ArrayList<String>> getReport(){
        Statement statement = Db_connection.get_statement();
        ArrayList<ArrayList<String>> modulesMark = new ArrayList<>();
        try{
            String query = "SELECT * FROM modulemark where username = '"+this.getUser_name()+"'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                ArrayList<String> module_mark = new ArrayList<>();
                module_mark.add(resultSet.getString("moduleCode"));
                module_mark.add(String.valueOf(resultSet.getInt("mark")));
                modulesMark.add(module_mark);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modulesMark;
    }
    public HashMap<String, String> getEnrollmentDetail(){
        Statement statement = Db_connection.get_statement();
        HashMap<String, String> enrollment_details = new HashMap<>();
        try{
            String query = "SELECT * FROM studentcourse where username = '"+this.getUser_name()+"'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                enrollment_details.put("courseCode", resultSet.getString("courseCode"));
                enrollment_details.put("level", resultSet.getString("level"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollment_details;
    }
    public ArrayList<HashMap<String, String>> getAvailableModules(){
        ArrayList<String> enrolledModules = this.getEnrolledModules();
        ArrayList<HashMap<String, String>> available_modules = new ArrayList<>();
        ArrayList<HashMap<String, String>> allModules = Admin.getAllModules();
        int level = Integer.parseInt(this.getEnrollmentDetail().get("level"));
        for (HashMap<String, String> module : allModules){
            if (Integer.parseInt(module.get("moduleLevel")) == level){
                if (!enrolledModules.contains(module.get("moduleCode"))){
                    available_modules.add(module);
                }
            }
        }
        return available_modules;
    }

    public boolean enrollModules(ArrayList<String> modules){
        Statement statement = Db_connection.get_statement();
        try{
            for (String module : modules){
                String query = "INSERT INTO modulemark (username, moduleCode) VALUES ('"+this.getUser_name()+"', '"+module+"')";
                statement.executeUpdate(query);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<HashMap<String, String>> getLecturers() {
        ArrayList<String> enrolledModules = this.getEnrolledModules();
        ArrayList<HashMap<String, String>> allLecturerDetails = new ArrayList<>();
        Statement statement = Db_connection.get_statement();
        Set<String> lecturers = new HashSet<>();
        String query;
        try{
            for (String module : enrolledModules){
                query = "SELECT * FROM module where moduleCode = '"+module+"'";
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    String lecturer_username = rs.getString("moduleLeader");
                    lecturers.add(lecturer_username);
                }
            }
            for (String lecturer : lecturers){
                query = "SELECT * FROM instructor where user_name = '"+lecturer+"'";
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    HashMap<String, String> lecturer_details = new HashMap<>();
                    lecturer_details.put("username", rs.getString("user_name"));
                    lecturer_details.put("name", rs.getString("name"));
                    lecturer_details.put("email", rs.getString("email"));
                    lecturer_details.put("phone", rs.getString("phone"));
                    allLecturerDetails.add(lecturer_details);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allLecturerDetails;
    }
}
