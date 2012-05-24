package t.tools.xml;

public class Room {
    private int roomNumber;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public Room() {
    }
    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    @Override
    public String toString() {
        return "Room: " + this.roomNumber;
    }
    
}
