package prashantpuri_2059631.coursemanagementsystem.structure;

import java.sql.SQLException;
import java.sql.Statement;

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
}
