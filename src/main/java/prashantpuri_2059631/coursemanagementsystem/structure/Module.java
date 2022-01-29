package prashantpuri_2059631.coursemanagementsystem.structure;

public class Module {
    private String moduleCode;
    private String moduleName;
    private String moduleLeader;
    private String courseCode;

    public Module(String moduleCode, String moduleName, String moduleLeader, String courseCode) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleLeader = moduleLeader;
        this.courseCode = courseCode;
    }
    // getters
    public String getModuleCode() {
        return moduleCode;
    }
    public String getModuleName() {
        return moduleName;
    }
    public String getModuleLeader() {
        return moduleLeader;
    }
    public String getCourseCode() {
        return courseCode;
    }
    // setters
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    public void setModuleLeader(String moduleLeader) {
        this.moduleLeader = moduleLeader;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}

