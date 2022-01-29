package prashantpuri_2059631.coursemanagementsystem.structure;

public class Course {
    private String courseCode;
    private String courseName;
    private int courseDuration;
    private int courseStartLevel;
    private boolean isActive;
    public Course(String courseCode, String courseName, int courseDuration, int courseStartLevel, boolean isActive) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseStartLevel = courseStartLevel;
        this.isActive = isActive;
    }
    // getters
    public String getCourseCode() {
        return courseCode;
    }
    public String getCourseName() {
        return courseName;
    }
    public int getCourseDuration() {
        return courseDuration;
    }
    public int getCourseStartLevel() {
        return courseStartLevel;
    }
    public boolean getIsActive() {
        return isActive;
    }
    // setters
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }
    public void setCourseStartLevel(int courseStartLevel) {
        this.courseStartLevel = courseStartLevel;
    }
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}


