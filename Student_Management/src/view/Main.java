package view;

import controller.Manager;
import controller.Validation;
import java.util.ArrayList;
import model.Student;

public class Main {

    public static void main(String[] args) {

        Validation inputValid = new Validation();
        Manager manager = new Manager();
        ArrayList<Student> listStudent = new ArrayList<>();

        manager.initData(listStudent);

        while (true) {

            manager.showMenu();

            int choice = inputValid.checkInt("Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program.", 1, 5);

            switch (choice) {
                case 1:

                    manager.createStudent(listStudent);
                    break;
                case 2:

                    manager.findAndSortStudent(listStudent);
                    break;
                case 3:

                    manager.updateOrDeleteStudentByID(listStudent);
                    break;
                case 4:

                    manager.displayReport(listStudent);
                    break;
                case 5:
                    return;

                default:
                    throw new AssertionError();
            }
        }
    }
}
