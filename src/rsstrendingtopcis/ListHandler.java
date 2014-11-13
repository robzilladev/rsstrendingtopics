package rsstrendingtopcis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains all the list data and the methods associated with manipulating them.
 * Once the initial list is populated, it is passed here for all future reference.
 * A second list, sorted, is created as a copy of the default list, and is the
 * only list that is changed. Sorted will be the displayed list whenever a sort
 * or word count has occurred. The trending list holds all items added via 
 * the trending words methods.
 * @author Rob Bloomfield
 */
public class ListHandler
{
    /**
     * Default list. List is never manipulated.
     */
    private List<RSSFeedItem> list;
    /**
     * Sorted list. List is manipulated via sort/reverse and used to identify
     * trending words. Sorted is used for every situation when the DEFAULT
     * radio button is NOT selected.
     */
    private List<RSSFeedItem> sorted;
    /**
     * Trending words list. RSSFeedItems are added once they have been identified
     * as 'trending' based on the word counts. List is cleared every time a new 
     * word is selected.
     */
    private List<RSSFeedItem> trending;
    /**
     * Comparator to sort by item title.
     */
    private final ItemTitleComparator titleComp;
    /**
     * Comparator to sort by feed title.
     */
    private final FeedTitleComparator feedTitleComp;
    /**
     * Comparator to sort by publication date.
     */
    private final DateComparator dateComp;
    /**
     * Comparator to sort by description length.
     */
    private final DescriptionComparator descComp;
    /**
     * Comparator to sort by feed description length.
     */
    private final FeedDescriptionComparator feedDescComp;
    /**
     * Comparator to sort by word count association.
     */
    private final WordCountComparator wordComp;
    /**
     * Comparator to sort by link length.
     */
    private final ItemLinkComparator linkComp;
    /**
     * Comparator to sort by author name.
     */
    private final AuthorComparator authComp;
    
    /**
     * Initialises the list handler with three new ArrayLists, and initialises
     * the required comparators.
     */
    public ListHandler()
    {
        list = new ArrayList<>();
        sorted = new ArrayList<>();
        trending = new ArrayList<>();
        
        // Initialises comparators.
        titleComp = new ItemTitleComparator();
        feedTitleComp = new FeedTitleComparator();
        dateComp = new DateComparator();
        descComp = new DescriptionComparator();
        feedDescComp = new FeedDescriptionComparator();
        wordComp = new WordCountComparator();
        linkComp = new ItemLinkComparator();
        authComp = new AuthorComparator();
    }
    
    /**
     * Sets up the lists. Takes in a new list and sets it as the default list,
     * and copies its contents into a second list, called sorted.
     * @param newList The list to be set up as the default list.
     */
    public void initialiseDefaultAndSorted(List<RSSFeedItem> newList)
    {
        list = newList;
        for (int i = 0; i<list.size();i++)
        {
            sorted.add(list.get(i));
        }
        System.out.println("Default: " + list.size() +" \tSorted: "+ sorted.size());
    }
    
    /**
     * Sorts the sorted list. Sorts the list based on the string and boolean
     * that is passed. For example, if "author" and true are passed, then
     * the list will be sorted by Author name, and then the list will be
     * reversed.
     * @param s Sort criteria ("author", "title" etc.)
     * @param reverse Determines whether the list will be reversed or not.
     * 
     */
    public void sort(String s, boolean reverse)
    {
        switch (s)
        {
            case "feedTitle":
                Collections.sort(sorted,feedTitleComp);
                if (reverse == true)
                {
                    Collections.reverse(sorted);
                }
                break;
            case "author":
                Collections.sort(sorted,authComp);
                if (reverse == true)
                {
                    Collections.reverse(sorted);
                }
                break;
            case "itemTitle": 
                Collections.sort(sorted,titleComp);
                if (reverse == true)
                {
                    Collections.reverse(sorted);
                }
                break;
            case "itemDescription": 
                Collections.sort(sorted,descComp);
                if (reverse == true)
                {
                    Collections.reverse(sorted);
                }
                break;
            case "itemDate": 
                Collections.sort(sorted,dateComp);
                if (reverse == true)
                {
                    Collections.reverse(sorted);
                }
                break;
            case "feedDesc": 
                Collections.sort(sorted,feedDescComp);
                if (reverse == true)
                {
                    Collections.reverse(sorted);
                }
                break;
            case "randomise": 
                Collections.shuffle(sorted);
                break;
            case "trendingWordCount": 
                Collections.sort(sorted,wordComp);
                break;
            case "itemLink":
                Collections.sort(sorted,linkComp);
                if (reverse == true)
                {
                    Collections.reverse(sorted);
                }
                break;
        }
    }
    
    /**
     * Gets the default list.
     * @return The default list.
     */
    public List<RSSFeedItem> getList()
    {
        return list;
    }
        
    /**
     * Gets the sorted list.
     * @return The sorted list.
     */
    public List<RSSFeedItem> getSorted()
    {
        return sorted;
    }

    /**
     * Gets the trending list.
     * @return The trending list.
     */
    public List<RSSFeedItem> getTrending()
    {
        return trending;
    }
    
    /**
     * Returns true if the sorted list contains elements (not empty).
     * @return !isEmpty, true if not empty.
     */
    public boolean sortPopulated()
    {
        return !sorted.isEmpty();
    }

    /**
     * Returns true if the default list contains elements (not empty).
     * @return !isEmpty, true if not empty.
     */
    public boolean listPopulated()
    {
        return !list.isEmpty();
    }
    
    /**
     * Clears the sorted list of all elements.
     */
    public void clearSorted()
    {
        sorted.clear();
    }

    /**
     * Clears the sorted list of all elements.
     */
    public void clearList()
    {
        list.clear();
    }
    /**
     * Adds a RSSFeedItem to the trending words list. Called when a word is 
     * selected from the trending JLIST, all words that contain the associated
     * word are added to this list.
     * @param addMe RSSFeedItem to be added.
     */
    public void addToTrending(RSSFeedItem addMe)
    {
        trending.add(addMe);
    }

    /**
     * Returns true if the trending list contains elements (not empty).
     * @return !isEmpty, true if not empty.
     */
    public boolean trendingPopulated()
    {
        return !trending.isEmpty();
    }

    /**
     * Clears the trending words list of all elements.
     */
    public void clearTrending()
    {
        trending.clear();
    }
}
