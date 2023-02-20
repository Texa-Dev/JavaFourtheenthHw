package entitys;

import java.sql.*;

public class StudentsDbManager {
    private Connection con;
    private Statement stat;
    private String url;
    private String user;
    private String pass;
    private String dbName;

    public StudentsDbManager(String url) {
        this.url=url;
    }

    //Создания базы данных
    public void createDB(String dbName) throws SQLException {
       stat.execute("CREATE DATABASE  IF NOT EXISTS " + dbName + ";");
        System.out.println("Database "+dbName+" was created!");
    }

    //Выбора базы данных
    public void selectDB(String dbName) throws SQLException {
        stat.execute("use "+dbName+";");
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

    public void createTable(String tabName) throws SQLException
    {
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

}