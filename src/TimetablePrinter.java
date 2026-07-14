import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimetablePrinter {
    private static final int DAY_WIDTH = 10;
    private static final int SLOT_WIDTH = 18;
    private static final int LUNCH_WIDTH = 5;
    private static final String COLUMN_SEPARATOR = " | ";

    public void printTimetable(Timetable timetable) {
        if (timetable == null) {
            throw new IllegalArgumentException("Timetable cannot be null.");
        }

        if (timetable.isEmpty()) {
            System.out.println("No lectures scheduled.");
            return;
        }

        List<Lecture> sortedLectures = new ArrayList<Lecture>(timetable.getLectures());
        Collections.sort(sortedLectures, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture firstLecture, Lecture secondLecture) {
                int dayComparison = firstLecture.getTimeSlot().getDay().compareTo(secondLecture.getTimeSlot().getDay());
                if (dayComparison != 0) {
                    return dayComparison;
                }

                int slotComparison = Integer.compare(
                        firstLecture.getTimeSlot().getSlotNumber(),
                        secondLecture.getTimeSlot().getSlotNumber());
                if (slotComparison != 0) {
                    return slotComparison;
                }

                return firstLecture.getRoom().getRoomName().compareTo(secondLecture.getRoom().getRoomName());
            }
        });

        Map<Day, Map<Integer, List<Lecture>>> timetableGrid = buildGrid(sortedLectures);

        printSeparatorLine();
        printHeaderRow();
        printSeparatorLine();
        for (Day day : Day.values()) {
            printDayBlock(day, timetableGrid.get(day));
            printSeparatorLine();
        }
    }

    private Map<Day, Map<Integer, List<Lecture>>> buildGrid(List<Lecture> lectures) {
        Map<Day, Map<Integer, List<Lecture>>> grid = new EnumMap<Day, Map<Integer, List<Lecture>>>(Day.class);

        for (Day day : Day.values()) {
            Map<Integer, List<Lecture>> daySlots = new HashMap<Integer, List<Lecture>>();
            for (int slotNumber = 1; slotNumber <= 6; slotNumber++) {
                daySlots.put(slotNumber, new ArrayList<Lecture>());
            }
            grid.put(day, daySlots);
        }

        for (Lecture lecture : lectures) {
            Day day = lecture.getTimeSlot().getDay();
            int slotNumber = lecture.getTimeSlot().getSlotNumber();
            grid.get(day).get(slotNumber).add(lecture);
        }

        return grid;
    }

    private void printHeaderRow() {
        String row = formatCell("Day", DAY_WIDTH)
                + COLUMN_SEPARATOR + formatCell("Slot 1", SLOT_WIDTH)
                + COLUMN_SEPARATOR + formatCell("Slot 2", SLOT_WIDTH)
                + COLUMN_SEPARATOR + formatCell("Slot 3", SLOT_WIDTH)
                + COLUMN_SEPARATOR + formatCell("Slot 4", SLOT_WIDTH)
                + COLUMN_SEPARATOR + formatCell("Lunch", LUNCH_WIDTH)
                + COLUMN_SEPARATOR + formatCell("Slot 5", SLOT_WIDTH)
                + COLUMN_SEPARATOR + formatCell("Slot 6", SLOT_WIDTH);

        System.out.println(row);
    }

    private void printDayBlock(Day day, Map<Integer, List<Lecture>> daySlots) {
        String[] dayCellLines = new String[3];
        dayCellLines[0] = formatCell(dayName(day), DAY_WIDTH);
        dayCellLines[1] = formatCell("", DAY_WIDTH);
        dayCellLines[2] = formatCell("", DAY_WIDTH);

        String[][] slotCellLines = new String[7][3];
        slotCellLines[0] = createSlotCellLines(daySlots.get(1));
        slotCellLines[1] = createSlotCellLines(daySlots.get(2));
        slotCellLines[2] = createSlotCellLines(daySlots.get(3));
        slotCellLines[3] = createSlotCellLines(daySlots.get(4));
        slotCellLines[4] = createLunchCellLines();
        slotCellLines[5] = createSlotCellLines(daySlots.get(5));
        slotCellLines[6] = createSlotCellLines(daySlots.get(6));

        for (int lineIndex = 0; lineIndex < 3; lineIndex++) {
            StringBuilder row = new StringBuilder();
            row.append(dayCellLines[lineIndex]);
            row.append(COLUMN_SEPARATOR).append(slotCellLines[0][lineIndex]);
            row.append(COLUMN_SEPARATOR).append(slotCellLines[1][lineIndex]);
            row.append(COLUMN_SEPARATOR).append(slotCellLines[2][lineIndex]);
            row.append(COLUMN_SEPARATOR).append(slotCellLines[3][lineIndex]);
            row.append(COLUMN_SEPARATOR).append(slotCellLines[4][lineIndex]);
            row.append(COLUMN_SEPARATOR).append(slotCellLines[5][lineIndex]);
            row.append(COLUMN_SEPARATOR).append(slotCellLines[6][lineIndex]);
            System.out.println(row);
        }
    }

    private String[] createSlotCellLines(List<Lecture> lectures) {
        String[] lines = new String[3];

        if (lectures == null || lectures.isEmpty()) {
            lines[0] = formatCell("----", SLOT_WIDTH);
            lines[1] = formatCell("", SLOT_WIDTH);
            lines[2] = formatCell("", SLOT_WIDTH);
            return lines;
        }

        lines[0] = formatCell(joinLectureField(lectures, 0), SLOT_WIDTH);
        lines[1] = formatCell(joinLectureField(lectures, 1), SLOT_WIDTH);
        lines[2] = formatCell(joinLectureField(lectures, 2), SLOT_WIDTH);
        return lines;
    }

    private String[] createLunchCellLines() {
        String[] lines = new String[3];
        lines[0] = formatCell("", LUNCH_WIDTH);
        lines[1] = formatCell("", LUNCH_WIDTH);
        lines[2] = formatCell("", LUNCH_WIDTH);
        return lines;
    }

    private String joinLectureField(List<Lecture> lectures, int fieldType) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < lectures.size(); index++) {
            Lecture lecture = lectures.get(index);
            String value = getLectureFieldValue(lecture, fieldType);

            if (builder.length() > 0) {
                builder.append(" / ");
            }
            builder.append(value);
        }

        return builder.toString();
    }

    private String getLectureFieldValue(Lecture lecture, int fieldType) {
        if (fieldType == 0) {
            return lecture.getRoom().getRoomName();
        }
        if (fieldType == 1) {
            return lecture.getSubject().getSubjectName();
        }
        return lecture.getSubject().getFacultyName();
    }

    private String dayName(Day day) {
        if (day == Day.MONDAY) {
            return "Monday";
        }
        if (day == Day.TUESDAY) {
            return "Tuesday";
        }
        if (day == Day.WEDNESDAY) {
            return "Wednesday";
        }
        if (day == Day.THURSDAY) {
            return "Thursday";
        }
        return "Friday";
    }

    private String formatCell(String text, int width) {
        String safeText = text == null ? "" : text;
        if (safeText.length() > width) {
            safeText = safeText.substring(0, width);
        }
        return String.format("%-" + width + "s", safeText);
    }

    private void printSeparatorLine() {
        System.out.println(repeat('-', buildTableWidth()));
    }

    private int buildTableWidth() {
        return DAY_WIDTH
                + (COLUMN_SEPARATOR.length() * 7)
                + (SLOT_WIDTH * 6)
                + LUNCH_WIDTH;
    }

    private String repeat(char character, int count) {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < count; index++) {
            builder.append(character);
        }
        return builder.toString();
    }
}
