package rsstrendingtopcis;

import java.util.Comparator;
/**
 * Comparator for sorting by word count. Only relevant if items have been
 * analysed for trending words prior to calling this sort.
 * @author Rob Bloomfield
 */
public class WordCountComparator implements Comparator<RSSFeedItem>
{
    /**
     * Sorts by word count. Gets number of words and compares.
     * @param t First RSSFeedItem
     * @param t1 Second RSSFeedItem
     * @return Negative, 0, positive for sort method.
     */
    @Override
    public int compare(RSSFeedItem t, RSSFeedItem t1)
    {
        return t1.getWordCount() - t.getWordCount();
    }
    
}
