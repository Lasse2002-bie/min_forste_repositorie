package Adevnture_spil;

public class Room {
    private String name;
    private String description;

    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private boolean triedNorth = false;
    private boolean triedSouth = false;
    private boolean triedEast = false;
    private boolean triedWest = false;

    public void tryNorth() {
        triedNorth = true;
    }
    public void trySouth() {
        triedSouth = true;
    }
    public void tryEast() {
        triedEast = true;
    }
    public void tryWest() {
        triedWest = true;
    }

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
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
    public boolean allDirectionsTried() {
        return triedNorth && triedSouth
                && triedEast && triedWest;
    }
}
