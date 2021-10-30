import java.util.Random;
/**
 * NPC's will offer the player cryptic advise as they move through the enviroment.
 *
 * @author Mihail Vaporakis
 * @version 2021.10.30
 */
public class NPC
{
    private Game game;
    private String dialog;
    private String name;
    private Room currentRoom;
    private int damage;
    
    /**
     * Constructor for objects of class NPC
     * @param newName the NPC's name
     * @param advice the NPC's dialog
     * @param startingRoom the NPC's starting room
     */
    public NPC(Game controller, String newName, String advice, Room startingRoom, int dmg)
    {
        game = controller;
        name = newName;
        dialog = advice;
        currentRoom = startingRoom;
        damage = dmg;
    }
    
    /**
     * Prints out the NPC's name and dialog
     */
    public void talk()
    {
        System.out.println(name + ":");
        System.out.println(dialog);
        if(damage > 0 || damage < 0)
        {
            game.getPlayer().playerDamaged(damage);
        }
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