package prashantpuri_2059631.coursemanagementsystem.structure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Students extends User{
    private String course_code;
    private int year;
    private int semester;
    public Students(String name, String email, String password, String phone, String address, String role, String user_name, String course_code) {
        super(name, email, password, phone, address, role, user_name);
        this.course_code = course_code;
        this.year = 1;
        this.semester = 1;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }


    public void register() throws SQLException {
        Statement statement = Db_connection.get_statement();
        String query = "INSERT INTO `student`(`name`, `email`, `password`, `phone`, `address`, `user_name`, `course_id`, `year`, `semester`) VALUES ('"+this.getName()+"','"+this.getEmail()+"','"+this.getPassword()+"','"+this.getPhone()+"','"+this.getAddress()+"','"+this.getUser_name()+"','"+this.getCourse_code()+"','"+this.getYear()+"','"+this.getSemester()+"')";
        statement.executeUpdate(query);

    }
    public ArrayList<String> getAllCourseCodes(){
        ArrayList<String> course_codes = new ArrayList<>();
        try{
            Statement statement = Db_connection.get_statement();
            String query = "SELECT * FROM `course` WHERE isActive = 1";
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
    public static HashMap<String, String> getCourseDetail(String courseCode){
        HashMap<String, String> course_details = new HashMap<>();
        try{
            Statement statement = Db_connection.get_statement();
            String query = "SELECT * FROM `course` WHERE `courseCode` = '"+courseCode+"'";
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
}
