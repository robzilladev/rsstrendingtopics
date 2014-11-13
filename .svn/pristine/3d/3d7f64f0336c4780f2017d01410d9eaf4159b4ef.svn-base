package rsstrendingtopcis;

import java.util.Comparator;
/**
 * Comparator for sorting by feed description length.
 * @author Rob Bloomfild
 */
public class FeedDescriptionComparator implements Comparator<RSSFeedItem>
{
    /**
     * Sorts by feed description length. Gets feed description from t and t1, 
     * and compares length.
     * @param t First RSSFeedItem
     * @param t1 Second RSSFeedItem
     * @return Negative, 0, positive for sort method.
     */
    @Override
    public int compare(RSSFeedItem t, RSSFeedItem t1)
    {
        return t.getFeedDescription().length() - t1.getFeedDescription().length();
    }
    
}
