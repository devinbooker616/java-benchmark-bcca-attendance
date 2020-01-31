package app;

import java.util.*;
import java.io.*;

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("Are you a teacher or student [t/s]: ");
        String teacherStudent = sc.nextLine();
        ArrayList<Sheet> attendanceSheet = loadStudents();
        if (teacherStudent.equals("s")) {
            for (Sheet students : attendanceSheet) {
                System.out.println(students.name + ", " + students.timeOfArrival + ", " + students.day);
            }
            Sheet studentInfo = getStudent();
            attendanceSheet.add(studentInfo);
            saveStudents(attendanceSheet);
        } else {
            System.out.println(
                    "Since you are a teacher would you like to see all students are just the absent ones[All/Absent]: ");
            String tardy = sc.nextLine();
            if (tardy.equals("All")) {
                for (Sheet students : attendanceSheet) {
                    System.out.println(students.name + ", " + students.timeOfArrival + ", " + students.day);
                }
            } else if (tardy.equals("Absent")) {
                for (Sheet students : attendanceSheet) {
                    if (students.day.equals("Absent")) {
                        System.out.println(students.name);
                    } else {
                        break;
                    }
                    System.out.println("For once in a blue moon everyone was here!");
                }
            }
        }

    }

    public static Sheet getStudent() {
        System.out.print("What is your name: ");
        String name = sc.nextLine();
        String day = validDay();
        System.out.print("What time did you arrive: ");
        String timeOfArrival = sc.nextLine();
        System.out.println(name + " has arrived on " + day + " at " + timeOfArrival);
        return new Sheet(name, timeOfArrival, day);
    }

    private static void saveStudents(ArrayList<Sheet> attend) {
        try {
            FileOutputStream fileStream = new FileOutputStream("orders.ser");
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(attend);
            os.close();
        } catch (IOException ex) {
            System.out.println("Failed to save Students.");
        }
    }

    public static ArrayList<Sheet> loadStudents() {
        try {
            FileInputStream fileStream = new FileInputStream("orders.ser");
            ObjectInputStream os = new ObjectInputStream(fileStream);
            ArrayList<Sheet> attend = (ArrayList<Sheet>) os.readObject();
            os.close();
            return attend;
        } catch (IOException | ClassNotFoundException ex) {
            return new ArrayList<Sheet>();
        }
    }

    public static String validDay() {
        String[] validDays = new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Absent" };
        while (true) {
            System.out.print("What day of the work week is it: ");
            String day = sc.nextLine();
            boolean result = Arrays.stream(validDays).anyMatch(day::equals);
            if (result) {
                return day;
            } else {
                System.out.println("That's not a day in the work week");
            }

        }
    }

}
