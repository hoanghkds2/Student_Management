package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.Student;

public class Validation {

    private static final Scanner sc = new Scanner(System.in);

    public static String inputString(String msg) {

        while (true) {

            System.out.println(msg);
            String input_raw = sc.nextLine();

            if (input_raw == null || input_raw.length() == 0) {

                System.err.println("Must input a string not empty !!!");
                System.out.println("Please enter again!");
                continue;
            }
            return input_raw;
        }
    }

    public static int checkInt(String msg, int min, int max) {

        while (true) {

            String input_raw = inputString(msg);

            try {

                int input = Integer.parseInt(input_raw);

                if (input < min || input > max) {
                    System.err.println("Must input a number from " + min + "to " + max);
                    continue;
                }
                return input;
            } catch (NumberFormatException e) {

                System.err.println("Must enter a number");
                continue;
            }

        }
    }

    public static String checkId(String msg, ArrayList<Student> list) {

        while (true) {

            int flag = 0;

            String id = inputString(msg);

            for (Student item : list) {
                if (item.getId().equals(id)) {
                    System.err.println("Id existed!!Please enter again");
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                continue;
            }
            return id;
        }

    }

    public static String checkCourseName(String msg) {

        while (true) {

            String courseName = inputString(msg);
            if (!(courseName.equals("Java") || courseName.equals(".Net") || courseName.equals("C/C++"))) {
                System.err.println("Course name must be one in three course below: Java, .Net, C/C++");
                continue;
            }
            return courseName;
        }
    }

    public static boolean checkYesNo(String msg) {

        while (true) {
            String input = inputString(msg);
            if (input.equalsIgnoreCase("Y")) {
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.err.println("Must input Y or N to select option");
                continue;
            }
        }
    }

    public static boolean checkUpdateDelete(String msg) {
        while (true) {
            String input = inputString(msg);
            if (input.equalsIgnoreCase("U")) {
                return true;
            } else if (input.equalsIgnoreCase("D")) {
                return false;
            } else {
                System.err.println("Must input U or D to select option");
                continue;
            }
        }
    }

}
