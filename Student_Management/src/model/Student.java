package model;

public class Student {

    private String id, name, courseName;
    private int semester;

    public Student() {
    }

    public Student(String id, String name, String courseName, int semester) {
        this.id = id;
        this.name = name;
        this.courseName = courseName;
        this.semester = semester;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void printData() {
        System.out.printf("%-15s%-15d%-15s", this.getName(), this.getSemester(), this.getCourseName());
    }

}
