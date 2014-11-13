package rsstrendingtopcis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a single RSS item extracted from the list of documents. Each item
 * contains all data necessary for display in the GUI, and for sorting.
 * @author Rob Bloomfield
 */
public class RSSFeedItem
{
    /**
     * Title of feed.
     */
    private String feedTitle;
    /**
     * Description of feed.
     */
    private String feedDescription;
    /**
     * Title of item.
     */
    private String itemTitle;
    /**
     * Link of item.
     */
    private String itemLink;
    /**
     * Description of item.
     */
    private String itemDescription;
    /**
     * String representation of date.
     */
    private String stringDate;
    /**
     * Unmodified description string.
     */
    private String originalDesc;
    
    /**
     * Author of item.
     */
    private String author;
    /**
     * Formatter object for parsing the date.
     */
    private DateFormat formatter; 
    /**
     * Date object of item.
     */
    private Date itemDate;
    /**
     * Index of item.
     */
    private int itemIndex;
    /**
     * Count of associated trending words.
     */
    private int wordCount;
    /**
     * List of associated trending words.
     */
    private List<String> words;
    
    /**
     * Initialises the required fields. Sets up the date formatter, creates a 
     * new list of words and sets the word count to 0.
     */
    public RSSFeedItem()
    {
        formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        words = new ArrayList<>();
        wordCount = 0;
    }
    /**
     * Returns item title for display in the JLIST.
     * @return Item title.
     */
        @Override
    public String toString()
    {
        return itemTitle;
    }
   
    //--------------------------------------------------------------------------
    // Getters and Setters for all RSSFeedItem variables.
    //--------------------------------------------------------------------------

    /**
     * Gets feed title.
     * @return feed title.
     */
    public String getFeedTitle()
    {
        return feedTitle;
    }

    /**
     * Sets feed title.
     * @param feedTitle String to be set as feed title.
     * @throws RSSFeedException
     */
    public void setFeedTitle(String feedTitle) throws RSSFeedException
    {
        this.feedTitle = feedTitle;
    }

    /**
     * Gets item title.
     * @return Item title.
     */
    public String getItemTitle()
    {
        return itemTitle;
    }

    /**
     * Sets item title.
     * @param itemTitle String to be set as item title.
     * @throws RSSFeedException
     */
    public void setItemTitle(String itemTitle) throws RSSFeedException
    {
        this.itemTitle = itemTitle;
    }

    /**
     * Gets item link/URL.
     * @return Item link.
     */
    public String getItemLink()
    {
        return itemLink;
    }

    /**
     * Sets item link/URL.
     * @param itemLink String to be set as item link.
     * @throws RSSFeedException
     */
    public void setItemLink(String itemLink) throws RSSFeedException
    {
        this.itemLink = itemLink;
    }

    /**
     * Gets feed description.
     * @return Feed description.
     */
    public String getFeedDescription()
    {
        return feedDescription;
    }

    /**
     * Sets feed description.
     * @param feedDescription String to be set as feed description.
     */
    public void setFeedDescription(String feedDescription)
    {
        this.feedDescription = feedDescription;
    }

    /**
     * Gets item date. 
     * @return Item date as date object.
     */
    public Date getItemDate()
    {
        return itemDate;
    }

    /**
     * Returns string representation of date.
     * @return String representation of date.
     */
    public String getStringDate()
    {
        return stringDate;
    }
    
    /**
     * Sets item date. String is passed in and parsed to a date object.
     * @param itemDate String to be parsed to date object.
     * @throws ParseException
     */
    public void setItemDate(String itemDate) throws ParseException
    {
        stringDate = itemDate;
        this.itemDate = convertDate(itemDate);
    }

    /**
     * Gets item description.
     * @return Item description.
     */
    public String getItemDescription()
    {
        return itemDescription;
    }

    /**
     * Sets item description. String is passed and parsed for any extra HTML tags
     * which are removed. String is then trimmed to remove any additional spacing
     * on the ends of the string. An original copy of the string is kept for testing.
     * @param itemDescription String to be parsed and set as the item description.
     * @throws RSSFeedException
     */
    public void setItemDescription(String itemDescription) throws RSSFeedException
    {
        originalDesc = itemDescription;
        this.itemDescription = itemDescription;
        this.itemDescription = this.itemDescription.replaceAll("\\<[^>]*>","").trim();
    }

    /**
     * Gets index of item.
     * @return index of item.
     */
    public int getItemIndex()
    {
        return itemIndex;
    }

    /**
     * Sets index of item.
     * @param i Int to be set as item index.
     */
    public void setItemIndex(int i)
    {
        itemIndex = i;
    }

    /**
     * Gets author.
     * @return Author
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * Sets author. Parses string for brackets on ends of names as this was noted
     * to be common amongst RSS feed items.
     * @param author String to be parsed and set as author name.
     */
    public void setAuthor(String author)
    {
        this.author = author.replaceAll("[()]", "").trim();
    }
    
    /**
     * Converts the string representation of the date into a comparable
     * date object.
     * @param sDate String representation of date to be parsed.
     * @return Date object.
     * @throws ParseException
     */
        public Date convertDate(String sDate) throws ParseException
    {
        Date parsedDate = formatter.parse(sDate);
        return parsedDate;
    }  
    
    /**
     * Adds word to associated words list.
     * @param w Word to be added.
     */
    public void addWord(String w)
    {
        words.add(w);
    }

    /**
     * Gets list of associated words.
     * @return List of associated words.
     */
    public List<String> getWords()
    {
        return words;
    }

    /**
     * Gets the count associated with the word list.
     * @return Size of word list.
     */
    public int getWordCount()
    {
        return words.size();
    }

    public String getOriginalDesc()
    {
        return originalDesc;
    }
    
}
