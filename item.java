
/**
 * Write a description of class item here.
 *
 * @author Mihail Vaporakis
 * @version 2021.10.28
 */
public class Item
{
    private String description;
    private int weight;
    
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
}