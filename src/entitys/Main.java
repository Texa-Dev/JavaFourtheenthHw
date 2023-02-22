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


        try {
            //создаем подключение
            manager.connect("root", "root");
            //создаем базу
            manager.createDB("students_db");
            //выбираем базу
            manager.selectDB("students_db");
            //создаем таблицу
            manager.createTable("students");
            //добавляем одного студента
            manager.insert("students", test);
            //добавляем несколько студентов
            manager.insertAll("students", s, s1, s2, s3);
            //удаление по ID
            //  manager.deleteById(tabName, 4);
            //удаление из конкретной таблицы по всем полям студента кроме ID
            //   manager.deleteByFields(tabName,test);
            //удаление из конкретной таблицы по имени поля конкретного студента
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
