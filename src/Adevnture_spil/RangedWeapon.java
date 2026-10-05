package Adevnture_spil;

public class RangedWeapon extends Weapon{

    private int ammunition;

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
        return "fire";
    }

    @Override
    public String getUsesLeftText() {
        return ammunition + " shots left";
    }

}
