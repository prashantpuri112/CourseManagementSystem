package prashantpuri_2059631.coursemanagementsystem.user_interface.instructor_frontend;

public class StudentMarkPrototype {
    private String username;
    private String mark;
    StudentMarkPrototype(String username, String mark) {
        this.username = username;
        this.mark = mark;
    }
    public String getUsername() {
        return username;
    }
    public String getMark() {
        return mark;
    }
    // setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
}
