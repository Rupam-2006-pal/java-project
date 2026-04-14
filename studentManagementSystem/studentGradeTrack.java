package studentManagementSystem;
import java.util.*;

public class studentGradeTrack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentSet studentSet = new StudentSet();

        System.out.println("=====================================");
        System.out.println("  Welcome to Student Grade Tracker");
        System.out.println("=====================================");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Initialize Student Database");
            System.out.println("2. Add New Student(s)");
            System.out.println("3. Delete a Student");
            System.out.println("4. Update a Student");
            System.out.println("5. View All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    if (!studentSet.isEmpty()) {
                        System.out.println("\tDatabase already initialized. Use option 2 to add more students.");
                    } else {
                        studentSet.putInitialStudentData();
                        System.out.println("Student database initialized successfully.\n");
                    }
                    break;
                case "2":
                    System.out.print("\tHow many students to add: ");
                    try {
                        int num = Integer.parseInt(sc.nextLine().trim());
                        // int num = sc.nextInt();  // alternative
                        if (num > 0) {
                            studentSet.insertStudent(num);
                            System.out.println("\t[+] " + num + " student(s) added successfully.");
                        } else {
                            System.out.println("\tInvalid number. Please enter a positive integer.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\tInvalid input. Please enter a number.");
                    }
                    break;
                case "3":
                    System.out.print("\tEnter Student ID to delete: ");
                    String delId = sc.nextLine().trim();
                    studentSet.deleteStudent(delId);
                    break;
                case "4":
                    System.out.print("\tEnter Student ID to update: ");
                    String upId = sc.nextLine().trim();
                    studentSet.updateStudent(upId);
                    break;
                case "5":
                    studentSet.showAllStudentData();
                    break;
                case "6":
                    System.out.println("Exiting... Thank you for using Student Grade Tracker!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select 1-6.");
            }
        }
    }
}

interface StudentProto {
    void setMarks();
    String getId();
    void updateName(String newName);
    void updateMarks(int idx, float newMarks);
    void showData();
}

interface StudentSetProto {
    void putInitialStudentData();
    void insertStudent(int num);
    void deleteStudent(String id);
    void updateStudent(String id);
    void showAllStudentData();
}

class Student implements StudentProto{
    private String name;
    private String id;
    private String grade;
    private float[] marks;
    private float[] a_h_l;

    public Student(String name) {
        this.name = name;
        marks = new float[4];  // marks of 4 subject
        a_h_l = new float[3];  // for age-height-lowest marks
        id = generateStudentId(5);
        grade = "";
    }

    public void setMarks() {
        Scanner sc = new Scanner(System.in); // create a new Scanner object

        System.out.println("\t\tEnter the marks for four subjects (out of 100) :");
        System.out.print("\t\t\tMath: ");  marks[0] = sc.nextFloat();
        System.out.print("\t\t\tComputer Science: ");  marks[1] = sc.nextFloat();
        System.out.print("\t\t\tPhysics: ");  marks[2] = sc.nextFloat();
        System.out.print("\t\t\tFinance: ");  marks[3] = sc.nextFloat();

        calculateAHL();

    }

    private void calculateAHL() {
        float h, l, sum;
        h = l = sum = marks[0];
        for(int i=1; i<4; i++) {
            if(h < marks[i]) h = marks[i];
            if(l > marks[i]) l = marks[i];
            sum += marks[i];
        }
        a_h_l[0] = (sum/4);
        a_h_l[1] = h;
        a_h_l[2] = l;

        float avg = a_h_l[0];
        if(90 <= avg && avg <=100) grade = "A+";
        else if(80 <= avg && avg <90) grade = "A";
        else if(70 <= avg && avg <80) grade = "B+";
        else if(60 <= avg && avg <70) grade = "B";
        else if(40 <= avg && avg <60) grade = "C";
        else grade = "SNC";
    }

    // getter
    public String getId() { return id; }
    public String getName() { return name; }
    public String getGrade() { return grade; }
    public float[] getMarks() { return marks.clone(); }
    public float[] getAHL() { return a_h_l.clone(); }

    // individual updater
    public void updateName(String newName) {
        name = newName;
    }
    public void updateMarks(int idx, float newMarks) {
        marks[idx] = newMarks;
        calculateAHL();
    }

