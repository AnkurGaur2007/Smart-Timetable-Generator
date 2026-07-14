import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Timetable {
    private final List<Lecture> lectures;

    public Timetable() {
        this.lectures = new ArrayList<Lecture>();
    }

    public void addLecture(Lecture lecture) {
        if (lecture == null) {
            throw new IllegalArgumentException("Lecture cannot be null.");
        }
        lectures.add(lecture);
    }

    public void removeLecture(Lecture lecture) {
        lectures.remove(lecture);
    }

    public void clear() {
        lectures.clear();
    }

    public boolean containsLecture(Lecture lecture) {
        if (lecture == null) {
            return false;
        }

        for (Lecture existingLecture : lectures) {
            boolean sameSubject = existingLecture.getSubject().getSubjectName().equals(lecture.getSubject().getSubjectName());
            boolean sameFaculty = existingLecture.getSubject().getFacultyName().equals(lecture.getSubject().getFacultyName());
            boolean sameRoom = existingLecture.getRoom().getRoomName().equals(lecture.getRoom().getRoomName());
            boolean sameDay = existingLecture.getTimeSlot().getDay() == lecture.getTimeSlot().getDay();
            boolean sameSlot = existingLecture.getTimeSlot().getSlotNumber() == lecture.getTimeSlot().getSlotNumber();

            if (sameSubject && sameFaculty && sameRoom && sameDay && sameSlot) {
                return true;
            }
        }

        return false;
    }

    public List<Lecture> getLectures() {
        return Collections.unmodifiableList(lectures);
    }

    public boolean isEmpty() {
        return lectures.isEmpty();
    }

    public int getLectureCount() {
        return lectures.size();
    }
}
