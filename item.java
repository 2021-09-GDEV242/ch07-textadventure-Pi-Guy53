
/**
 * Write a description of class item here.
 *
 * @author Mihail Vaporakis
 * @version 2021.10.28
 */
public class item
{
    private String description;
    private int weight;
    
    /**
     * Constructor for objects of class item
     * @param description String what the item is/looks like
     * @param weight int how heavy the item is
     */
    public item(String itemInfo, int grams)
    {
        description = itemInfo;
        weight = grams;
    }
    
    public String getInfo()
    {
        return description + " " + weight;
    }
    
    public int getWeight()
    {
        return weight;
    }
}