import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Mihail Vaporakis
 * @version 2021.11.1
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;              //stores an ArrayList of items in this room
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<Item>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Adds an item to the room
     * @param itemInfo String of the items description
     * @param itemWeight int of the items weight in grams
     */
    public void addItem(String itemInfo, int itemWeight)
    {
        items.add(new Item(itemInfo, itemWeight));
    }
    
    /**
     * removes an item from the room
     * @param item to be removed
     */
    public void removeItem(Item item)
    {
        items.remove(item);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     *     Items present: It is a book and weights 13 grams
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + ".\n" + getItems();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    /**
     * @return the items present the room
     */
    public String getItems()
    {
        String returnString = "Items present:";
        for(Item item_ : items) {
            returnString += " " + item_.getInfo();
        }
        return returnString;
    }

    /**
     * adds an item to the players inventory
     * @param the item name to pick up
     */
    public Item returnItem(String itemName)
    {
        for(Item item_ : items)
        {
            if(item_.getName().equals(itemName))
            {
                return item_;
            }
        }
        return null;
    }
}