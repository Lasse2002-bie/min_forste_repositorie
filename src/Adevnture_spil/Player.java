package Adevnture_spil;
import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private ArrayList<Item> inventory;

    public Player(Room startRoom) {
        currentRoom = startRoom;
        inventory = new ArrayList<>();
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }
    public boolean removeItem (Item item) {
        return inventory.remove(item);
    }
    public boolean takeItem(String itemName) {
        Item item = currentRoom.findItem(itemName);
        if (item != null) {
            currentRoom.removeItem(item);
            inventory.add(item);
            return true;
        }
        return false;
    }
    public Item findInventoryItem(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
    public boolean dropItem(String itemName) {
        Item item = findInventoryItem(itemName);

        if (item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);
            return true;
        }

        return false;
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }
    public boolean move(String direction) {
        currentRoom.markTried(direction);

        Room desiredRoom = switch (direction) {
            case "north" -> currentRoom.getNorth();
            case "south" -> currentRoom.getSouth();
            case "east" -> currentRoom.getEast();
            case "west" -> currentRoom.getWest();
            default -> null;
        };

        if (desiredRoom != null) {
            currentRoom = desiredRoom;
            return true;
        }
        return false;
    }

}