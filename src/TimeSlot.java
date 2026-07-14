import java.util.HashMap;
import java.util.Map;

public class TimeSlot {
    private static final Map<Integer, String> SLOT_TIMES = new HashMap<Integer, String>();

    static {
        SLOT_TIMES.put(1, "8:00 - 9:00");
        SLOT_TIMES.put(2, "9:00 - 10:00");
        SLOT_TIMES.put(3, "10:00 - 11:00");
        SLOT_TIMES.put(4, "11:00 - 12:00");
        SLOT_TIMES.put(5, "12:30 - 1:30");
        SLOT_TIMES.put(6, "1:30 - 2:30");
    }

    private final Day day;
    private final int slotNumber;

    public TimeSlot(Day day, int slotNumber) {
        if (day == null) {
            throw new IllegalArgumentException("Day cannot be null.");
        }
        if (slotNumber < 1 || slotNumber > 6) {
            throw new IllegalArgumentException("Slot number must be between 1 and 6.");
        }

        this.day = day;
        this.slotNumber = slotNumber;
    }

    public Day getDay() {
        return day;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public String getTimeRange() {
        return SLOT_TIMES.get(slotNumber);
    }

    @Override
    public String toString() {
        return day + " | Slot " + slotNumber + " | " + getTimeRange();
    }
}
