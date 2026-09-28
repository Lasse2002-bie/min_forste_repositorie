package Adevnture_spil;
import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private ArrayList<Item> items;

    private Room north;
    private Room south;
    private Room east;
    private Room west;

    private boolean triedNorth = false;
    private boolean triedSouth = false;
    private boolean triedEast = false;
    private boolean triedWest = false;


    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
    }

    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getEast() {
        return east;
    }

    public Room getWest() {
        return west;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public void markTried(String direction) {
        switch (direction) {
            case "north" -> triedNorth = true;
            case "south" -> triedSouth = true;
            case "east" -> triedEast = true;
            case "west" -> triedWest = true;
        }
    }

    public boolean allDirectionsTried() {
        return triedNorth && triedSouth && triedEast && triedWest;
    }

    public String describe() {
        String text = name + ": " + description;

        if (allDirectionsTried()) {
            text += "\nThere are doors to the:";
            if (north != null) text += " North";
            if (south != null) text += " South";
            if (east != null) text += " East";
            if (west != null) text += " West";
        }

        if (!items.isEmpty()) {
            text += "\nItems:";

            for (Item item : items) {
                text += "\n- " + item.getLongName();
            }
        }
        return text;
    }
    public void addItem(Item item) {
            items.add(item);
        }
        public ArrayList<Item> getItems() {
            return items;
        }
    public boolean removeItem(Item item) {
        return items.remove(item);
    }
        public Item findItem(String itemName) {
        for (Item item : items) {
            if(item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
        }
    }
