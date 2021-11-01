import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author Mihail Vaporakis
 * @version 2021.11.1
 */
public class Player
{
    private int health;
    private int maxHealth;
    private ArrayList<Item> inventory;  //Might not be used at this time
    private int carryWeight;            //Will be used with the inventory system
    private Game game;
    /**
     * Constructor for objects of class Player
     */
    public Player(int HP, Game controller)
    {
        health = HP;
        game = controller;
        maxHealth = health;
    }
    
    /**
     * Removes health from the player, and prints the remainder
     * Could also be used to heal the player
     * @param dmg int how much damage is inflicted on the player
     */
    public void playerDamaged(int dmg)
    {
        health = health - dmg;
        if(health > maxHealth)
        {
            health = maxHealth;
        }
        
        System.out.println("You have " + health + " HP left");
        checkDeath();
    }
    
    /**
     * Checks if the player health has dropped below zero, if it has it sends a death message.
     */
    private void checkDeath()
    {
        if(health <= 0)
        {
            System.out.println("You collapse to the floor as your vision fades to black");
            game.playerDeath();
        }
    }
    
    public void checkInventory()
    {
        System.out.println("Your inventory is currently empty");
    }
    
    public void addItem(Item item)
    {
        inventory.add(item);
    }
}