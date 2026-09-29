package Adevnture_spil;
import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int health;

    //Hvor står spilleren
    public Player(Room startRoom) {
        currentRoom = startRoom;
        inventory = new ArrayList<>();
        health = 100;
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
    public EatResult eat(String shortName) {
        Item item = findInventoryItem(shortName);

        if (item == null) {
            item = currentRoom.findItem(shortName);
        }
        if (item == null) {
            return EatResult.NOT_FOUND;
        }
        if (!(item instanceof Food)) {
            return EatResult.NOT_FOOD;
        }
        Food food = (Food) item;
        health += food.getHealthPoints();

        inventory.remove(food);
        currentRoom.removeItem(food);

        return EatResult.EATEN;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public int getHealth() {
        return health;
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