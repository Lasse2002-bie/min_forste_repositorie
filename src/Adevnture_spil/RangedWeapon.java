package Adevnture_spil;

//Subclass af Weapon
public class RangedWeapon extends Weapon{

    //Gemmer antal skud tilbage
    private int ammunition;

    public RangedWeapon(String shortName, String longName, int damage, int ammunition) {
        //Sender navn og damage videre til Weapon
        super(shortName, longName, damage);
        //Gemmer ammo vi sender videre når objekt laves
    public RangedWeapon(String shortName, String longName, int damage, int ammunition, boolean trickshot) {
        super(shortName, longName, damage, trickshot);
        this.ammunition = ammunition;
    }

    @Override
    public boolean canUse() {
        return  ammunition > 0;
    }
    @Override
    public void use() {
        ammunition--;
    }

    @Override
    public String getAttackVerb() {
        //Bruges i UI til fx. "You fire revolver"
        return "fire";
    }

    @Override
    public String getUsesLeftText() {
        //Returnere antal skud tilbage
        return ammunition + " shots left";
    }

}
