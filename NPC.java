import java.util.Random;
/**
 * NPC's will offer the player cryptic advise as they move through the enviroment.
 *
 * @author Mihail Vaporakis
 * @version 2021.10.28
 */
public class NPC
{
    private String dialog;
    private Room currentRoom;
    private Game game;
    
    /**
     * Constructor for objects of class NPC
     */
    public NPC(Game controller, String advice, Room startingRoom)
    {
        dialog = advice;
        game = controller;
        currentRoom = startingRoom;
    }
}