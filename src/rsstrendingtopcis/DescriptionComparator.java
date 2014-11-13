package rsstrendingtopcis;

import java.util.Comparator;
/**
 * Comparator for sorting by description length.
 * @author Rob Bloomfield
 */
public class DescriptionComparator implements Comparator<RSSFeedItem>
{
    /**
     * Sorts by description length. Gets description from t and t1, 
     * and compares the length.
     * @param t First RSSFeedItem
     * @param t1 Second RSSFeedItem
     * @return Negative, 0, positive for sort method.
     */
    @Override
    public int compare(RSSFeedItem t, RSSFeedItem t1)
    {
        return t.getItemDescription().length()-t1.getItemDescription().length();
    }
    
}
