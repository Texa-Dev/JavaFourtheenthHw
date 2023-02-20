package entitys;

public class Student {
    private Integer id;
    private String surname;
    private String name;
    private int age;
    private int course;
    private String student_group;
    private boolean stateFunded; //бюджетная форма обучения
    public Student() {
    }

    public Student(String surname, String name, int age, int course, String group, boolean stateFunded) {
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.course = course;
        this.student_group = group;
        this.stateFunded = stateFunded;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getStudent_group() {
        return student_group;
    }

    public void setStudent_group(String student_group) {
        this.student_group = student_group;
    }

    public boolean isStateFunded() {
        return stateFunded;
    }

    public void setStateFunded(boolean stateFunded) {
        this.stateFunded = stateFunded;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", course='" + course + '\'' +
                ", group='" + student_group + '\'' +
                ", stateFunded=" + stateFunded +
                '}';
    }
}
