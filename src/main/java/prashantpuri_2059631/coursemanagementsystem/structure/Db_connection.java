package prashantpuri_2059631.coursemanagementsystem.structure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Db_connection {
    public static Statement get_statement() {
        try {
            String db_user = "root";
            String db_password = "root";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/course_management", db_user, db_password);
            return connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

