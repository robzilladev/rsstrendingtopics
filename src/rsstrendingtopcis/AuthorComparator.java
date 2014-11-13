package rsstrendingtopcis;

import java.util.Comparator;

/**
 * Comparator for sorting by author name.
 * @author Rob Bloomfield
 */
public class AuthorComparator implements Comparator<RSSFeedItem>
{
    /**
     * Sorts by author name. Gets author from t and t1, and compares. Note that
     * sort is only done by first name.
     * @param t First RSSFeedItem
     * @param t1 Second RSSFeedItem
     * @return Negative, 0, positive for sort method.
     */
    @Override
    public int compare(RSSFeedItem t, RSSFeedItem t1)
    {
        return t.getAuthor().compareToIgnoreCase(t1.getAuthor());
    }
    
}
