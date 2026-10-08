package Adevnture_spil;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    //Spillerens rum de står i
    private Room currentRoom;

    //Inventory kan indeholde alle typer Item
    private ArrayList<Item> inventory;

    //Spillerens liv
    private int health;

    //Gemmer equipped våben
    //Kan både indeholde Melee og ranged
    private Weapon equippedWeapon;
    private Random random = new Random();
    //Hvor står spilleren
    public Player(Room startRoom) {
        currentRoom = startRoom;
        inventory = new ArrayList<>();
        health = 100;
    }

    //Returnerer spillerens inventory
    public ArrayList<Item> getInventory() {

        return inventory;
    }

    //Tag noget fra rummet og lægger det i inventory
    public boolean takeItem(String itemName) {
        Item item = currentRoom.findItem(itemName);

        if (item != null) {
            currentRoom.removeItem(item);
            inventory.add(item);
            return true;
        }
        return false;
    }

    //Leder efter item i inventory ud fra shortName
    public Item findInventoryItem(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        //At vi ikke fandt noget
        return null;
    }

    //Dropper fra inventory tilbage i rummet
    public boolean dropItem(String itemName) {
        Item item = findInventoryItem(itemName);

        if (item != null) {
            //Hvis vi dropper equipped weapon, unequipper vi det
            if (item == equippedWeapon) {
                equippedWeapon = null;
            }
            inventory.remove(item);
            currentRoom.addItem(item);
            return true;
        }

        return false;
    }

    //Forsøger at drikke et item
    public DrinkResult drink(String shortName) {
        Item item = findInventoryItem(shortName);

        //Hvis det ikke er i inventory søges rummet
        if (item == null) {
            item = currentRoom.findItem(shortName);
        }
        if (item == null) {
            return DrinkResult.NOT_FOUND;
        }
        //Tjekker om det fundne Item er Consumable
        if (!(item instanceof Consumable)) {
            return DrinkResult.NOT_DRINKABLE;
        }
        //Vi ved item er consumable, så vi tar det
        Consumable consumable = (Consumable) item;
        health += consumable.getHealthPoints();
        //Fjerner det efter det brugt
        inventory.remove(consumable);
        currentRoom.removeItem(consumable);

        return Adevnture_spil.DrinkResult.DRANK;
    }
    //Forsøger at spise Item

    //Spis noget
    public EatResult eat(String shortName) {
        Item item = findInventoryItem(shortName);

        if (item == null) {
            item = currentRoom.findItem(shortName);
        }
        if (item == null) {
            return EatResult.NOT_FOUND;
        } //Tjekker om Item er food
        if (!(item instanceof Food)) {
            return EatResult.NOT_FOOD;
        } // Caster Item til Food så vi kan bruge Food-metode
        Food food = (Food) item;
        health += food.getHealthPoints();

        inventory.remove(food);
        currentRoom.removeItem(food);

        return EatResult.EATEN;
    }
    //Leder i inventory og efter i nuværende rum

    public Item findItem(String itemName) {
        Item item = findInventoryItem(itemName);

        if (item == null) {
            item = currentRoom.findItem(itemName);
        }
        return item;
    }

    //Forsøger at equip item fra inventory
    public EquipResult equip(String itemName) {

        //Vi leder kun i inventory
        Item item = findInventoryItem(itemName);

        if (item == null) {
            return EquipResult.NOT_FOUND;
        } //Tjekker om fundne item er Weapon
        if (!(item instanceof Weapon)) {
            return EquipResult.NOT_WEAPON;
        }
        //Efter instanceof ved vi, at item kan bruges som weapon
        Weapon weapon = (Weapon) item;
        equippedWeapon = weapon;

        return EquipResult.EQUIPPED;
    }
    // returnerer det våben spilleren har equipped
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public AttackResult attack() {

        // Hvis player ikke har et weapon equipped
        if (equippedWeapon == null) {
            return AttackResult.NO_WEAPON;
        }

        // Hvis der ikke er enemies i rummet
        if (currentRoom.getEnemies().isEmpty()) {

            if (!equippedWeapon.canUse()) {
                return AttackResult.NO_USES_LEFT;
            }

            // Angriber bare den tomme luft
            equippedWeapon.use();
            return AttackResult.ATTACKED;
        }

        // Tager den første enemy i rummet
        Enemy enemy = currentRoom.getEnemies().get(0);

        // Bruger vores attack(String) til at angribe den
        return attack(enemy.getShortName());
    }

    // "attack trickshot" uden navn -> skyder på den første enemy i rummet
    public AttackResult trickshot() {
        if (currentRoom.getEnemies().isEmpty()) {
            return AttackResult.NO_ENEMY;
        }
        Enemy enemy = currentRoom.getEnemies().get(0);
        return trickshot(enemy.getShortName());
    }

    // Trickshot mod en bestemt enemy - 25% chance for 3x damage
    public AttackResult trickshot(String enemyName) {
        if (equippedWeapon == null) {
            return AttackResult.NO_WEAPON;
        }
        if (!equippedWeapon.getTrickshot()) {
            return AttackResult.CANT_TRICKSHOT;
        }

        // Tjekker om enemy findes FØR vi bruger et skud
        Enemy enemy = currentRoom.findEnemy(enemyName);
        if (enemy == null) {
            return AttackResult.NO_ENEMY;
        }
        if (!equippedWeapon.canUse()) {
            return AttackResult.NO_USES_LEFT;
        }

        equippedWeapon.use();
        int chance = random.nextInt(100);

        if (chance < 25) {
            // Ramt: enemy tager 3x damage
            enemy.hit(equippedWeapon.getDamage() * 3);

            if (enemy.getHealth() <= 0) {
                return AttackResult.ENEMY_DIED;
            }
            enemyAttacksBack(enemy);
            return AttackResult.TRICKSHOT_HIT;
        }

        // Miss: enemy slår stadig tilbage
        enemyAttacksBack(enemy);
        return AttackResult.TRICKSHOT_MISS;
    }

    // Enemy forsøger at slå tilbage
    private void enemyAttacksBack(Enemy enemy) {
        int enemyDamage = enemy.attack();
        if (enemyDamage > 0) {
            hit(enemyDamage);
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public int getHealth() {
        return health;
    }
    public void hit(int damage) {
        health -= damage;
    }

    //Bevæg spilleren
    public boolean move(String direction) {
        currentRoom.markTried(direction);

        // Finder rum der ligger i den valgte retning
        Room desiredRoom = switch (direction) {
            case "north" -> currentRoom.getNorth();
            case "south" -> currentRoom.getSouth();
            case "east" -> currentRoom.getEast();
            case "west" -> currentRoom.getWest();
            default -> null;
        };
        // Hvis der findes et rum i retningen flyttes spilleren
        if (desiredRoom != null) {
            currentRoom = desiredRoom;
            return true;
        }
        return false;
    }
    // Angriber en enemy i det rum spilleren står i
    public AttackResult attack(String enemyName) {

        // Tjekker om spilleren har et weapon equipped
        if (equippedWeapon == null) {
            return AttackResult.NO_WEAPON;
        }

        // Tjekker om enemy findes FØR vi bruger våbnet
        Enemy enemy = currentRoom.findEnemy(enemyName);

        if (enemy == null) {
            return AttackResult.NO_ENEMY;
        }

        // Tjekker om våbnet stadig kan bruges
        if (!equippedWeapon.canUse()) {
            return AttackResult.NO_USES_LEFT;
        }

        // Bruger våbnet - ranged mister ét skud
        equippedWeapon.use();

        // Enemy mister health svarende til weapon damage
        enemy.hit(equippedWeapon.getDamage());

        //Hvis enemy døde af attack stopper attack-sekvens
        if (enemy.getHealth() <= 0) {
            return AttackResult.ENEMY_DIED;
        }

        //Hvis enemy overlevede og prøver at slå tilbage
        int enemyDamage = enemy.attack();

        //Hvis enemys weapon kunne bruges
        if (enemyDamage > 0) {
            hit(enemyDamage);
        }

        return AttackResult.ENEMY_HIT;
    }
}