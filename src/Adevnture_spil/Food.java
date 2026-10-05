package Adevnture_spil;

// Food er en type Item og arver derfor shortName og longName
public class Food extends Item{

    // Bestemmer hvor meget spillerens health ændres, når maden spises
    // Kan både være positivt og negativt
    private int healthPoints;


    public Food(String shortName, String longName, int healthPoints) {
        // Sender shortName og longName videre til Item-constructoren
        super(shortName, longName);

        // Gemmer hvor meget maden påvirker health
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
