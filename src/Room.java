public class Room {
    private String roomName;
    private int capacity;
    private boolean lab;

    public Room(String roomName, int capacity, boolean lab) {
        if (roomName == null || roomName.trim().isEmpty()) {
            throw new IllegalArgumentException("Room name cannot be null or empty.");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }

        this.roomName = roomName;
        this.capacity = capacity;
        this.lab = lab;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isLab() {
        return lab;
    }

    public String toString() {
        return "Room Name: " + roomName + ", Capacity: " + capacity + ", Lab: " + lab;
    }
}
