package Adevnture_spil;

import java.util.Scanner;

public class User_Interface {
    private Scanner scanner = new Scanner(System.in);
    private Adventure adventure = new Adventure();

    public void start() {
        System.out.println("Welcome to Gustav, Marcus & Lasses incredible adventure game!!");
        scanner.nextLine();
        System.out.println("You wake up feeling lightheaded");
        scanner.nextLine();
        System.out.println("The last thing you remember was going ham on the floor at the annual Dansevands event, and some guy handing you his drink");
        scanner.nextLine();
        System.out.println("The room you wake up in feels cold and empty, with nothing but two doors");
        System.out.println("As you look down you notice all your clothes is missing");
        scanner.nextLine();
        System.out.println("Find your missing clothes");
        System.out.println("Type 'help' to see commands");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.startsWith("go ")) {
                String direction = command.substring(3);
                if (adventure.go(direction)) {
                    System.out.println(adventure.look());
                } else {
                    System.out.println("You cannot go this way");
                }

            } else if (command.equals("look")) {
                System.out.println(adventure.look());

            } else if (command.equals("help")) {
                System.out.println("Commands: go north, go south, go east, go west, look, help, exit");

            } else if (command.equals("exit")) {
                System.out.println("Quitter");
                break;

            } else {
                System.out.println("I don't understand that command.");
            }
        }
    }
}