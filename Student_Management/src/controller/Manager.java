package controller;

import com.sun.source.tree.ContinueTree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import model.Report;
import model.Student;

public class Manager {

    public void initData(ArrayList<Student> listStudent) {

        listStudent.add(new Student("S01", "Phan Ngoc Duc", "Java", 2));
        listStudent.add(new Student("S02", "Phan Ngoc Anh", "Java", 3));
        listStudent.add(new Student("S03", "Tran Ngoc Duc", ".Net", 4));
        listStudent.add(new Student("S03", "Tran Ngoc Duc", ".Net", 4));
        listStudent.add(new Student("S03", "Tran Ngoc Duc", ".Net", 4));
        listStudent.add(new Student("S03", "Tran Ngoc Duc", ".Net", 4));
        listStudent.add(new Student("S03", "Tran Ngoc Duc", ".Net", 4));
        listStudent.add(new Student("S03", "Tran Ngoc Duc", ".Net", 4));
        listStudent.add(new Student("S03", "Tran Ngoc Duc", ".Net", 4));
    }

    public void showMenu() {
        System.out.println("WELCOME TO STUDENT MANAGEMENT");
        System.out.println("1. Create");
        System.out.println("2. Find and Sort");
        System.out.println("3. Update/Delete");
        System.out.println("4. Report");
        System.out.println("5. Exit");
    }

    public void createStudent(ArrayList<Student> list) {

        int length = list.size();

        while (true) {

            String id = Validation.checkId("Enter id student: ", list);

            String name = Validation.inputString("Enter name student: ");

            int semester = Validation.checkInt("Enter semester student", 1, 9);

            String courseName = Validation.checkCourseName("Enter course name: ");

            Student newStudent = new Student(id, name, courseName, semester);

            list.add(newStudent);

            if (list.size() > 10) {
                if (Validation.checkYesNo("Do you want to continue (Y/N) ")) {
                    continue;
                } else {
                    break;
                }
            } else {
                System.out.println("Add Student Success ! Number student in list is " + (length + 1) + " , please input data student till to enough 10 student");
                continue;
            }
        }

    }

    public void findAndSortStudent(ArrayList<Student> listStudent) {
        if (listStudent.isEmpty()) {
            System.out.println("The student list is empty");
            return;
        }

        String name_to_find = Validation.inputString("Input name to find student by name:");
        ArrayList<Student> listStudentByName = getStudentListByName(listStudent, name_to_find);
        if (listStudentByName.isEmpty()) {
            System.out.println("Student does not exist !!!");
            return;
        } else {

            displayListStudent(listStudentByName, "List Student Sorted By Name: ");

            Comparator c = new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    int d = o1.getName().compareTo(o2.getName());

                    if (d > 0) {
                        return 1;
                    } else if (d < 0) {
                        return -1;
                    }
                    return 0;
                }

            };

            Collections.sort(listStudent, c);

            displayListStudent(listStudent, "List Student Find By Name Above: ");

        }

    }

    public void displayListStudent(ArrayList<Student> list, String msg) {
        System.out.println(msg);
        int stt = 1;
        System.out.printf("%-10s%-15s%-15s%-15s\n", "STT", "Student Name", "Student Semester", "Course Name");

        for (Student s : list) {
            System.out.printf("%-10d%-15s%-15d%-15s", stt, s.getName(), s.getSemester(), s.getCourseName());

            System.out.println("");
            stt++;
        }
    }

    public ArrayList<Student> getStudentListByName(ArrayList<Student> listStudent, String name_to_find) {

        ArrayList<Student> listStudentByName = new ArrayList<Student>();

        for (Student s : listStudent) {

            if (s.getName().contains(name_to_find)) {
                listStudentByName.add(s);
            }
        }
        return listStudentByName;
    }

    public void updateOrDeleteStudentByID(ArrayList<Student> list) {

        String id_to_find = Validation.inputString("Input a id to find student in list: ");

        Student findedStudent = searchStudentByID(list, id_to_find);

        if (findedStudent == null) {
            System.out.println("Student does not exist in system");

        } else {

            boolean option = Validation.checkUpdateDelete("Do you want to update (U) or delete (D) student");
            if (option) {

                updateStudent(findedStudent, list);
            } else {

                deleteStudent(findedStudent, list);
            }
        }

    }

    public Student searchStudentByID(List<Student> list, String id_to_find) {

        for (Student s : list) {

            if (s.getId().equals(id_to_find)) {
                return s;
            }
        }

        return null;
    }

    public void updateStudent(Student findedStudent, ArrayList<Student> list) {

        System.out.println("Data student before update: ");
        findedStudent.printData();

        System.out.println("");

        String id = Validation.checkId("Enter id student: ", list);

        String name = Validation.inputString("Enter name student: ");

        int semester = Validation.checkInt("Enter semester student", 1, 9);

        String courseName = Validation.checkCourseName("Enter course name: ");

        findedStudent.setId(id);
        findedStudent.setCourseName(courseName);
        findedStudent.setName(name);
        findedStudent.setSemester(semester);

        System.out.println("Update successful");

    }

    public void deleteStudent(Student findedStudent, ArrayList<Student> list) {
        list.remove(findedStudent);
        System.out.println("Delete successful");
    }

    public void displayReport(ArrayList<Student> studentList) {
        ArrayList<Report> listReport = getReportList(studentList);
        for (Report report : listReport) {
            report.printData();
            System.out.println("");
        }

    }

    public ArrayList<Report> getReportList(ArrayList<Student> studentList) {
        ArrayList<Report> listReport = new ArrayList<>();
        HashMap<String, Integer> reports = new HashMap<>();
        for (Student student : studentList) {
            String key = student.getName() + "-" + student.getCourseName();
            if (reports.containsKey(key)) {
                int old_total = reports.get(key);
                reports.put(key, old_total + 1);
            } else {
                reports.put(key, 1);
            }
        }
        Set keys = reports.keySet();
        for (Object key : keys) {

            String[] data = key.toString().split("-");
            String studentName = data[0];
            String courseName = data[1];
            int totalCourse = reports.get(key);
            Report newReport = new Report(studentName, courseName, totalCourse);
            listReport.add(newReport);
        }
        return listReport;
    }

}
