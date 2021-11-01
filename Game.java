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
 * @version 2021.11.1
 */

public class Game 
{
    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }

    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Player player;
    //NPCs are Stored in the Game class instead of the Room class for 2 reasons
    // 1) so NPCs will eventually be able to move around via code, rather than several NPCs of the same name
    // code driven movement will not be included due to time constraints
    // 2) NPCs are able to get a Game class referance without having to pass the Game class through Rooms, and then to NPCs
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
        Room m8, m10, n9, m7;

        // create the rooms
        start = new Room("dropped inside of a small metal room. Glowing blue lights flicker along the ceiling.");
        i3 = new Room("in a dead end.");
        c5 = new Room("in a dead end.");
        e5 = new Room("in a short section of hallway. \nA set of 7 long parallel gouges mar the east wall...");
        g5 = new Room("in a large obervetory");

        i5 = new Room("in a tee junction, three doors lead off, other than that, the room is bare.");
        k5 = new Room("in what appears to be a barracks. You can hear a soft growl \ncoming from the back corrner.");
        g7 = new Room("what looks like a normal tee juntion, but only the east, \nand west doors can open. The north door is locked.");
        i7 = new Room("a dark hall, the lighting in this section appears to be offline,\nand details are hard to make out.");
        k7 = new Room("a Server farm. The hum of the fans provides a constent low drone. \nWhat would need this much prossesing though?");

        i9 = new Room("locked out of the south doorway, it just won't budge. \nThe lights have dimmed slightly from the previous room, \nbut you can still barely see.");
        k9 = new Room("in a work room, there is a computer terminal on the desk \nin front of you. The lights along its side shows it \nis operational.");
        l9 = new Room("logged into the computer. The background is of the night sky, but with 5 moons. \nthere are only 3 apps available though");
        g11 = new Room("startled by a dark form in the hallway. \nDespite the electice lights, there are still dark pockets of shadow.");
        i11 = new Room("walking though a smaller hallway than then before, \nbarely half a meter wide. \nbut you can see 7 parallel gouges on the walls an ceiling \neach at least 3 cm deep.");

        k11 = new Room("what looks like a janitors closet.");
        g13 = new Room("in an empty hanger. There are no ships, but the though the \nshielded opening you can see a dying star.");
        i13 = new Room("in yet another 4 way hub. The lights in here are orange rather than light blue.");
        k13 = new Room("unsure of what you are seeing. There looks to be a tree \nmade of rock crystal growing out of the floor.");
        i15 = new Room("in a medical bay. Locked cabinets line the walls.");

        m8 = new Room("looking are an ancient email server. Cobwebs dust most of the messages... \nthere is one newer message though");
        m10 = new Room("opening the Ren'py application. \nthere are no files present to open");
        n9 = new Room("now finidng out that a space ship does not have the internet \nneeded to run a web browser.");
        m7 = new Room("reading the newest email, it reads. \n\"What are you doing on my ship\"");

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

        g7.setExit("east", start);
        g7.setExit("west", g5);
        //no north door, "Its a trap!"

        i7.setExit("south", g7);
        i7.setExit("east", i9);
        i7.setExit("west", i5);

        k7.setExit("east", k9);

        i9.setExit("north", k9);
        i9.setExit("west", i7);
        //no south door, "Its a trap!"

        k9.setExit("computer", l9);
        k9.setExit("south", i9);
        k9.setExit("east", k11);
        k9.setExit("west", k7);

        l9.setExit("exit", k9);
        l9.setExit("mail", m8);
        l9.setExit("renpy", m10);
        l9.setExit("crome", n9);

        m8.setExit("exit", k9);
        m8.setExit("menu", l9);
        m8.setExit("email", m7);

        m7.setExit("exit", k9);
        m7.setExit("menu", l9);

        m10.setExit("exit", k9);
        m10.setExit("menu", l9);

        n9.setExit("exit", k9);
        n9.setExit("menu", l9);

        g11.setExit("north", i11);
        g11.setExit("west", start);

        i11.setExit("south", g11);
        i11.setExit("east", i13);

        k11.setExit("west", k9);

        g13.setExit("north", i13);

        i13.setExit("north", k13);
        i13.setExit("south", g13);
        i13.setExit("east", i15);
        i13.setExit("west", i11);

        k13.setExit("south", i13);

        i15.setExit("west", i13);

        currentRoom = start;  // starts the game at start
        previousRoom = start;

        start.addItem("chain", 100);
        i15.addItem("bottle", 2);
        i15.addItem("syringe", 3);
        c5.addItem("piece_of_broken_glass", 10);
        k5.addItem("pool_of_blood", 0);
        k13.addItem("crystal_shard", 4);

        //Add the NPCs
        npcs.add(new NPC(this, "The Chain Master", "Welcome traveller. Prepare to meet your fate.", start, 0));
        //NPCs don't have the coding to move, but the same one can apear in differnt places
        npcs.add(new NPC(this, "The Chain Master", "Traveller, you really must at least Try to escape.", c5, 0));
        npcs.add(new NPC(this, "A telescope", "Through the telescope you can see unknow constellations, you are a long way from home", g5, 0));
        npcs.add(new NPC(this, "The dark shape", "*growl* *Hiss* a claw rakes at you from the darkness", k5, 2));
        npcs.add(new NPC(this, "The Chain Master", "You should not be here. \n*a lighting bolt lances out at you", k7, 1));
        npcs.add(new NPC(this, "The dark form", "*roar* dark purple teeth snap at you", g11, 2));
        npcs.add(new NPC(this, "The Chain Master", "Are you looking for healing?", i15, -2));
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
        System.out.println("Welcome to the World of Games and Fate!");
        System.out.println("World of Games and Fate is an adventure of peril, and survival.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        getNPC();
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

            case EAT:
                eatFood();
                break;

            case TAKEITEM:
                takeItem(command);
                break;

            case DROPITEM:
                dropItem(command);
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
        System.out.println("You are lost. You are NOT alone. You wander");
        System.out.println("through a mysterious complex.");
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
            getNPC();
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
        getNPC();
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
        getNPC();
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
     * checks for an NPC, and prompts the player if there is
     */
    private void getNPC()
    {
        for(NPC npc : npcs)
        {
            boolean npcPresent = false;
            if(npc.getRoom() == currentRoom)
            {
                System.out.print(npc.getName() + " is standing there, ");
                npcPresent = true;
            }
            if(npcPresent)
            {
                System.out.println("type 'talk' to interact with them");
            }
        }
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

    /**
     * @return the player character
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * Eat command, does nothing but textual feedback
     */
    private void eatFood()
    {
        System.out.println("You pull food package out of your pocket, \n and consume the contents, you feel a bit better.");
    }

    /**
     * Pick up an item
     * @param the command word
     */
    private void takeItem(Command command)
    {
        Item item;
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Take what?");
            return;
        }

        item = currentRoom.returnItem(command.getSecondWord());

        if (item == null) {
            System.out.println("There is no item!");
        }
        else 
        {
            player.addItem(item);
            currentRoom.removeItem(item);
        }
    }

    /**
     * Drops an item
     * @param command word
     */
    private void dropItem(Command command)
    {
        Item item;
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Drop what?");
            return;
        }

        item = player.dropItem(command.getSecondWord());
        player.removeItem(item);
        if (item == null) {
            System.out.println("There is no item!");
        }
        else 
        {
            currentRoom.addItem(item.getName(), item.getWeight());
        }
    }
}