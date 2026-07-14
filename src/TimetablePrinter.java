import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TimetablePrinter {

    public void printTimetable(Timetable timetable) {
        if (timetable == null) {
            throw new IllegalArgumentException("Timetable cannot be null.");
        }

        ConsoleHelper.printTitle("SMART UNIVERSITY TIMETABLE");

        if (timetable.isEmpty()) {
            System.out.println("No lectures scheduled.");
            return;
        }

        List<Lecture> lectures = new ArrayList<>(timetable.getLectures());
        Collections.sort(lectures, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture firstLecture, Lecture secondLecture) {
                int dayComparison = firstLecture.getTimeSlot().getDay().compareTo(secondLecture.getTimeSlot().getDay());
                if (dayComparison != 0) {
                    return dayComparison;
                }

                int slotComparison = Integer.compare(firstLecture.getTimeSlot().getSlotNumber(), secondLecture.getTimeSlot().getSlotNumber());
                if (slotComparison != 0) {
                    return slotComparison;
                }

                return firstLecture.getRoom().getRoomName().compareTo(secondLecture.getRoom().getRoomName());
            }
        });

        Day currentDay = null;

        for (Lecture lecture : lectures) {
            if (currentDay != lecture.getTimeSlot().getDay()) {
                currentDay = lecture.getTimeSlot().getDay();
                ConsoleHelper.printSection(currentDay.toString());
            }

            printLecture(lecture);
        }
    }

    private void printLecture(Lecture lecture) {
        System.out.println("Slot " + lecture.getTimeSlot().getSlotNumber() + " (" + lecture.getTimeSlot().getTimeRange() + ")");
        System.out.println();
        System.out.println("Room     : " + lecture.getRoom().getRoomName());
        System.out.println("Subject  : " + lecture.getSubject().getSubjectName());
        System.out.println("Faculty  : " + lecture.getSubject().getFacultyName());
        ConsoleHelper.printSeparator();
    }
}
