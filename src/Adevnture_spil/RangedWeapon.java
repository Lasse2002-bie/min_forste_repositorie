package Adevnture_spil;

// Subclass af Weapon
public class RangedWeapon extends Weapon {

    // Gemmer antal skud tilbage
    private int ammunition;

    // Constructor til normalt ranged weapon
    public RangedWeapon(String shortName, String longName, int damage, int ammunition) {
        // Sender navn og damage videre til Weapon
        // Trickshot bliver automatisk false
        super(shortName, longName, damage);

        // Gemmer ammo når objektet laves
        this.ammunition = ammunition;
    }

    // Constructor til ranged weapon hvor vi bestemmer trickshot
    public RangedWeapon(String shortName, String longName, int damage, int ammunition, boolean trickshot) {
        // Sender også trickshot videre til Weapon
        super(shortName, longName, damage, trickshot);

        // Gemmer ammo
        this.ammunition = ammunition;
    }

    @Override
    public boolean canUse() {
        // Kan kun bruges hvis der er ammunition tilbage
        return ammunition > 0;
    }

    @Override
    public void use() {
        // Fjerner ét skud når våbnet bruges
        ammunition--;
    }

    @Override
    public String getAttackVerb() {
        // Bruges i UI til fx "You fire shotgun"
        return "fire";
    }

    @Override
    public String getUsesLeftText() {
        // Returnerer antal skud tilbage
        return ammunition + " shots left";
    }
}