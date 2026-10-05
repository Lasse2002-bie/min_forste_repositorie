package Adevnture_spil;


// Item er den fælles klasse for ting, der kan findes i spillet
public class Item {
    // Det korte navn bruges fx i kommandoer som "take knife"
    private String shortName;
    // Det lange navn er beskrivelsen af itemet
    private String longName;

    // Constructor der sætter navn og beskrivelse, når et Item bliver oprettet
    public Item (String shortName, String longName) {
        this.shortName = shortName;
        this.longName = longName;
    }
    // Returnerer itemets korte navn
    public String getShortName() {
        return shortName;
    }
    // Returnerer itemets beskrivelse
    public String getLongName() {
        return longName;
    }
}
