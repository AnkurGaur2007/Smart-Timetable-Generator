public class ConstraintChecker {


    public boolean canPlaceLecture(Timetable timetable, Subject subject, Room room, TimeSlot timeSlot) {
        if (timetable == null) {
            throw new IllegalArgumentException("Timetable cannot be null.");
        }
        if (subject == null) {
            throw new IllegalArgumentException("Subject cannot be null.");
        }
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        if (timeSlot == null) {
            throw new IllegalArgumentException("Time slot cannot be null.");
        }

        return isRoomAvailable(timetable, room, timeSlot)
                && isFacultyAvailable(timetable, subject, timeSlot)
                && isValidRoomType(subject, room)
                && isNotDuplicate(timetable, subject, room, timeSlot)
                && isSubjectAlreadyScheduledToday(timetable, subject, timeSlot)
                && isSameSubjectRepeatedInRoom(timetable, subject, room, timeSlot);
    }

    private boolean isRoomAvailable(Timetable timetable, Room room, TimeSlot timeSlot) {
        // A room cannot be used by two lectures at the same time.
        for (Lecture lecture : timetable.getLectures()) {
            boolean sameRoom = lecture.getRoom().getRoomName().equals(room.getRoomName());
            boolean sameDay = lecture.getTimeSlot().getDay() == timeSlot.getDay();
            boolean sameSlot = lecture.getTimeSlot().getSlotNumber() == timeSlot.getSlotNumber();

            if (sameRoom && sameDay && sameSlot) {
                return false;
            }
        }
        return true;
    }

    private boolean isFacultyAvailable(Timetable timetable, Subject subject, TimeSlot timeSlot) {
        // A faculty member cannot teach two lectures at the same day and slot.
        for (Lecture lecture : timetable.getLectures()) {
            boolean sameFaculty = lecture.getSubject().getFacultyName().equals(subject.getFacultyName());
            boolean sameDay = lecture.getTimeSlot().getDay() == timeSlot.getDay();
            boolean sameSlot = lecture.getTimeSlot().getSlotNumber() == timeSlot.getSlotNumber();

            if (sameFaculty && sameDay && sameSlot) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidRoomType(Subject subject, Room room) {
        // Lab subjects must go into lab rooms, and theory subjects must go into normal rooms.
        if (subject.isLab()) {
            return room.isLab();
        }

        return !room.isLab();
    }

    private boolean isNotDuplicate(Timetable timetable, Subject subject, Room room, TimeSlot timeSlot) {
        // Prevent the exact same lecture from being added more than once.
        for (Lecture lecture : timetable.getLectures()) {
            boolean sameSubject = lecture.getSubject().getSubjectName().equals(subject.getSubjectName());
            boolean sameRoom = lecture.getRoom().getRoomName().equals(room.getRoomName());
            boolean sameDay = lecture.getTimeSlot().getDay() == timeSlot.getDay();
            boolean sameSlot = lecture.getTimeSlot().getSlotNumber() == timeSlot.getSlotNumber();

            if (sameSubject && sameRoom && sameDay && sameSlot) {
                return false;
            }
        }
        return true;
    }

    private boolean isSubjectAlreadyScheduledToday(Timetable timetable, Subject subject, TimeSlot timeSlot) {
        // A subject should only appear once per day to keep the timetable balanced.
        for (Lecture lecture : timetable.getLectures()) {
            boolean sameSubject = lecture.getSubject().getSubjectName().equals(subject.getSubjectName());
            boolean sameDay = lecture.getTimeSlot().getDay() == timeSlot.getDay();

            if (sameSubject && sameDay) {
                return false;
            }
        }
        return true;
    }

    private boolean isSameSubjectRepeatedInRoom(Timetable timetable, Subject subject, Room room, TimeSlot timeSlot) {
        // The same subject should not keep reappearing in the same room on the same day.
        for (Lecture lecture : timetable.getLectures()) {
            boolean sameSubject = lecture.getSubject().getSubjectName().equals(subject.getSubjectName());
            boolean sameRoom = lecture.getRoom().getRoomName().equals(room.getRoomName());
            boolean sameDay = lecture.getTimeSlot().getDay() == timeSlot.getDay();

            if (sameSubject && sameRoom && sameDay) {
                return false;
            }
        }
        return true;
    }
}
