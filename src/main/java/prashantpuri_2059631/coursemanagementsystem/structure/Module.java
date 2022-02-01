package prashantpuri_2059631.coursemanagementsystem.structure;

public class Module {
    private String moduleCode;
    private String moduleName;
    private int moduleLevel;
    private String moduleLeader;
    private String courseCode;
    private boolean isOptional;

    public Module(String moduleCode, String moduleName, int moduleLevel, String moduleLeader, String courseCode, boolean isOptional) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleLevel = moduleLevel;
        this.moduleLeader = moduleLeader;
        this.courseCode = courseCode;
        this.isOptional = isOptional;
    }
    // getters
    public String getModuleCode() {
        return moduleCode;
    }
    public String getModuleName() {
        return moduleName;
    }
    public int getModuleLevel() {
        return moduleLevel;
    }
    public String getModuleLeader() {
        return moduleLeader;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public boolean getIsOptional() {
        return isOptional;
    }
    // setters
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    public void setModuleLevel(int moduleLevel) {
        this.moduleLevel = moduleLevel;
    }
    public void setModuleLeader(String moduleLeader) {
        this.moduleLeader = moduleLeader;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public void setIsOptional(boolean optional) {
        this.isOptional = optional;
    }
}

