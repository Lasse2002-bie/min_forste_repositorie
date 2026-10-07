package Adevnture_spil;

public class Enemy {

    // enemy kort navn
    private String shortName;

    // enemy langt navn
    private String longName;

    // enemy beskrivelse
    private String description;

    // enemy liv
    private int health;

    //enemy equipped våben
    private Weapon weapon;

    // rum enemy er i
    private Room room;

    public Enemy(String shortName, String longName, String description, int health, Weapon weapon, Room room) {

        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.room = room;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    //Enemy bliver ramt og mister liv
    public void hit(int damage) {
        health -= damage;

        //Hvis enemy ikke har mere liv dør den
        if (health <= 0) {

            //Enemy dropper weapon i rummet
            room.addItem(weapon);

            //Enemy fjernes fra rummets liste
            room.removeEnemy(this);
        }
    }
    //Enemy angriber player med sit våben
    public int attack() {

        //Hvis enemys weapon ikke kan bruges
        if (!weapon.canUse()) {
            return 0;
        }

        //Bruger rangedWeapon og mister et skud
        weapon.use();

        //Returnere hvor meget damage player skal miste
        return weapon.getDamage();
    }
}
