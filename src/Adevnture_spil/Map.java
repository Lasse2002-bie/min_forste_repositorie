package Adevnture_spil;

public class Map {

    private Room startRoom;
// opretter mappen
    public Map() {
        buildMap();
    }
    // returnere start-rum
    public Room getStartRoom() {
        return startRoom;
    }

        //Eksisterende rooms
    private void buildMap() {
        Room room1 = new Room("Room1", "A cold and empty room with nothing but two doors");
        Room room2 = new Room("Room2", "A room with graffiti all over the walls, with big arrows pointing straight ahead to an old wooden door painted all red");
        Room room3 = new Room("Room3", "In the middle of the room sits a beautiful dining table with room for one. The candles on the table seems to be out but theres still smoke coming from them. Has someone recently been here?");
        Room room4 = new Room("Room4", "A room full of empty wooden boxes with what looks to be childrens toys, in the corner sits a small flock of squeaking rats. Gross!");
        Room room5 = new Room("Room5", "A sinister room with all sorts of garden tools hanging on the wall, as you look around your eyes fall on a single elevator that can only go up. Is this your way out?");
        Room room6 = new Room("Room6", "As you open the door you arrive on a wooden balcony with a few planks missing. You look beyond the distance and see nothing but trees and a few birds singing. You watch your step carefully as you walk across the dangerous planks,");
        Room room7 = new Room("Room7", "A room with broken glass and empty bottles all over the floor. On the wall hangs some spooky masks from classic horror movies");
        Room room8 = new Room("Room8", "A room filled with shelves. Upon them are hundreds of jars covered in a thick layer of dust. It is impossible to see what they contain.");
        Room room9 = new Room("Room9", "A storage room with rows of boxes. All labeled with a name, upon inspecting them, one name looks kinda like yours. You should probably look inside.");
        Room room10 = new Room("Room10", "A dark hallway with strange noises coming from the walls");
        Room room11 = new Room("Room11", "A dusty bedroom with an old bloodstained mattress");
        Room room12 = new Room("Room12", "A cold bathroom with a cracked mirror above the sink");
        Room room13 = new Room("Room13", "A small kitchen filled with rotten food and dirty dishes");
        Room room14 = new Room("Room14", "A narrow room covered in old family photographs");
        Room room15 = new Room("Room15", "A damp basement with chains hanging from the ceiling");
        Room room16 = new Room("Room16", "An abandoned office with papers scattered across the floor");
        Room room17 = new Room("Room17", "A silent room with a locked wooden chest in the middle");

        //Eksisterende Items:
        Item lamp = new Item("lamp", "A shiny brass lamp");
        Item sprayCan = new Item("spraycan", "A spray can filled with red graffiti");
        Item butterKnife = new Item("knife", "A sharp silver knife");
        Item creepyDoll = new Item("doll", "An ancient creepy doll");
        Item lawnShear = new Item("shear", "A garden shear with three sharp blades");
        Item elevatorKey = new Item("key", "A key that seems to be for some sort of elevator");
        Item mysteryJar = new Item("jar", "A jar with a strange form of liquid");
        Item playerClothes = new Item ("clothes", "A bundle of clothes that you wore when you got drugged");
        Item flashlight = new Item("flashlight", "A weak flashlight with barely any battery left");
        Item hammer = new Item("hammer", "A rusty hammer with a heavy wooden handle");
        Item photo = new Item("photo", "An old photograph with a face scratched out");
        Item bandage = new Item("bandage", "A dirty bandage that looks slightly used");
        Item crowbar = new Item("crowbar", "A heavy metal crowbar covered in rust");
        Item note = new Item("note", "A handwritten note with a strange warning");

        Food bread = new Food("bread", "a loaf of stale bread", 10);
        Food mushroom = new Food("mushroom", "a pale glowing mushroom", -50);
        Food energyDrink = new Food ("energydrink", "an icecold monster energy", 30);
        Food catFood = new Food ("catfood", "an old can of stinky cat food", -20);
        Food cheeseBurger = new Food ("burger", "a big juicy cheeseburger", 40);
        Food pillJar = new Food ("pill", "a bottle of small round pills", 60);
        Food tuna = new Food ("tuna", "a smelly can of tuna but sure to be worth a taste", 40);


        //Items lokation:
        room1.addItem(lamp);
        room2.addItem(sprayCan);
        room3.addItem(butterKnife);
        room4.addItem(creepyDoll);
        room5.addItem(lawnShear);
        room7.addItem(elevatorKey);
        room8.addItem(mysteryJar);
        room9.addItem(playerClothes);
        room10.addItem(flashlight);
        room11.addItem(hammer);
        room14.addItem(photo);
        room15.addItem(crowbar);
        room16.addItem(note);
        room17.addItem(bandage);

        //Food lokation:
        room1.addItem(bread);
        room5.addItem(mushroom);
        room11.addItem(energyDrink);
        room13.addItem(tuna);
        room8.addItem(catFood);
        room15.addItem(cheeseBurger);
        room7.addItem(pillJar);




        //Rooms forbindelser
        room1.setEast(room2);
        room1.setSouth(room4);
        room1.setNorth(room16);

        room2.setWest(room1);
        room2.setEast(room3);

        room3.setWest(room2);
        room3.setSouth(room6);
        room3.setEast(room15);

        room4.setNorth(room1);
        room4.setSouth(room7);

        room5.setSouth(room8);

        room6.setNorth(room3);
        room6.setSouth(room9);

        room7.setNorth(room4);
        room7.setEast(room8);
        room7.setSouth(room10);

        room8.setNorth(room5);
        room8.setWest(room7);
        room8.setEast(room9);

        room9.setWest(room8);
        room9.setNorth(room6);

        room10.setEast(room14);
        room10.setWest(room11);
        room10.setNorth(room7);

        room11.setEast(room10);
        room11.setSouth(room13);
        room11.setNorth(room12);

        room12.setSouth(room11);

        room13.setNorth(room11);

        room14.setWest(room10);

        room15.setWest(room3);
        room15.setSouth(room17);

        room16.setSouth(room1);

        room17.setNorth(room15);
        startRoom = room1;

    }


}
