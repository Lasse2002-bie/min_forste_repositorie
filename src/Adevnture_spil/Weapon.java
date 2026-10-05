package Adevnture_spil;

//weapon er fælles superklasse for alle våben
public abstract class Weapon extends Item {

    //Alle weapons har damage
    private int damage;
    private boolean trickshot;
    public Weapon(String shortName, String longName, int damage, Boolean trickshot) {
        super(shortName, longName);
        this.damage = damage;
        this.trickshot = trickshot;

    //Constructor subsclasses bruger med super
    public Weapon(String shortName, String longName, int damage) {
        super(shortName, longName); //Sender navne videre til Item
        this.damage = damage; //Gemmer weapon damage
    }
    //Returner damage
    public boolean getTrickshot() {return trickshot;}
    public int getDamage() {
        return damage;
    }

    //Subclasses bestemmer hvornår våben skal bruges
    public abstract boolean canUse();

    //Subclasses bestemmer hvad der sker når våben bruges
    public abstract void use();

    //Sub bestemmer attack ord
    public abstract String getAttackVerb();

    //Sub bestemmer tekst om uses/ammo
    public abstract String getUsesLeftText();


}
