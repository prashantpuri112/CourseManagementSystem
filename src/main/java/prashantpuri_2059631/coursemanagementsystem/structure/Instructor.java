package prashantpuri_2059631.coursemanagementsystem.structure;

public class Instructor extends User {

    public Instructor(String name, String email, String password, String phone, String address, String user_name) {
        super(name, email, password, phone, address, user_name, "instructor");
    }
}
