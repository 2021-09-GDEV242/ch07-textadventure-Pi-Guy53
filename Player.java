import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author Mihail Vaporakis
 * @version 2021.10.28
 */
public class Player
{
    private int health;
    private ArrayList<Item> inventory;  //Might not be used at this time
    private int carryWeight;            //Will be used with the inventory
    /**
     * Constructor for objects of class Player
     */
    public Player(int HP)
    {
        health = HP;
    }
    
    /**
     * Removes health from the player, and prints the remainder
     * Could also be used to heal the player
     * @param dmg int how much damage is inflicted on the player
     */
    public void playerDamaged(int dmg)
    {
        health = health - dmg;
        
        System.out.println("You have " + health + " HP left");
        
        
    }
    
    private void checkDeath()
    {
        
    }
}