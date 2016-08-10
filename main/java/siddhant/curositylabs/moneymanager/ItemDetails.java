package siddhant.curositylabs.moneymanager;

import android.content.ClipData;

/**
 * Created by Student on 8/10/2016.
 */
public class ItemDetails {

    String ItemName,ItemCategory,Date;
    String ItemCost;

    public void setItemName(String ItemName)
    {
        this.ItemName = ItemName;
    }

    public void setItemCost(String ItemCost)
    {
        this.ItemCost = ItemCost;
    }

    public void setItemCategory(String ItemCategory)
    {
        this.ItemCategory = ItemCategory;
    }

    public void setDate(String Date)
    {
        this.Date = Date;
    }

    public String getItemName()
    {
        return ItemName;
    }

    public String getItemCost()
    {
        return ItemCost;
    }

    public String getItemCategory()
    {
        return  ItemCategory;
    }

    public String getDate()
    {
        return Date;
    }

}
