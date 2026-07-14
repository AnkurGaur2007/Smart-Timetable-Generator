import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputManager {
    private final Scanner scanner;

    public InputManager() {
        this.scanner = new Scanner(System.in);
    }

    public List<Subject> readSubjects() {
        int numberOfSubjects = readPositiveInteger("Enter number of subjects: ");
        List<Subject> subjects = new ArrayList<Subject>();

        for (int i = 1; i <= numberOfSubjects; i++) {
            ConsoleHelper.printSection("Subject " + i);

            String subjectName = readNonEmptyText("Subject Name: ");
            String facultyName = readNonEmptyText("Faculty Name: ");
            int lecturesPerWeek = readPositiveInteger("Lectures Per Week: ");
            boolean lab = readYesNo("Is this a Lab? (Y/N): ");

            subjects.add(new Subject(subjectName, facultyName, lecturesPerWeek, lab));
        }

        return subjects;
    }

    public List<Room> createDefaultRooms() {
        List<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room("R101", 60, false));
        rooms.add(new Room("R102", 60, false));
        rooms.add(new Room("R103", 60, false));
        rooms.add(new Room("R104", 60, false));
        rooms.add(new Room("LAB1", 30, true));
        return rooms;
    }

    public int readMenuChoice() {
        while (true) {
            System.out.print("Choose an option: ");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 3) {
                    return choice;
                }
            } catch (NumberFormatException exception) {
                // Keep asking until the user enters a valid menu option.
            }

            System.out.println("Please enter 1, 2, or 3.");
        }
    }

    private int readPositiveInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException exception) {
                // Keep asking until the user enters a valid number.
            }

            System.out.println("Please enter a number greater than 0.");
        }
    }

    private String readNonEmptyText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("This field cannot be empty.");
        }
    }

    private boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Y")) {
                return true;
            }

            if (input.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("Please enter Y or N.");
        }
    }
}
