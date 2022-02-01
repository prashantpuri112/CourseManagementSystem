package prashantpuri_2059631.coursemanagementsystem.structure;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Instructor extends User {

    public Instructor(String name, String email, String password, String phone, String address, String user_name) {
        super(name, email, password, phone, address, user_name, "instructor");
    }
    public ArrayList<String> getMyModules(){
        Statement statement = Db_connection.get_statement();
        ArrayList<String> my_modules = new ArrayList<>();
        try{
            String query = "SELECT moduleCode FROM module WHERE moduleLeader = '"+this.getUser_name()+"'";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                my_modules.add(resultSet.getString("moduleCode"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return my_modules;
    }
    public ArrayList<HashMap<String, String>> getModuleStudents(String moduleCode){
        Statement statement = Db_connection.get_statement();
        ArrayList<HashMap<String, String>> students = new ArrayList<>();
        try{
            String query = "SELECT * FROM modulemark where moduleCode = '"+moduleCode+"'";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                HashMap<String, String> student = new HashMap<>();
                student.put("username", resultSet.getString("username"));
                student.put("mark", resultSet.getString("mark"));
                students.add(student);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }
    public boolean updateStudentMark(String username, String mark, String moduleCode){

        Statement statement = Db_connection.get_statement();
        try{
            String query = "UPDATE modulemark SET mark = "+Integer.parseInt(mark)+" WHERE username = '"+username+"' AND moduleCode = '"+moduleCode+"'";
            System.out.println(query);
            statement.executeUpdate(query);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
