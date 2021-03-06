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
    private ArrayList<Item> inventory;
    private int carryWeight;
    private Game game;
    /**
     * Constructor for objects of class Player
     */
    public Player(int HP, Game controller)
    {
        health = HP;
        game = controller;
        maxHealth = health;
        inventory = new ArrayList<Item>();
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

    /**
     * Checks the players inventory
     */
    public void checkInventory()
    {
        if(inventory.size() == 0)
        {
            System.out.println("Your inventory is currently empty");
        }
        else
        {
            System.out.println("You have: ");
            for(Item item_ : inventory)
            {
                System.out.print(item_.getName() +", ");
            }
        }
    }

    /**
     * adds an item to the player inventory
     * @param the item to add
     */
    
    public void addItem(Item item)
    {
        inventory.add(item);
        System.out.println("Added " + item.getName() + " to your inventory");
    }
    
    public Item dropItem(String name)
    {   
        for(Item item_: inventory)
        {
            if(item_.getName().equals(name))
            {
                return item_;
            }
        }
        
        return null;
    }
    
    /**
     * removes an item from the inventory
     * @param item to be removed 
     */
    public void removeItem(Item item)
    {
        inventory.remove(item);
    }
}