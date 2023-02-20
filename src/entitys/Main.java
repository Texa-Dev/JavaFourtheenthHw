package entitys;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306";
        StudentsDbManager manager = new StudentsDbManager(url);
           Student s = new Student("Tarantino","Quentin",55,4,"PV421",false);
          Student s1 = new Student("Test","Student",35,1,"PV021",false);
          Student s2 = new Student("Spielberg","Steven",65,5,"PV521",true);
          Student s3 = new Student("Nolan","Christopher",48,2,"PV221",true);

        try {
            manager.connect("root", "");
            //  manager.createDB("students_db");
            manager.selectDB("students_db");
            //   manager.createTable("students");
            // manager.insert("students",s1);
          // manager.insertAll("students", s,s1,s2,s3);

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