    public void showData() {
        String line = "\t===============================================";
        System.out.println(line);
        System.out.printf("\tStudent ID   : %s\n", id);
        System.out.printf("\tName         : %s\n", name);
        System.out.printf("\tGrade        : %s\n", grade);
        System.out.printf("\tAverage      : %.2f\n", a_h_l[0]);
        System.out.println("\t-----------------------------------------------");
        System.out.printf("\t| %-18s | %7s |\n", "Subject", "Marks");
        System.out.println("\t-----------------------------------------------");
        System.out.printf("\t| %-18s | %7.2f |\n", "Math", marks[0]);
        System.out.printf("\t| %-18s | %7.2f |\n", "Computer Science", marks[1]);
        System.out.printf("\t| %-18s | %7.2f |\n", "Physics", marks[2]);
        System.out.printf("\t| %-18s | %7.2f |\n", "Finance", marks[3]);
        System.out.println("\t-----------------------------------------------");
        System.out.printf("\t| %-18s | %7.2f |\n", "Highest mark", a_h_l[1]);
        System.out.printf("\t| %-18s | %7.2f |\n", "Lowest mark", a_h_l[2]);
        System.out.println(line);
    }


    // generate any length alpha-numeric ID
    private static String generateStudentId(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();  // create a Random object
        String id = "";

        for (int i = 0; i < length; i++) {
            id += (chars.charAt(random.nextInt(chars.length())));  // add random char from cgars String
        }

        return id.toString();
    }
}

class StudentSet implements StudentSetProto {
    private ArrayList<Student> studentSet;

    public StudentSet() {
        studentSet = new ArrayList<>();
    }

    // to input a chunk of student data for first time
    public void putInitialStudentData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\tEnter the number of students to initialize: ");
        int num = sc.nextInt();
        sc.nextLine(); // consume newline
        insertStudent(num);
    }

    public void insertStudent(int num) {
        Scanner sc = new Scanner(System.in);  // creat a new Scanner object

        for(int i=0; i<num; i++) {
            System.out.println("\t" + (i+1) + ".");

            System.out.print("\t\tName: ");
            Student s = new Student(sc.nextLine());
            s.setMarks();

            studentSet.add(s);
        }
    }

    public void deleteStudent(String id) {
        int len = studentSet.size();
        int i;
        for(i=0; i<len; i++) {
            if(id.equals(studentSet.get(i).getId())) {
                studentSet.remove(i);
                System.out.println("\t[-] Student deleted successfully.");
                break;
            }
        }
        if(i == len) {
            System.out.println("\t[!] Failed to delete. Student not found.");
        }
    }

    public void updateStudent(String id) {
        int len = studentSet.size(), i;
        for(i=0; i<len; i++) {
            if(studentSet.get(i).getId().equals(id)) {  // student found
                Student s = studentSet.get(i);
                s.showData();

                Scanner sc = new Scanner(System.in);
                System.out.println("\t\t1. Update name\n\t\t2. Update marks\n\t\t3. Not now");
                System.out.print("\t\tChoose: ");
                String ch = sc.nextLine();

                switch(ch) {
                    case "1":
                        System.out.println("\t\t\tEnter the new name of student " + id + ": ");
                        String newName = sc.nextLine();
                        s.updateName(newName);
                    break;
                    case "2":
                        System.out.println("\t\t\t1. Math\n\t\t\t2. Computer Science\n\t\t\t3. Physics\n\t\t\t4. Finance");
                        System.out.print("\t\t\tChoose: ");
                        String ch1 = sc.nextLine();
                        if(ch1.equals("1") || ch1.equals("2") || ch1.equals("3") || ch1.equals("4")) {
                            System.out.print("\t\t\t\tEnter new marks :");
                            float newMarks = sc.nextFloat();
                            s.updateMarks(Integer.parseInt(ch1) - 1, newMarks);
                        }
                        else {
                            System.out.println("\t\t\t\tYou choose wrong!");
                        }
                    break;
                    case "3": break;
                    default: System.out.println("\t\t\tYou choose wrong!");
                }

                break;
            }
        }
        if(i == len) {
            System.out.println("\t\t[!] Student not found.");
        }
    }

    public boolean isEmpty() {
        return studentSet.isEmpty();
    }

    public void showAllStudentData() {
        int recordLen = studentSet.size();
        for(int i=0; i<recordLen; i++) {
            studentSet.get(i).showData();
        }
    }
}