package Adevnture_spil;
import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int health;
    private Weapon equippedWeapon;

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

            if (item == equippedWeapon) {
                equippedWeapon = null;
            }
            inventory.remove(item);
            currentRoom.addItem(item);
            return true;
        }

        return false;

    } public DrinkResult drink (String shortName) {
        Item item = findInventoryItem(shortName);
        if (item == null) {
            item = currentRoom.findItem(shortName);
        }
        if (item == null) {
            return DrinkResult.NOT_FOUND;
        }
        if (!(item instanceof Consumable)) {
            return DrinkResult.NOT_DRINKABLE;
        }
        Consumable consumable = (Consumable) item;
        health += consumable.getHealthPoints();

        inventory.remove(consumable);
        currentRoom.removeItem(consumable);

        return Adevnture_spil.DrinkResult.DRANK;
    }
    //Spis noget
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
    public Item findItem(String itemName) {
        Item item = findInventoryItem(itemName);

        if (item == null) {
            item = currentRoom.findItem(itemName);
        }
        return item;
    }
    public EquipResult equip(String itemName) {
        Item item = findInventoryItem(itemName);

        if (item == null) {
            return EquipResult.NOT_FOUND;
        }
        if (!(item instanceof Weapon)) {
            return EquipResult.NOT_WEAPON;
        }
        Weapon weapon = (Weapon) item;
        equippedWeapon = weapon;

        return EquipResult.EQUIPPED;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }
    public AttackResult attack() {
        if (equippedWeapon == null) {
            return AttackResult.NO_WEAPON;
        }
        if (!equippedWeapon.canUse()) {
            return AttackResult.NO_USES_LEFT;
        }
        equippedWeapon.use();
        return AttackResult.ATTACKED;

    }
    public AttackResult trickshot() {
        if(equippedWeapon==null) {
            return AttackResult.NO_USES_LEFT;
        }
        if (!equippedWeapon.canUse()) {
            return AttackResult.NO_USES_LEFT;
        }
        if ()
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