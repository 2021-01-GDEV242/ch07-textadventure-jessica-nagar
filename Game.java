/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room start, dep1Recep, dep1Cubicals, dep1Con, dep1Exec, dep1Admin, dep1Hallway, dep1Elevator, dep2Recep, dep2Cubicals, dep2Con, dep2Exec, dep2Admin,
                    dep2Hallway, dep2Elevator, cafe, dep1FloorClosed, dep2FloorClosed, end;
      
        // create the rooms
        start = new Room(" at the common area on the floor.");
        dep1Recep = new Room(" at the I.T. Department's reception desk. The door locks behind you. You can no longer go through this door.");
        dep1Cubicals = new Room(" at the I.T. cubicals.");
        dep1Con = new Room(" at the I.T. conference room.");
        dep1Exec = new Room(" in the Executive Director's office of the I.T. Department.");
        dep1Admin = new Room(" in the Admin's office of the I.T. Department.");
        dep1Hallway = new Room(" in the I.T. Department's hallway.");
        dep1Elevator = new Room(" in the elevator connected to the I.T. Department's wing.");
        dep2Recep = new Room (" at the Human Resources Department's reception desk. The door locks behind you. You can no longer go through this door.");
        dep2Cubicals = new Room (" at the HR cubicals.");
        dep2Con = new Room (" at the HR conference room.");
        dep2Exec = new Room (" in the Executive Director's office of the HR Department.");
        dep2Admin = new Room (" in the Admin's office of the HR Department.");
        dep2Hallway = new Room (" in the HR Department's hallway.");
        dep2Elevator = new Room (" in the elevator connected to the HR Department's wing.");
        cafe = new Room (" in the cafeteria.");
        dep1FloorClosed = new Room (" on a closed floor. You must go back.");
        dep2FloorClosed = new Room (" on a closed floor. You must go back.");
        end = new Room (" at the exit. You have successfully escaped! Congradulations!");
        
        // initialise room exits
        start.setExit("east", dep1Recep);
        start.setExit("south", dep2Recep);
        
        dep1Recep.setExit("south", dep1Cubicals);
        
        dep1Cubicals.setExit("north", dep1Recep);
        dep1Cubicals.setExit("east", dep1Con);
        dep1Cubicals.setExit("south", cafe);
        
        dep1Con.setExit("north", dep1Exec);
        dep1Con.setExit("east", dep1Admin);
        dep1Con.setExit("south", dep1Hallway);
        dep1Con.setExit("west", dep1Cubicals);
        
        dep1Exec.setExit("south", dep1Con);
        
        dep1Admin.setExit("west", dep1Con);
        
        dep1Hallway.setExit("north", dep1Con);
        dep1Hallway.setExit("east", dep1Elevator);
        dep1Hallway.setExit("west", cafe);
        
        dep1Elevator.setExit("up", dep1FloorClosed);
        dep1Elevator.setExit("down", end);
        
        dep2Recep.setExit("east", dep2Cubicals);
        
        dep2Cubicals.setExit("east", cafe);
        dep2Cubicals.setExit("south", dep2Con);
        dep2Cubicals.setExit("west", dep2Recep);
        
        dep2Con.setExit("north", dep2Cubicals);
        dep2Con.setExit("east", dep2Hallway);
        dep2Con.setExit("south", dep2Exec);
        dep2Con.setExit("west", dep2Admin);
        
        dep2Exec.setExit("north", dep2Con);
        
        dep2Admin.setExit("east", dep2Con);
        
        dep2Hallway.setExit("north", cafe);
        dep2Hallway.setExit("south", dep2Elevator);
        dep2Hallway.setExit("west", dep2Con);
        
        dep2Elevator.setExit("up", dep2FloorClosed);
        dep2Elevator.setExit("down", end);
        
        cafe.setExit("north", dep1Cubicals);
        cafe.setExit("east", dep1Hallway);
        cafe.setExit("south", dep2Hallway);
        cafe.setExit("west", dep2Cubicals);
        
        dep1FloorClosed.setExit("down", dep1Elevator);
        
        dep2FloorClosed.setExit("down", dep2Elevator);

        
        currentRoom = start;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Work Escape.");
        System.out.println("You are trapped in a maze of rooms.");
        System.out.println("There is only one room that leds to the outside.");
        System.out.println("Can you find the exit?");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                look();
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
}
