package Adevnture_spil;

// Consumable er en type Item og bruges til ting spilleren kan drikke
public class Consumable extends Item {

    // Bestemmer hvor meget spillerens health ændres, når den drikkes
    // Kan både være positivt og negativt
    private int healthPoints;

    public Consumable(String shortName, String longName, int healthPoints) {
        // Sender shortName og longName videre til Item-constructoren
        super(shortName, longName);
        // Gemmer hvor meget denne Consumable påvirker health
        this.healthPoints = healthPoints;
    }
    // Returnerer hvor mange healthPoints drikken giver eller fjerner
    public int getHealthPoints() {

        return healthPoints;
    }
}