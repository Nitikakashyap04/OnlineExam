import java.util.*;

class User {
    String username;
    String password;
    String name;

    User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    void updateProfile(Scanner sc) {
        System.out.print("Enter new name: ");
        this.name = sc.nextLine();
        System.out.print("Enter new password: ");
        this.password = sc.nextLine();
        System.out.println("Profile updated successfully!\n");
    }
}

class Exam {
    Map<String, String> questions = new LinkedHashMap<>();
    Map<String, String> options = new LinkedHashMap<>();
    int timer = 60; // in seconds

    Exam() {
        questions.put("Java is ______?", "b");
        options.put("Java is ______?", "a) Platform dependent\nb) Platform independent\nc) Both\nd) None");

        questions.put("Which one is not a Java feature?", "c");
        options.put("Which one is not a Java feature?", "a) Object-Oriented\nb) Portable\nc) Use of pointers\nd) Dynamic");

        questions.put("Which company developed Java?", "a");
        options.put("Which company developed Java?", "a) Sun Microsystems\nb) Microsoft\nc) Apple\nd) Oracle");
    }

    void startExam(Scanner sc) {
        int correct = 0;
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\nTime's up! Auto-submitting the exam...");
                System.exit(0);
            }
        };
        Timer t = new Timer();
        t.schedule(task, timer * 1000);

        for (String q : questions.keySet()) {
            System.out.println("\n" + q);
            System.out.println(options.get(q));
            System.out.print("Enter your answer (a/b/c/d): ");
            String ans = sc.nextLine().trim().toLowerCase();
            if (ans.equals(questions.get(q))) {
                correct++;
            }
        }
        t.cancel(); // cancel the timer if completed early
        System.out.println("\nExam Submitted Successfully!");
        System.out.println("You scored: " + correct + "/" + questions.size() + "\n");
    }
}

public class onlineexam{
    static Scanner sc = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static User currentUser = null;

    public static void main(String[] args) {
        // Pre-adding one user
        users.add(new User("user1", "pass123", "Student"));

        System.out.println("=== Welcome to Online Examination System ===");
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    loggedIn = login();
                    break;
                case 2:
                    register();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        int choice;
        do {
            System.out.println("\n1. Update Profile and Password");
            System.out.println("2. Start Exam");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    currentUser.updateProfile(sc);
                    break;
                case 2:
                    Exam exam = new Exam();
                    exam.startExam(sc);
                    break;
                case 3:
                    System.out.println("Logging out... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid Choice. Try Again!");
            }
        } while (choice != 3);
    }

    static boolean login() {
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        for (User u : users) {
            if (u.username.equals(uname) && u.password.equals(pass)) {
                currentUser = u;
                System.out.println("\nLogin Successful! Welcome, " + currentUser.name + "\n");
                return true;
            }
        }
        System.out.println("\nLogin Failed. Please try again.");
        return false;
    }

    static void register() {
        System.out.print("Enter new username: ");
        String uname = sc.nextLine();
        // Check if username already exists
        for (User u : users) {
            if (u.username.equals(uname)) {
                System.out.println("Username already exists! Try a different one.");
                return;
            }
        }
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        users.add(new User(uname, pass, name));
        System.out.println("Registration successful! You can now login.\n");
    }
}

