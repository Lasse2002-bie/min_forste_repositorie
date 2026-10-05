package Adevnture_spil;


public class Adventure {
    //Holder styr på spilleren og spillets map
    private Player player;
    private Map map;

    public Adventure() {
        // Laver spillets map
        map =  new Map();
        // Laver spilleren og placerer den i startRoom
        player = new Player(map.getStartRoom());
    }
    // Sender bevægelsen videre til Player
    public boolean go(String direction) {
        return player.move(direction);
    }

    // Henter beskrivelsen af det rum spilleren står i
    public String look() {

        return player.getCurrentRoom().describe();
    }

    // Laver en tekst med alle items i spillerens inventory
    public String inventory() {
        if (player.getInventory().isEmpty()) {
            return "Your inventory is empty";
        }
        String text = "Inventory:";

        for (Item item : player.getInventory()) {
            text += "\n- " + item.getLongName();

            // Viser hvilket weapon der er equipped
            if (item == player.getEquippedWeapon()) {
                text += " (equipped)";
            }
        }
        return text;
    }
    // Sender take videre til Player
    public boolean takeItem(String itemName) {

        return player.takeItem(itemName);
    }
    // Sender drop videre til Player
    public boolean dropItem(String itemName) {

        return player.dropItem(itemName);
    }
    // Henter spillerens health
    public int getHealth() {

        return player.getHealth();
    }
    // Sender eat videre til Player
    public EatResult eat(String itemName) {

        return player.eat(itemName);
    }
    // Sender drink videre til Player
    public DrinkResult drink(String itemName) {

        return player.drink(itemName);
    }
    // Finder et item gennem Player
    public Item findItem(String itemName) {

        return player.findItem(itemName);
    }
    // Sender equip videre til Player
    public EquipResult equip (String itemName) {

        return player.equip(itemName);
    }
    // Sender attack videre til Player
    public AttackResult attack() {

        return player.attack();
    }
    // Henter det weapon spilleren har equipped
    public Weapon getEquippedWeapon() {

        return player.getEquippedWeapon();
    }

}