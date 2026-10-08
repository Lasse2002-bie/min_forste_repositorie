package Adevnture_spil;
import java.util.Scanner;
public class User_Interface {

    // Scanner bruges til at læse det spilleren skriver
    private Scanner scanner = new Scanner(System.in);

    // UI kommunikerer med spillet gennem Adventure
    private Adventure adventure = new Adventure();

    //intro
    public void start() {
        System.out.println("Welcome to Gustav, Marcus & Lasses incredible adventure game!!");
        scanner.nextLine();
        System.out.println("You wake up feeling lightheaded");
        scanner.nextLine();
        System.out.println("The last thing you remember was going ham on the floor at the annual Dansevands event, and some guy handing you his drink");
        scanner.nextLine();
        System.out.println("The room you wake up in feels cold and empty, with nothing but two doors");
        System.out.println("As you look down you notice all your clothes are missing");
        scanner.nextLine();
        System.out.println("Find your missing clothes");
        System.out.println("Type 'help' to see commands");

        //Spillet fortsætter indtil vi rammer break
        while (true) {
            System.out.print("> ");

            // Gemmer spillerens kommando
            String command = scanner.nextLine();

            //startsWith bruges fordi der kommer en retning efter "go "
            if (command.startsWith("go ")) {

                // Fjerner "go " og gemmer kun retningen
                // Fx "go north" bliver til "north"
                String direction = command.substring(3);

                if (adventure.go(direction)) {
                    System.out.println("You are in: " + adventure.look());
                } else {
                    System.out.println("You cannot go this way");
                }

            } else if (command.equals("look")) {
                System.out.println(adventure.look());

            } else if (command.equals("inventory")) {
                System.out.println(adventure.inventory());

                // Fx "take knife" -> "knife"
            } else if (command.startsWith("take ")) {
                String itemName = command.substring(5);

                if (adventure.takeItem(itemName)) {
                    System.out.println("You picked up " + itemName);
                } else {
                    System.out.println("There is no " + itemName + " here");
                }
            } else if (command.startsWith("drop ")) {
                String itemName = command.substring(5);

                if (adventure.dropItem(itemName)) {
                    System.out.println("You dropped " + itemName);
                } else {
                    System.out.println("There is no " + itemName + " to drop");
                }

            } else if (command.startsWith("equip ")) {
                // Fx "equip knife" -> "knife"
                String itemName = command.substring(6);
                // Adventure/Player returnerer resultatet af equip-forsøget
                EquipResult result = adventure.equip(itemName);

                // UI bestemmer hvilken besked spilleren skal se
                if (result == EquipResult.NOT_FOUND) {
                    System.out.println("You don't have " + itemName + " in your inventory");
                } else if (result == EquipResult.NOT_WEAPON) {
                    System.out.println(itemName + " is not a weapon");
                } else if (result == EquipResult.EQUIPPED) {
                    System.out.println("You equipped " + itemName);
                }

            } else if (command.equals("attack")) {
                // Player udfører attack og returnerer resultatet

                int healthBefore = adventure.getHealth();

                AttackResult attackResult = adventure.attack();

                int healthAfter = adventure.getHealth();

                if (healthAfter <= 0) {
                    System.out.println("You died! Game over.");
                    break;
                }

                if (attackResult == AttackResult.NO_WEAPON) {
                    System.out.println("You have no weapon equipped");

                } else if (attackResult == AttackResult.NO_USES_LEFT) {
                    System.out.println("Your weapon cannot be used anymore");

                } else if (attackResult == AttackResult.ATTACKED) {
                    Weapon weapon = adventure.getEquippedWeapon();

                    System.out.println("You " + weapon.getAttackVerb() + " "
                            + weapon.getShortName() + " "
                            + weapon.getUsesLeftText());

                } else if (attackResult == AttackResult.ENEMY_HIT) {
                    System.out.println("You attacked the enemy");

                    int damageTaken = healthBefore - healthAfter;

                    if (damageTaken > 0) {
                        System.out.println("The enemy attacks you back for "
                                + damageTaken + " damage");
                    } else {
                        System.out.println("The enemy tried to attack you, but could not use its weapon");
                    }

                } else if (attackResult == AttackResult.ENEMY_DIED) {
                    System.out.println("You killed the enemy and they dropped their weapon");
                }

            } else if (command.startsWith("attack trickshot")) {
                // "attack trickshot fishman" -> "fishman"
                // "attack trickshot" alene -> tom tekst = første enemy i rummet
                String enemyName = command.substring(16).trim();

                int healthBefore = adventure.getHealth();

                AttackResult trickshot;
                if (enemyName.isEmpty()) {
                    trickshot = adventure.trickshot();
                } else {
                    trickshot = adventure.trickshot(enemyName);
                }

                int healthAfter = adventure.getHealth();
                Weapon weapon = adventure.getEquippedWeapon();

                if (trickshot == AttackResult.NO_WEAPON) {
                    System.out.println("You have no weapon equipped");
                } else if (trickshot == AttackResult.CANT_TRICKSHOT) {
                    System.out.println("You can't trickshot with this weapon, attack normally");
                } else if (trickshot == AttackResult.NO_ENEMY) {
                    System.out.println("There is no " + (enemyName.isEmpty() ? "enemy" : enemyName) + " here to trickshot");
                } else if (trickshot == AttackResult.NO_USES_LEFT) {
                    System.out.println("Your weapon cannot be used anymore");
                } else if (trickshot == AttackResult.ENEMY_DIED) {
                    System.out.println("360 NO-SCOPE DAT HOE! You hit for " + weapon.getDamage() * 3
                            + " damage and killed them. " + weapon.getUsesLeftText());
                } else if (trickshot == AttackResult.TRICKSHOT_HIT) {
                    System.out.println("360 NO-SCOPE DAT HOE! You hit for " + weapon.getDamage() * 3
                            + " damage. " + weapon.getUsesLeftText());
                } else if (trickshot == AttackResult.TRICKSHOT_MISS) {
                    System.out.println("You missed the trickshot... GG noob.. " + weapon.getUsesLeftText());
                }

                // Viser hvis enemy slog tilbage
                int damageTaken = healthBefore - healthAfter;
                if (damageTaken > 0) {
                    System.out.println("The enemy attacks you back for " + damageTaken + " damage");
                }

                if (healthAfter <= 0) {
                    System.out.println("You died! Game over.");
                    break;
                }
            }
            else if (command.startsWith("attack")) {

                //fx Attack killer -> "killer"
                String enemyName = command.substring(7);

                int healthBefore = adventure.getHealth();

                AttackResult attackResult = adventure.attack(enemyName);

                int healthAfter = adventure.getHealth();

                if (healthAfter <= 0) {
                    System.out.println("You died! Game over.");
                    break;
                }

                if (attackResult == AttackResult.NO_WEAPON) {
                    System.out.println("You have no weapon equipped");
                }
                else if (attackResult == AttackResult.NO_USES_LEFT) {
                    System.out.println("Your weapon cannot be used anymore");
                }
                else if (attackResult == AttackResult.NO_ENEMY) {
                    System.out.println("There is no " + enemyName + " here");
                }
                else if (attackResult == AttackResult.ENEMY_HIT) {
                    System.out.println("You attacked " + enemyName);

                    int damageTaken = healthBefore - healthAfter;

                    if (damageTaken > 0) {
                        System.out.println(enemyName + " attacks you back for " + damageTaken + " damage");
                    }
                    else {
                        System.out.println(enemyName + " tried to attack you, but could not use its weapon");
                    }
                }
                else if (attackResult == AttackResult.ENEMY_DIED) {
                    System.out.println("You killed " + enemyName + " and they dropped their weapon");
                }
            }

            else if (command.startsWith("eat ")) {
                String itemName = command.substring(4);
                Item item = adventure.findItem(itemName);

                // Gemmer health før vi spiser, så vi kan sammenligne bagefter
                int healthBefore = adventure.getHealth();
                EatResult result = adventure.eat(itemName);
                int healthAfter = adventure.getHealth();

                if (result == EatResult.NOT_FOUND) {
                    System.out.println("There is nothing like " + itemName + " to eat around here");

                } else if (result == EatResult.NOT_FOOD) {
                    System.out.println("You cannot eat " + item.getLongName());

                } else if (result == EatResult.EATEN) {

                    // Sammenligner health før og efter maden
                    if (healthAfter > healthBefore) {
                        System.out.println("You eat " + item.getLongName() + " you feel a little better ");
                    } else if (healthAfter < healthBefore) {
                        System.out.println("You eat " + item.getLongName() + " you feel a little worse");
                    } else {
                        System.out.println("You eat " + item.getLongName() + ".");
                    }
                }
                if (healthAfter <= 0) {
                    System.out.println("You died! Game over.");
                    break;
                }

            } else if (command.startsWith("drink ")) {
                String itemName = command.substring(6);
                Item item = adventure.findItem(itemName);
                int healthBefore = adventure.getHealth();
                DrinkResult result = adventure.drink(itemName);
                int healthAfter = adventure.getHealth();

                if (result == DrinkResult.NOT_FOUND) {
                    System.out.println("There is nothing like " + itemName + " to drink around here");
                } else if (result == DrinkResult.NOT_DRINKABLE) {
                    System.out.println("You cannot drink " + item.getLongName());
                } else if (result == DrinkResult.DRANK) {
                    if (healthAfter > healthBefore) {
                        System.out.println("You drink " + item.getLongName() + " and feel a little better");

                    } else if (healthAfter < healthBefore) {
                        System.out.println("You drink " + item.getLongName() + " and feel a little worse");

                    } else {
                        System.out.println("You drink " + item.getLongName());
                    }
                }
                if (healthAfter <= 0) {
                    System.out.println("You died! Game over.");
                    break;
                }

            } else if (command.equals("help")) {
                // Viser spilleren de tilgængelige kommandoer
                System.out.println("Commands: go north, go south, go east, go west, look, help, exit, take, drop, inventory, jump, eat, health, drink, equip, attack");

            } else if (command.equals("exit")) {
                System.out.println("Quitter");
                // Stopper while-loopet og dermed spillet
                break;

            } else if (command.equals("dansevand")) {
                System.out.println("Dance break!");

            } else if (command.equals("jump")) {
                System.out.println("You jumped too high, hit your head on the ceiling and died");
                break;
            } else if (command.equals("WASDdownupdownup")) {
                System.out.println("You have completed the game!");
                break;

            } else if (command.equals("health")) {
                int health = adventure.getHealth();
                // Viser forskellig tekst alt efter spillerens health
                if (health >= 100) {
                    System.out.println("health " + health + "- you are in perfect health");
                } else if (health >= 50) {
                    System.out.println("health " + health + "- you are in  good health, but avoid fighting right now");
                } else if (health >= 25) {
                    System.out.println("health " + health + "- you are wounded - find something healthy to eat");
                } else if (health >= 1) {
                    System.out.println("health " + health + "- you are barely alive");
                } else {
                    System.out.println("health " + health + "- you should be dead");
                }

            } else {
                // Hvis ingen af de kendte commands matcher
                System.out.println("I don't understand that command.");
            }

        }
    }
}