import java.util.List;
public class Main {

    public static void main(String[] args) {
        ConsoleHelper.printTitle("SMART TIMETABLE GENERATOR");

        InputManager inputManager = new InputManager();
        TimeSlotGenerator timeSlotGenerator = new TimeSlotGenerator();
        Timetable timetable = new Timetable();
        ConstraintChecker constraintChecker = new ConstraintChecker();
        TimetablePrinter timetablePrinter = new TimetablePrinter();

        List<Subject> subjects = inputManager.readSubjects();
        List<Room> rooms = inputManager.createDefaultRooms();
        List<TimeSlot> timeSlots = timeSlotGenerator.generateAllTimeSlots();

        timeSlotGenerator.shuffleTimeSlots(timeSlots);

        while (true) {
            ConsoleHelper.printBlankLine();
            ConsoleHelper.printSeparator();
            System.out.println("1. Generate Timetable");
            System.out.println("2. Generate New Timetable");
            System.out.println("3. Exit");
            ConsoleHelper.printSeparator();

            int choice = inputManager.readMenuChoice();

            if (choice == 3) {
                System.out.println("Thank you for using Smart Timetable Generator.");
                return;
            }

            if (choice == 2) {
                timetable.clear();
                timeSlotGenerator.shuffleTimeSlots(timeSlots);
            }

            Scheduler scheduler = new Scheduler(subjects, rooms, timeSlots, timetable, constraintChecker);
            boolean generated = scheduler.generateTimetable();

            if (generated) {
                timetablePrinter.printTimetable(timetable);
            } else {
                System.out.println("No valid timetable could be generated.");
            }

            scheduler.printStatistics(generated);
        }
    }
}
