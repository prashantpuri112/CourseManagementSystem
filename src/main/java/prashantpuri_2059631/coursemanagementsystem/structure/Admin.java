package prashantpuri_2059631.coursemanagementsystem.structure;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Admin extends User {
    Admin(String name, String email, String password, String phone, String address, String role, String user_name) {
        super(name, email, password, phone, address, role, user_name);
    }
    public static boolean addInstructor(Instructor instructor){
        try{
            Statement stmt = Db_connection.get_statement();
            String query = "INSERT INTO instructor(name, email, user_name, address, phone, password) values ('"+instructor.getName()+"','"+instructor.getEmail()+"','"+instructor.getUser_name()+"','"+instructor.getAddress()+"','"+instructor.getPhone()+"','"+instructor.getPassword()+"')";
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean editUser(User user){
        try{
            Statement stmt = Db_connection.get_statement();
            String table_name = user.getRole();
            String query = "UPDATE "+table_name+" SET name = '"+user.getName()+"', email = '"+user.getEmail()+"', password = '"+user.getPassword()+"', phone = '"+user.getPhone()+"', address = '"+user.getAddress()+"' WHERE user_name = '"+user.getUser_name()+"'";
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static HashMap<String, String> getLecturerDetails(String user_name){
        HashMap<String, String> details = new HashMap<>();
        try{
            Statement stmt = Db_connection.get_statement();
            String query = "SELECT * FROM instructor WHERE user_name = '"+user_name+"'";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                details.put("name", rs.getString("name"));
                details.put("email", rs.getString("email"));
                details.put("password", rs.getString("password"));
                details.put("phone", rs.getString("phone"));
                details.put("address", rs.getString("address"));
                details.put("user_name", rs.getString("user_name"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return details;
    }
    // method to add course
    public static boolean addCourse(Course course){
        try{
            Statement smt = Db_connection.get_statement();
            String query = "INSERT INTO course(courseCode, courseName, courseDuration, courseStartLevel, isActive) values ('"+course.getCourseCode()+"','"+course.getCourseName()+"','"+course.getCourseDuration()+"','"+course.getCourseStartLevel()+"','"+course.getIsActive()+"')";
            smt.executeUpdate(query);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean editCourse(Course course){
        try{

            Statement smt = Db_connection.get_statement();
            String query = "UPDATE course SET  courseName = '"+course.getCourseName()+"', courseDuration = '"+course.getCourseDuration()+"', courseStartLevel = '"+course.getCourseStartLevel()+"', isActive = '"+course.getIsActive()+"' WHERE courseCode = '"+course.getCourseCode()+"'";
            smt.executeUpdate(query);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static HashMap<String, String> getCourseDetail(String courseCode){
        HashMap<String, String> details = new HashMap<>();
        try{
            Statement stmt = Db_connection.get_statement();
            String query = "SELECT * FROM course WHERE courseCode = '"+courseCode+"'";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                details.put("courseCode", rs.getString("courseCode"));
                details.put("courseName", rs.getString("courseName"));
                details.put("courseDuration", rs.getString("courseDuration"));
                details.put("courseStartLevel", rs.getString("courseStartLevel"));
                details.put("isActive", rs.getString("isActive"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return details;
    }

    // module methods
    // it has three fields moduleCode, moduleName, moduleLeader, courseCode
    // check if moduleLeader exist in instructor table and if courseCode exist in course table
    public static boolean checkInstructor(String moduleLeader){
        try{
            Statement stmt = Db_connection.get_statement();
            String query = "SELECT * FROM instructor WHERE user_name = '"+moduleLeader+"'";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    // checking if courseCode exist in course table
    public static boolean checkCourseCode(String courseCode){
        try{
            Statement stmt = Db_connection.get_statement();
            String query = "SELECT * FROM course WHERE courseCode = '"+courseCode+"'";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    // method to add module
    public static boolean addModule(Module module){
        try{
            Statement smt = Db_connection.get_statement();
            String query = "INSERT INTO module(moduleCode, moduleName, moduleLeader, courseCode) values ('"+module.getModuleCode()+"','"+module.getModuleName()+"','"+module.getModuleLeader()+"','"+module.getCourseCode()+"')";
            smt.executeUpdate(query);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    // method to edit module
    public static boolean editModule(Module module){
        try{

            Statement smt = Db_connection.get_statement();
            String query = "UPDATE module SET  moduleName = '"+module.getModuleName()+"', moduleLeader = '"+module.getModuleLeader()+"', courseCode = '"+module.getCourseCode()+"' WHERE moduleCode = '"+module.getModuleCode()+"'";
            smt.executeUpdate(query);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static HashMap<String, String> getModuleDetail(String moduleCode) {
        HashMap<String, String> details = new HashMap<>();
        try {
            Statement stmt = Db_connection.get_statement();
            String query = "SELECT * FROM module WHERE moduleCode = '" + moduleCode + "'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                details.put("moduleName", rs.getString("moduleName"));
                details.put("moduleLeader", rs.getString("moduleLeader"));
                details.put("courseCode", rs.getString("courseCode"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }
    public static boolean checkModule(String moduleCode){
        try{
            Statement stmt = Db_connection.get_statement();
            String query = "SELECT * FROM module WHERE moduleCode = '"+moduleCode+"'";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteModule(String moduleCode){
        try{
            Statement stmt = Db_connection.get_statement();
            String query = "DELETE FROM module WHERE moduleCode = '"+moduleCode+"'";
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean deleteInstructor(String user_name){
        try{
            Statement stmt = Db_connection.get_statement();
            String query = "DELETE FROM instructor WHERE user_name = '"+user_name+"'";
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
