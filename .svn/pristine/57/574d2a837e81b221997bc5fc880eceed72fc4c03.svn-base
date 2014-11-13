package rsstrendingtopcis;

import java.util.Comparator;

/**
 * Comparator for sorting by publication date
 * @author Rob Bloomfield
 */
public class DateComparator implements Comparator<RSSFeedItem>
{
    /**
     * Sorts by publication date. Gets date object from t and t1, and compares.
     * @param t First RSSFeedItem
     * @param t1 Second RSSFeedItem
     * @return Negative, 0, positive for sort method.
     */
    @Override
    public int compare(RSSFeedItem t, RSSFeedItem t1)
    {
        return t1.getItemDate().compareTo(t.getItemDate());
    }
    
}
