package Adevnture_spil;

public class Map {

    private Room startRoom;

    public Map() {
        buildMap();
    }

    private void buildMap()
    {
        Room room1 = new Room("Room1", "A cold and empty room with nothing but two doors");
        Room room2 = new Room("Room2", "A room with graffiti all over the walls, with big arrows pointing straight ahead to an old wooden door painted all red");
        Room room3 = new Room("Room3", "In the middle of the room sits a beautiful dining table with room for one. The candles on the table seems to be out but theres still smoke coming from them. Has someone recently been here?");
        Room room4 = new Room("Room4", "A room full of empty wooden boxes with what looks to be childrens toys, in the corner sits a small flock of squeaking rats. Gross!");
        Room room5 = new Room("Room5", "A sinister room with all sorts of garden tools hanging on the wall, as you look around your eyes fall on a single elevator that can only go up. Is this your way out?");
        Room room6 = new Room("Room6", "As you open the door you arrive on a wooden balcony with a few planks missing. You look beyond the distance and see nothing but trees and a few birds singing. You watch your step carefully as you walk across the dangerous planks,");
        Room room7 = new Room("Room7", "A room with broken glass and empty bottles all over the floor. On the wall hangs some spooky masks from classic horror movies");
        Room room8 = new Room("Room8", "A room filled with shelves. Upon them are hundreds of jars covered in a thick layer of dust. It is impossible to see what they contain.");
        Room room9 = new Room("Room9", "A storage room with rows of boxes. All labeled with a name, upon inspecting them, one name looks kinda like yours. You should probably look inside.");

        room1.setEast(room2);
        room2.setWest(room1);
        room2.setEast(room3);
        room3.setWest(room2);
        room3.setSouth(room6);
        room6.setNorth(room3);
        room6.setSouth(room9);
        room9.setNorth(room6);
        room1.setSouth(room4);
        room4.setNorth(room1);
        room4.setSouth(room7);
        room7.setNorth(room4);
        room7.setEast(room8);
        room8.setWest(room7);
        room8.setEast(room9);
        room9.setWest(room8);
        room8.setNorth(room5);
        room5.setSouth(room8);
        startRoom = room1;

    }
    public Room getStartRoom() {
        return startRoom;
    }
}
