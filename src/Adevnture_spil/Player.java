package Adevnture_spil;

public class Player {
    private Room currentRoom;

    public Player(Room startRoom) {
        currentRoom = startRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean move(String direction) {
        currentRoom.markTried(direction);

        Room desiredRoom = switch (direction) {
            case "north" -> currentRoom.getNorth();
            case "south" -> currentRoom.getSouth();
            case "east"  -> currentRoom.getEast();
            case "west"  -> currentRoom.getWest();
            default -> null;
        };

        if (desiredRoom != null) {
            currentRoom = desiredRoom;
            return true;
        }
        return false;
    }
}