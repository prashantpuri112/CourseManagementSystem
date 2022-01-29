package prashantpuri_2059631.coursemanagementsystem.structure;

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
}
