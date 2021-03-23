/**
 *  This class is the main class of the "Work Escape" application. 
 *  "Work Escape" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Jessica Nagar
 * @version 2021.03.22
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room prevRoom;
        
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
      
        //create item objects
        Item startItems[] = {new Item("Nothing", 0)};
        Item dep1RecepItems[] = {new Item("Welcome Sign", 0), new Item("Bell", 0), new Item("Plant", 0)};
        Item dep1CubicalsItems[] = {new Item("Files", 10), new Item("Phone", 15)};
        Item dep1ConItems[] = {new Item("Projector", 50), new Item("Phone", 15)};
        Item dep1ExecItems[] = {new Item("Laptop", 100), new Item("Calendar", 25), new Item("Phone", 15)};
        Item dep1AdminItems[] = {new Item("Laptop", 100), new Item("Calendar", 25), new Item("Phone", 15)};
        Item dep1HallwayItems[] = {new Item("Painting", 0), new Item("Plant", 0)};
        Item dep1ElevatorItems[] = {new Item("Buttons", 0), new Item ("Light", 0)};
        Item dep2RecepItems[] = {new Item("Welcome Sign", 0), new Item("Bell", 0), new Item("Plant", 0)};
        Item dep2CubicalsItems[] = {new Item("Files", 10), new Item("Phone", 15)};
        Item dep2ConItems[] = {new Item("Projector", 50), new Item("Phone", 15)};
        Item dep2ExecItems[] = {new Item("Laptop", 100), new Item("Calendar", 25), new Item("Phone", 15)};
        Item dep2AdminItems[] = {new Item("Laptop", 100), new Item("Calendar", 25), new Item("Phone", 15)};
        Item dep2HallwayItems[] = {new Item("Painting", 0), new Item("Plant", 0)};
        Item dep2ElevatorItems[] = {new Item("Buttons", 0), new Item ("Light", 0)};
        Item cafeItems[] = {new Item("Donut", 25), new Item("Pizza", 75), new Item("Sandwich", 50)};
        Item dep1FloorClosedItems[] = {new Item("Closed Sign", 0), new Item("Plant", 0)};
        Item dep2FloorClosedItems[] = {new Item("Closed Sign", 0), new Item("Plant", 0)};
        Item endItems[] = {new Item("Nothing", 0)};
                    
        /**  8.20 - Single item in each room
         * 
         * Item startItem, dep1RecepItem, dep1CubicalsItem, dep1ConItem, dep1ExecItem, dep1AdminItem, dep1HallwayItem, dep1ElevatorItem, dep2RecepItem, dep2CubicalsItem,
                    dep2ConItem, dep2ExecItem, dep2AdminItem, dep2HallwayItem, dep2ElevatorItem, cafeItem, dep1FloorClosedItem, dep2FloorClosedItem, endItem;
        
        startItem = new Item();
        dep1RecepItem = new Item("Welcome Sign: Please wait to be helped", 0);
        dep1CubicalsItem = new Item("Files: Computer diagnostics", 10);
        dep1ConItem = new Item("Projector: Used for presentations", 50);
        dep1ExecItem = new Item("Laptop: Check emails", 100);
        dep1AdminItem = new Item("Calendar: Set up meetings", 25);
        dep1HallwayItem = new Item("Painting: Wall art", 0);
        dep1ElevatorItem = new Item("Buttons: To get to other floors", 0);
        dep2RecepItem = new Item("Welcome Sign: Please wait to be helped", 0);
        dep2CubicalsItem = new Item("Files: Resumes", 10);
        dep2ConItem = new Item("Projector: Used for presentaions", 50);
        dep2ExecItem = new Item("Laptop: Check emails", 100);
        dep2AdminItem = new Item("Calendar: Set up meetings", 25);
        dep2HallwayItem = new Item("Painting: Wall art", 0);
        dep2ElevatorItem = new Item("Buttons: To get to other floors", 0);
        cafeItem = new Item("Donut: to eat", 75);
        dep1FloorClosedItem = new Item("Closed Sign: Floor is closed", 0);
        dep2FloorClosedItem = new Item("Closed Sign: Floor is closed", 0);
        endItem = new Item();
         
        
        start = new Room(" at the common area on the floor", startItem);
        dep1Recep = new Room(" at the I.T. Department's reception desk. The door locks behind you. You can no longer go through this door", dep1RecepItem);
        dep1Cubicals = new Room(" at the I.T. cubicals", dep1CubicalsItem);
        dep1Con = new Room(" at the I.T. conference room.", dep1ConItem);
        dep1Exec = new Room(" in the Executive Director's office of the I.T. Department", dep1ExecItem);
        dep1Admin = new Room(" in the Admin's office of the I.T. Department", dep1AdminItem);
        dep1Hallway = new Room(" in the I.T. Department's hallway", dep1HallwayItem);
        dep1Elevator = new Room(" in the elevator connected to the I.T. Department's wing", dep1ElevatorItem);
        dep2Recep = new Room (" at the Human Resources Department's reception desk. The door locks behind you. You can no longer go through this door", dep2RecepItem);
        dep2Cubicals = new Room (" at the HR cubicals", dep2CubicalsItem);
        dep2Con = new Room (" at the HR conference room", dep2ConItem);
        dep2Exec = new Room (" in the Executive Director's office of the HR Department", dep2ExecItem);
        dep2Admin = new Room (" in the Admin's office of the HR Department", dep2AdminItem);
        dep2Hallway = new Room (" in the HR Department's hallway", dep2HallwayItem);
        dep2Elevator = new Room (" in the elevator connected to the HR Department's wing", dep2ElevatorItem);
        cafe = new Room (" in the cafeteria", cafeItem);
        dep1FloorClosed = new Room (" on a closed floor. You must go back", dep1FloorClosedItem);
        dep2FloorClosed = new Room (" on a closed floor. You must go back", dep2FloorClosedItem);
        end = new Room (" at the exit. You have successfully escaped! Congradulations", endItem);
        */  
        
        // create the rooms
        start = new Room(" at the common area on the floor");
        dep1Recep = new Room(" at the I.T. Department's reception desk. The door locks behind you. You can no longer go through this door");
        dep1Cubicals = new Room(" at the I.T. cubicals");
        dep1Con = new Room(" at the I.T. conference room.");
        dep1Exec = new Room(" in the Executive Director's office of the I.T. Department");
        dep1Admin = new Room(" in the Admin's office of the I.T. Department");
        dep1Hallway = new Room(" in the I.T. Department's hallway");
        dep1Elevator = new Room(" in the elevator connected to the I.T. Department's wing");
        dep2Recep = new Room (" at the Human Resources Department's reception desk. The door locks behind you. You can no longer go through this door");
        dep2Cubicals = new Room (" at the HR cubicals");
        dep2Con = new Room (" at the HR conference room");
        dep2Exec = new Room (" in the Executive Director's office of the HR Department");
        dep2Admin = new Room (" in the Admin's office of the HR Department");
        dep2Hallway = new Room (" in the HR Department's hallway");
        dep2Elevator = new Room (" in the elevator connected to the HR Department's wing");
        cafe = new Room (" in the cafeteria");
        dep1FloorClosed = new Room (" on a closed floor. You must go back");
        dep2FloorClosed = new Room (" on a closed floor. You must go back");
        end = new Room (" at the exit. You have successfully escaped! Congradulations");
        
        // add item into room
        start = addItemsToRoom(start, startItems);
        dep1Recep = addItemsToRoom(dep1Recep, dep1RecepItems);
        dep1Cubicals = addItemsToRoom(dep1Cubicals, dep1CubicalsItems);
        dep1Con = addItemsToRoom(dep1Con, dep1ConItems);
        dep1Exec = addItemsToRoom(dep1Exec, dep1ExecItems);
        dep1Admin = addItemsToRoom(dep1Admin, dep1AdminItems);
        dep1Hallway = addItemsToRoom(dep1Hallway, dep1HallwayItems);
        dep1Elevator = addItemsToRoom(dep1Elevator, dep1ElevatorItems);
        dep2Recep = addItemsToRoom(dep2Recep, dep2RecepItems);
        dep2Cubicals = addItemsToRoom(dep2Cubicals, dep2CubicalsItems);
        dep2Con = addItemsToRoom(dep2Con, dep2ConItems);
        dep2Exec = addItemsToRoom(dep2Exec, dep2ExecItems);
        dep2Admin = addItemsToRoom(dep2Admin, dep2AdminItems);
        dep2Hallway = addItemsToRoom(dep2Hallway, dep2HallwayItems);
        dep2Elevator = addItemsToRoom(dep2Elevator, dep2ElevatorItems);
        cafe = addItemsToRoom(cafe, cafeItems);
        dep1FloorClosed = addItemsToRoom(dep1FloorClosed, dep1FloorClosedItems);
        dep2FloorClosed = addItemsToRoom(dep2FloorClosed, dep2FloorClosedItems);
        end = addItemsToRoom(end, endItems);
        
        
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

        
        currentRoom = start;  // start game at the start
        prevRoom = null;
    }
    
    /**
     * add array items to room the return room
     */
    private Room addItemsToRoom(Room room, Item items[])
    {
        for(int i = 0; i < items.length; i++)
        {
            room.addItem(items[i]);
        }
        return room;
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
                
            case EAT:
                eat();
                break;
                
            case BACK:
                back();
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
        System.out.println("around at the office.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param command The command to be processed
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
            prevRoom = currentRoom;
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
    
    /**
     * get the room description by using the look command
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * eat a cookie by using the eat command
     */
    private void eat()
    {
        System.out.println("You have eaten a cookie.");
    }
    
    /**
     * go back to the previous room by using the back command
     */
    private void back()
    {
        currentRoom = prevRoom;
        System.out.println(currentRoom.getLongDescription());
    }
        
}
