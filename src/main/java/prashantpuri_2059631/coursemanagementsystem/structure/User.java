package prashantpuri_2059631.coursemanagementsystem.structure;

import prashantpuri_2059631.coursemanagementsystem.CurrentInstructor;
import prashantpuri_2059631.coursemanagementsystem.CurrentStudent;

import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String role;
    private String user_name;

    //constructor
    public User(String name, String email, String password, String phone, String address, String user_name, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.user_name = user_name;

    }
    //getter
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public String getRole() {
        return role;
    }
    public String getUser_name() {return user_name;}

    //setter
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
        }

    public static boolean login(String username, String password, String user_role){
        System.out.println("User name: "+username+"\nPassword: "+password+"\nUser role: "+user_role);
        try{
            Statement statement = Db_connection.get_statement();
            String query = "SELECT * FROM " + user_role.toLowerCase() + " WHERE user_name = '" + username + "' AND password = '" + password + "'";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                if (user_role.equalsIgnoreCase("student")){
                    CurrentStudent.currentStudent = new Student(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("address"), resultSet.getString("user_name"));
                } else if (user_role.equalsIgnoreCase("instructor")){
                    CurrentInstructor.currentInstructor = new Instructor(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("address"), resultSet.getString("user_name"));
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
