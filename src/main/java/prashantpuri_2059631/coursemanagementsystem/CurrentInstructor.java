package prashantpuri_2059631.coursemanagementsystem;

import prashantpuri_2059631.coursemanagementsystem.structure.Instructor;

public class CurrentInstructor {
    public static Instructor currentInstructor = null;

    public static Instructor getCurrentInstructor() {
        return currentInstructor;
    }

    public static void setCurrentInstructor(Instructor currentInstructor) {
        CurrentInstructor.currentInstructor = currentInstructor;
    }
}
