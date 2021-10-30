
/**
 * The Item class stores the data for item objects, this currently includes a 
 * description, and a weight in grams.
 *
 * @author Mihail Vaporakis
 * @version 2021.10.30
 */
public class Item
{
    private String description;
    private int weight;
    private boolean isTaken = false;    //ground work for potential inventory system
    
    /**
     * Constructor for objects of class item
     * @param itemInfo String what the item is/looks like
     * @param grams int how heavy the item is in grams
     */
    public Item(String itemInfo, int grams)
    {
        description = itemInfo;
        weight = grams;
    }
    
    /**
     * @return String of the item description and weight
     */
    public String getInfo()
    {
        return "It is a " + description + " that weighs " + weight + " grams.";
    }
    
    /**
     * @return int of the items weight
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * @return whether the item has been picked up or not
     */
    public boolean pickedUp()
    {
        return isTaken;
    }
}