package Adevnture_spil;

//Subclass til Weapon
//Arver ting fra Weaponn og Item
public class MeleeWeapon extends Weapon{

    public MeleeWeapon(String shortName, String longName, int damage) {
        //Sender værdier videre til constructor i Weapon
        super(shortName, longName, damage);
    }
    @Override
    public boolean canUse() {
        //Melee har ik ammo og kan altid bruges
        return true;
    }

    @Override
    public void use() {
    // Tom fordi melee ik bruger ammo
    }

    @Override
    public String getAttackVerb() {
        //Bruges når UI skal skal skrive fx "You swing knife"
        return "swing";
    }

    @Override
    public String getUsesLeftText() {
        //Vises ik pga ingen ammo
        return "";
    }
}
