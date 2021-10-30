import java.util.ArrayList;
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
 * @author  Mihail Vaporakis
 * @version 2021.10.28
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Player player;
    private ArrayList<NPC> npcs;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        npcs = new ArrayList<NPC>();
        createRooms();
        parser = new Parser();
        player = new Player(5, this);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room start, i3, c5, e5, g5;
        Room i5, k5, g7, i7, k7;
        Room i9, k9, l9, g11, i11;
        Room k11, g13, i13, k13, i15;

        // create the rooms
        start = new Room("outside the main entrance of the university");
        i3 = new Room("outside the main entrance of the university");
        c5 = new Room("outside the main entrance of the university");
        e5 = new Room("outside the main entrance of the university");
        g5 = new Room("outside the main entrance of the university");
        
        i5 = new Room("outside the main entrance of the university");
        k5 = new Room("outside the main entrance of the university");
        g7 = new Room("outside the main entrance of the university");
        i7 = new Room("outside the main entrance of the university");
        k7 = new Room("outside the main entrance of the university");
        
        i9 = new Room("outside the main entrance of the university");
        k9 = new Room("outside the main entrance of the university");
        l9 = new Room("outside the main entrance of the university");
        g11 = new Room("outside the main entrance of the university");
        i11 = new Room("outside the main entrance of the university");
        
        k11 = new Room("outside the main entrance of the university");
        g13 = new Room("outside the main entrance of the university");
        i13 = new Room("outside the main entrance of the university");
        k13 = new Room("outside the main entrance of the university");
        i15 = new Room("outside the main entrance of the university");
        
        // initialise room exits
        start.setExit("north", i9);
        start.setExit("west", g7);
        start.setExit("east", g11);
        
        i3.setExit("east", i5);
        
        c5.setExit("north", e5);
        
        e5.setExit("north", g5);
        e5.setExit("south", c5);
        
        g5.setExit("east", g7);
        g5.setExit("south", e5);
        
        i5.setExit("north", k5);
        i5.setExit("east", i7);
        i5.setExit("west", i3);
        
        k5.setExit("south", i5);
        
        g7.setExit("north", i7);
        g7.setExit("east", start);
        g7.setExit("west", g5);
        
        i7.setExit("south", g7);
        i7.setExit("east", i9);
        i7.setExit("west", i5);
        
        k7.setExit("east", k9);
        
        i9.setExit("east", ##);
        k9.setExit("east", ##);
        l9.setExit("east", ##);
        g11.setExit("east", ##);
        i11.setExit("east", ##);
        
        k11.setExit("east", ##);
        g13.setExit("east", ##);
        i13.setExit("east", ##);
        k13.setExit("east", ##);
        i15.setExit("east", ##);

        currentRoom = outside;  // start game outside
        previousRoom = outside;

        //Add the NPCs
        npcs.add(new NPC("Joe", "Dialog", ##));
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
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        for(NPC npc : npcs)
        {
            if(npc.getRoom() == currentRoom)
            {
                System.out.print(npc.getName() + " is standing there, ");
            }
        }
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

            case LOOK:
                look();
                break;

            case INVENTORY:
                checkInventory();
                break;

            case GOBACK:
                goBack();
                break;

            case TALK:
                talkWithNPC();
                break;

            case QUIT:
                wantToQuit = quit(command);
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
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            for(NPC npc : npcs)
            {
                if(npc.getRoom() == currentRoom)
                {
                    System.out.print(npc.getName() + " is standing there, ");
                }
            }
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
     * Looks around the room again
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Checks your inventory
     */
    private void checkInventory()
    {
        player.checkInventory();
    }

    /**
     * Goes back one room, and only one room
     */
    private void goBack()
    {
        previousRoom = currentRoom;
        currentRoom = previousRoom;
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Go to this room when the player dies
     */
    public void playerDeath()
    {
        currentRoom = new Room("You have died.");
        previousRoom = currentRoom;
        look();
    }

    /**
     * Talk with an NPC if present
     */
    private void talkWithNPC()
    {
        for(NPC npc : npcs)
        {
            if(npc.getRoom() == currentRoom)
            {
                npc.talk();
            }
        }
    }
    /**
     * @return the current room
     */
    public Room getRoom()
    {
        return currentRoom;
    }
}