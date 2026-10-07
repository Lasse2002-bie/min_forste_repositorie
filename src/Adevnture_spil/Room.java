package Adevnture_spil;
import java.util.ArrayList;

public class Room {

    // Rummets navn og beskrivelse
    private String name;
    private String description;

    // Liste over alle items der ligger i rummet
    // Fordi listen bruger Item, kan den også indeholde Food, Consumable og Weapon
    private ArrayList<Item> items;

    //Liste over enemies i rummet
    private ArrayList<Enemy> enemies;

    // Referencer til de rooms man kan gå til
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    // Holder styr på hvilke retninger spilleren allerede har prøvet
    private boolean triedNorth = false;
    private boolean triedSouth = false;
    private boolean triedEast = false;
    private boolean triedWest = false;


    public Room(String name, String description) {
        this.name = name;
        this.description = description;

        // Opretter en tom liste til rummets items
        items = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    // Returnerer rummene i de forskellige retninger
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
    // Bruges i Map til at forbinde rooms med hinanden
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

    // Gemmer at spilleren har prøvet en bestemt retning
    public void markTried(String direction) {
        switch (direction) {
            case "north" -> triedNorth = true;
            case "south" -> triedSouth = true;
            case "east" -> triedEast = true;
            case "west" -> triedWest = true;
        }
    }

    // Returnerer true hvis spilleren har prøvet alle fire retninger
    public boolean allDirectionsTried() {

        return triedNorth && triedSouth && triedEast && triedWest;
    }

    // Laver den tekst der beskriver rummet
    public String describe() {

        String text = name + ": " + description;

        // Når alle retninger er prøvet, vises hvilke døre der faktisk findes
        if (allDirectionsTried()) {
            text += "\nThere are doors to the:";
            if (north != null) text += " North";
            if (south != null) text += " South";
            if (east != null) text += " East";
            if (west != null) text += " West";
        }

        // Hvis der ligger items i rummet, bliver de vist
        if (!items.isEmpty()) {
            text += "\nItems:";

            // Går igennem alle items i rummet
            for (Item item : items) {
                text += "\n- " + "(" + item.getShortName() + ") " + item.getLongName();
            }
        }

        //Hvis der er enemies i rummet vises de
        if (!enemies.isEmpty()) {
            text += "\nEnemies:";

            for (Enemy enemy : enemies) {
                text += "\n- (" + enemy.getShortName() + ") "
                        + enemy.getLongName();

                text += "\n  " + enemy.getDescription();
            }
        }
        return text;
    }
    // Tilføjer et Item til rummet
    public void addItem(Item item) {

        items.add(item);
        }

    // Returnerer listen over rummets items
        public ArrayList<Item> getItems() {

        return items;
        }

    // Fjerner et bestemt Item fra rummet
    public boolean removeItem(Item item) {

        return items.remove(item);
    }

    // Leder efter et Item i rummet ud fra dets shortName
        public Item findItem(String itemName) {

            // Går igennem alle items i rummet
        for (Item item : items) {

            // equalsIgnoreCase gør store/små bogstaver ligegyldige
            if(item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
            // Hvis itemet ikke findes
        return null;
        }
    // Tilføjer en enemy til rummet
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    // Fjerner en enemy fra rummet
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    //Leder efter en enemy i rummet ud fra dens shortName
    public Enemy findEnemy (String enemyName) {

        //Går igennem alle enemies i rummet
        for (Enemy enemy : enemies) {

            //Finder enemy ud fra dens shortName
            if (enemy.getShortName().equalsIgnoreCase(enemyName)) {
                return enemy;
            }
        }
        return null;
    }

    // Returnerer alle enemies i rummet
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    }
