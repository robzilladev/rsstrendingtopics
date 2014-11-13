package rsstrendingtopcis;

import java.util.Comparator;
/**
 * Comparator for sorting by feed title.
 * @author Rob Bloomfield
 */
public class FeedTitleComparator implements Comparator<RSSFeedItem>
{
    /**
     * Sorts by feed title. Gets feed title from t and t1, and compares.
     * @param t First RSSFeedItem
     * @param t1 Second RSSFeedItem
     * @return Negative, 0, positive for sort method.
     */
    @Override
    public int compare(RSSFeedItem t, RSSFeedItem t1)
    {
        return t.getFeedTitle().compareToIgnoreCase(t1.getFeedTitle());
    }
    
}
