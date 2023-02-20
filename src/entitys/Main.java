package entitys;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306";
        StudentsDbManager manager = new StudentsDbManager(url);

        try {
            manager.connect("root", "");
          //  manager.createDB("students_db");
            manager.selectDB("students_db");
            manager.createTable("students");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                manager.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
