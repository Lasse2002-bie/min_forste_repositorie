package Adevnture_spil;

public abstract class Weapon extends Item {
    private int damage;
    private boolean trickshot;
    public Weapon(String shortName, String longName, int damage, Boolean trickshot) {
        super(shortName, longName);
        this.damage = damage;
        this.trickshot = trickshot;
    }
    public boolean getTrickshot() {return trickshot;}
    public int getDamage() {
        return damage;
    }

    public abstract boolean canUse();

    public abstract void use();

    public abstract String getAttackVerb();

    public abstract String getUsesLeftText();


}
