package rsstrendingtopcis;

import java.util.Comparator;
/**
 * Comparator for sorting by item URL length.
 * @author Rob Bloomfield
 */
public class ItemLinkComparator implements Comparator <RSSFeedItem>
{
    /**
     * Sorts by item URL length. Gets URL from t and t1, and compares length.
     * @param t First RSSFeedItem
     * @param t1 Second RSSFeedItem
     * @return Negative, 0, positive for sort method.
     */
    @Override
    public int compare(RSSFeedItem t, RSSFeedItem t1)
    {
        return t1.getItemLink().length() - t.getItemLink().length();
    }
    
}
