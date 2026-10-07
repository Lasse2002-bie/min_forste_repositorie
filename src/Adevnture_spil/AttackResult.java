package Adevnture_spil;

//Resultater attack kan give
public enum AttackResult {
    ATTACKED, //Angrebet lykkedes
    NO_WEAPON, //Spilleren har ikke et weapon equipped
    NO_USES_LEFT, // Våbnet kan ikke bruges mere
    TRICKSHOT_HIT,
    TRICKSHOT_MISS,
    CANT_TRICKSHOT,

    //Enemy attack resultater
    NO_ENEMY,
    ENEMY_HIT,
    ENEMY_DIED

}
