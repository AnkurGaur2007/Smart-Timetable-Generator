import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeSlotGenerator {

    public List<TimeSlot> generateAllTimeSlots() {
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

        for (Day day : Day.values()) {
            for (int slotNumber = 1; slotNumber <= 6; slotNumber++) {
                timeSlots.add(new TimeSlot(day, slotNumber));
            }
        }

        return timeSlots;
    }

    public void shuffleTimeSlots(List<TimeSlot> timeSlots) {
        if (timeSlots == null) {
            throw new IllegalArgumentException("Time slot list cannot be null.");
        }

        Collections.shuffle(timeSlots);
    }
}
