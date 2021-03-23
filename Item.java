
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String itemDescription;
    private int itemWeight;

    /**
     * Constructor for objects of class Item
     */
    public Item()
    {
        // initialise instance variables
        itemDescription = " ";
        itemWeight = 0;
    }

    /**
     * 
     */
    public Item(String description, int weight)
    {
        itemDescription = description;
        itemWeight = weight;
    }
    
    public String getItemDescription()
    {
        String itemString = "Item Description: ";
        itemString += this.itemDescription +"\n";
        itemString += "Item Weight: "+this.itemWeight;
        return itemString;
    }
}
