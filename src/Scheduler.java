import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private final List<Subject> subjects;
    private final List<Room> rooms;
    private final List<TimeSlot> timeSlots;
    private final Timetable timetable;
    private final ConstraintChecker constraintChecker;
    private final List<Subject> lecturesToSchedule;

    private long recursiveCalls;
    private long backtracks;
    private long placementAttempts;
    private long failedPlacements;
    private long executionTime;

    public Scheduler(List<Subject> subjects, List<Room> rooms, List<TimeSlot> timeSlots, Timetable timetable, ConstraintChecker constraintChecker) {
        if (subjects == null) {
            throw new IllegalArgumentException("Subjects list cannot be null.");
        }
        if (rooms == null) {
            throw new IllegalArgumentException("Rooms list cannot be null.");
        }
        if (timeSlots == null) {
            throw new IllegalArgumentException("Time slots list cannot be null.");
        }
        if (timetable == null) {
            throw new IllegalArgumentException("Timetable cannot be null.");
        }
        if (constraintChecker == null) {
            throw new IllegalArgumentException("Constraint checker cannot be null.");
        }

        this.subjects = new ArrayList<Subject>(subjects);
        this.rooms = new ArrayList<Room>(rooms);
        this.timeSlots = new ArrayList<TimeSlot>(timeSlots);
        this.timetable = timetable;
        this.constraintChecker = constraintChecker;
        this.lecturesToSchedule = buildLecturesToSchedule();
    }

    public boolean generateTimetable() {
        resetStatistics();
        timetable.clear();

        long startTime = System.currentTimeMillis();
        boolean success = schedule(0);
        long endTime = System.currentTimeMillis();

        executionTime = endTime - startTime;
        return success;
    }

    public void printStatistics(boolean successful) {
        System.out.println();
        System.out.println("==============================");
        if (successful) {
            System.out.println("Generation Successful");
        } else {
            System.out.println("Generation Failed");
        }
        System.out.println();
        System.out.println("Recursive Calls : " + recursiveCalls);
        System.out.println("Backtracks : " + backtracks);
        System.out.println("Placement Attempts : " + placementAttempts);
        System.out.println("Failed Placements : " + failedPlacements);
        System.out.println("Execution Time : " + executionTime + " ms");
        System.out.println("==============================");
    }

    public long getRecursiveCalls() {
        return recursiveCalls;
    }

    public long getBacktracks() {
        return backtracks;
    }

    public long getPlacementAttempts() {
        return placementAttempts;
    }

    public long getFailedPlacements() {
        return failedPlacements;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    private List<Subject> buildLecturesToSchedule() {
        List<Subject> result = new ArrayList<Subject>();

        for (Subject subject : subjects) {
            for (int count = 0; count < subject.getLecturesPerWeek(); count++) {
                result.add(subject);
            }
        }

        return result;
    }

    private boolean schedule(int lectureIndex) {
        // Base case: all lectures have been placed successfully.
        recursiveCalls++;
        if (lectureIndex == lecturesToSchedule.size()) {
            return true;
        }

        Subject currentSubject = lecturesToSchedule.get(lectureIndex);

        // Try every time slot, then every room, until we find a valid placement.
        for (TimeSlot timeSlot : timeSlots) {
            for (Room room : rooms) {
                if (constraintChecker.canPlaceLecture(timetable, currentSubject, room, timeSlot)) {
                    Lecture lecture = new Lecture(currentSubject, room, timeSlot);
                    timetable.addLecture(lecture);
                    placementAttempts++;

                    // Recursive step: move on to the next lecture.
                    if (schedule(lectureIndex + 1)) {
                        return true;
                    }

                    // Backtracking step: remove the lecture and try another option.
                    timetable.removeLecture(lecture);
                    backtracks++;
                } else {
                    failedPlacements++;
                }
            }
        }

        return false;
    }

    private void resetStatistics() {
        recursiveCalls = 0;
        backtracks = 0;
        placementAttempts = 0;
        failedPlacements = 0;
        executionTime = 0;
    }
}
