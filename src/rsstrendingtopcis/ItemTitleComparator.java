package rsstrendingtopcis;

import java.util.Comparator;
/**
 * Comparator for sorting by item title.
 * @author Rob Bloomfield
 */
public class ItemTitleComparator implements Comparator <RSSFeedItem>
{
    /**
     * Sorts by item title. Gets item title from t and t1, and compares.
     * @param t First RSSFeedItem
     * @param t1 Second RSSFeedItem
     * @return Negative, 0, positive for sort method.
     */
    @Override
    public int compare(RSSFeedItem t, RSSFeedItem t1)
    {
        return t.getItemTitle().compareToIgnoreCase(t1.getItemTitle());
    }
    
}
