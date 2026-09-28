package Adevnture_spil;

import java.util.ArrayList;

public class Adventure {
    private Player player;
    private Map map;

    public Adventure() {
        map =  new Map();
        player = new Player(map.getStartRoom());
    }

    public boolean go(String direction) {
        return player.move(direction);
    }

    public String look() {
        return player.getCurrentRoom().describe();
    }
    public ArrayList<Item> getInventory() {
        return player.getInventory();
    }
    public boolean takeItem(String itemName) {
        return player.takeItem(itemName);
    }
    public boolean dropItem(String itemName) {
        return player.dropItem(itemName);
    }
}