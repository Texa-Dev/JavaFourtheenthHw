package entitys;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306";
        String tabName = "students"; // создал просто для теста

        StudentsDbManager manager = new StudentsDbManager(url);
        Student test = new Student("Messi", "Leo", 35, 2, "PV121", true);
        Student s = new Student("Tarantino", "Quentin", 55, 4, "PV421", false);
        Student s1 = new Student("Test", "Student", 35, 1, "PV021", false);
        Student s2 = new Student("Spielberg", "Steven", 65, 5, "PV521", true);
        Student s3 = new Student("Nolan", "Christopher", 48, 2, "PV221", true);
        //   Student delStu = new Student("Messi","Leo",35,2,"PV121",true);


        try {
            manager.connect("root", "");
            //  manager.createDB("students_db");
            manager.selectDB("students_db");
            //   manager.createTable("students");
            // manager.insert("students",test);
            //   manager.insertAll("students", test,test,test,test);
            //  manager.deleteById(tabName, 4);
            //   manager.deleteByFields(tabName,test);

            manager.deleteByField(tabName, test, "name");
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
