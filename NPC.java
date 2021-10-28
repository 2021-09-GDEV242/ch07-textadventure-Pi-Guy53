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
    private String name;
    private Room currentRoom;
    
    /**
     * Constructor for objects of class NPC
     * @param newName the NPC's name
     * @param advice the NPC's dialog
     * @param startingRoom the NPC's starting room
     */
    public NPC(String newName, String advice, Room startingRoom)
    {
        name = newName;
        dialog = advice;
        currentRoom = startingRoom;
    }
    
    /**
     * Prints out the NPC's name and dialog
     */
    public void talk()
    {
        System.out.println(name + ":");
        System.out.println(dialog);
    }
    
    /**
     * @return the NPC's current room
    */
    public Room getRoom()
    {
        return currentRoom;
    }
    
    /**
     * @return the NPC's name
     */
    public String getName()
    {
        return name;
    }
}