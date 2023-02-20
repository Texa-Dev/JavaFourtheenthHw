package entitys;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class StudentsDbManager {
    private Connection con;
    private Statement stat;
    private String url;
    private String user;
    private String pass;
    private String dbName;

    public StudentsDbManager(String url) {
        this.url = url;
    }

    //Создания базы данных
    public void createDB(String dbName) throws SQLException {
        stat.execute("create database if not exists " + dbName + ";");
        System.out.println("Database " + dbName + " was created!");
    }
    //Выбора базы данных
    public void selectDB(String dbName) throws SQLException {
        stat.execute("use " + dbName + ";");
    }
    //Подключение к базе данных
    public void connect(String user, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, pass);
        stat = con.createStatement();
        System.out.println("Connect successfully!!!");
    }
    //Закрытие подключения к базе данных
    public void close() throws SQLException {
        stat.close();
        con.close();
    }
    //Метод получения полей студента с помощью рефлексии
    private String[] studentFields() {
        List<Field> declFields = Arrays.stream(Student.class.getDeclaredFields()).toList();
        String[] fields = new String[declFields.size()];
        for (int i = 0; i < declFields.size(); i++) {
            fields[i] = declFields.get(i).getName();
        }
        return fields;
    }

//Метода для создания таблицы студентов:
    public void createTable(String tabName) throws SQLException {
        String sql = "create table if not exists %s(%s);";
        sql = String.format(sql,
                tabName,
                String.format("%s,%s,%s,%s,%s,%s,%s",
                        "id int primary key auto_increment",
                        "surname varchar(50)",
                        "name varchar(50)",
                        "age int(3)",
                        "course int(1)",
                        "student_group varchar(50)",
                        "stateFunded boolean"
                ));
        stat.execute(sql);
    }

    //Метод для добавления новой записи в таблицу
    public void insert(String tabName, Student student) {

        String sql = "insert into " + tabName + " (%s,%s,%s,%s,%s,%s,%s) values(?,?,?,?,?,?,?);";
        sql = String.format(sql, studentFields());
        // System.out.println(sql);
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, 0);
            ps.setObject(2, student.getSurname());
            ps.setObject(3, student.getName());
            ps.setObject(4, student.getAge());
            ps.setObject(5, student.getCourse());
            ps.setObject(6, student.getStudent_group());
            ps.setObject(7, student.isStateFunded());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Student saved!");
    }

    //Метод для вставки нескольких студентов
    public void insertAll(String tabName, Student... entities) throws Exception {
        // не знаю насколько правильный подход, но работает
        for (Student entity : entities) {
            insert(tabName, entity);
        }
        System.out.println("Add "+entities.length + " students");
    }

    //Метод для удаления записи в таблице по id
    public void deleteById(String tabName, Integer id) throws Exception {
        String sql = "delete from " + tabName + " where id= " + id;
        stat.executeUpdate(sql);
        System.out.println("Student deleted!");
    }

    //Удаление записи в таблице по всем полям студента, кроме id
    public void deleteByFields(String tabName, Student student) throws Exception {
        String sql = "delete from " + tabName + " where %s = ? and `%s` = ? and %s = ? and %s = ? and %s = ? and %s = ? ;";
        // исключения Id из списка выборки
        String[] s= Arrays.copyOfRange(studentFields(),1,studentFields().length);
        sql = String.format(sql, s);

        System.out.println(sql);
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, student.getSurname());
            ps.setObject(2, student.getName());
            ps.setObject(3, student.getAge());
            ps.setObject(4, student.getCourse());
            ps.setObject(5, student.getStudent_group());
            ps.setObject(6, student.isStateFunded());
            int count = ps.executeUpdate();
            System.out.println("Deleted! " + count);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Удаление записи в таблице по одному полю студента
    //не уверен что это так
    public void deleteByField(String tabName, Student student, String fieldName) throws Exception {
        String sql = "delete from " + tabName + " where "+fieldName+" = ?";
                Field field = student.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, field.get(student)); //field.get(student) получение значения конкретного поля у студента
            int count = ps.executeUpdate();
            System.out.println("Deleted! " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}