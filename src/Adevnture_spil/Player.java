package Adevnture_spil;
import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private ArrayList<Item> inventory;

   //Hvor står spilleren
    public Player(Room startRoom) {
        currentRoom = startRoom;
        inventory = new ArrayList<>();
    }
    //Hvad bærer spilleren
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    //Tag noget fra rummet
    public boolean takeItem(String itemName) {
        Item item = currentRoom.findItem(itemName);

        if (item != null) {
            currentRoom.removeItem(item);
            inventory.add(item);
            return true;
        }
        return false;
    }
    //Find noget i inventory
    public Item findInventoryItem(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
    //Læg noget i rummet
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
    //Bevæg spilleren
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