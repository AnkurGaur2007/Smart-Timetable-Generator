public class Lecture {
    private final Subject subject;
    private final Room room;
    private final TimeSlot timeSlot;

    public Lecture(Subject subject, Room room, TimeSlot timeSlot) {
        if (subject == null) {
            throw new IllegalArgumentException("Subject cannot be null.");
        }
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        if (timeSlot == null) {
            throw new IllegalArgumentException("Time slot cannot be null.");
        }

        // Store objects instead of repeating their data in Strings.
        this.subject = subject;
        this.room = room;
        this.timeSlot = timeSlot;
    }

    public Subject getSubject() {
        return subject;
    }

    public Room getRoom() {
        return room;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    @Override
    public String toString() {
        return "--------------------------------------------------\n" +
               "Subject : " + subject.getSubjectName() + "\n\n" +
               "Faculty : " + subject.getFacultyName() + "\n\n" +
               "Room    : " + room.getRoomName() + "\n\n" +
               "Day     : " + timeSlot.getDay() + "\n\n" +
               "Slot    : " + timeSlot.getSlotNumber() + "\n\n" +
               "Time    : " + timeSlot.getTimeRange() + "\n" +
               "--------------------------------------------------";
    }
}
