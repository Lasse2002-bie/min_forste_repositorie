package Adevnture_spil;

public class Adventure {
    private Player player;
    private Map map;

    public Adventure() {
        map =  new Map();
        player = new Player(map.getStartRoom());
    }

    public boolean go(String direction) {
        return player.move(direction);
    }

    public String look() {
        return player.getCurrentRoom().describe();
    }
}