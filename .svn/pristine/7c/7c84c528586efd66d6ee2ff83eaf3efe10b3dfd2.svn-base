package rsstrendingtopcis;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Generates a list of all RSSFeedItems from the supplied document list. Accepts
 * a list of documents, and parses all required elements. Creates a new RSSFeedItem
 * and attempts to add the parsed elements to it. An exception is thrown if a value
 * is null and a default value is set.
 * @author Rob
 */
public class RSSFeedItemExtractor
{
    /**
     * List of all RSSFeedItems. For every item identified within the XML, a new
     * RSSFeedItem object is created and added to the list.
     */
    private List<RSSFeedItem> feedItems;
    
    /**
     * Initialises the RSSFeedItem list.
     */
    public RSSFeedItemExtractor()
    {
        feedItems = new ArrayList<>();
    }

    /**
     * Accepts a list of RSS documents, and extracts all required items from it.
     * Returns a new list of all extracted items.
     * @param docList Document list to be parsed for items.
     * @return List of all extracted RSSFeedItems.
     * @throws ParseException
     */
    public List extractItems(List <Document> docList) throws ParseException
    {
        int itemCount = 0;
        for (int i = 0; i<docList.size();i++)
        {
            Document doc = docList.get(i);
            Element rss = doc.getRootElement();
            Element channel = rss.getChild("channel");
            String feedDesc;
            List<Element> items;
            items = channel.getChildren("item");
            
            //------------------------------------------------------------------
            // Feed Description is extracted at this level as the same value
            // will be true for multiple feed items. String of length 0 is also
            // accounted for.
            //------------------------------------------------------------------
            if (channel.getChild("description") != null)
                feedDesc = channel.getChild("description").getText();
            else 
                feedDesc = "Unknown feed description..";
            if (feedDesc.length() == 0)
                feedDesc = "Unknown feed description..";

            //------------------------------------------------------------------
            // Extracts all required elements from each 'item'. If the element is
            // null (does not exist), then an exception is thrown and caught, and
            // a default value is set for that element.
            //------------------------------------------------------------------
            for (Element item: items)
            {
                itemCount++;
                feedItems.add(new RSSFeedItem());
                //--------------------------------------------------------------
                // FEED TITLE & DESCRIPTION
                //--------------------------------------------------------------
                try
                {
                    if (channel.getChild("title") == null)
                    {
                        feedItems.get(feedItems.size()-1).setFeedTitle("Unknown feed title..");
                        throw new RSSFeedException("feed title", itemCount);
                    }
                    else
                    {    
                        feedItems.get(feedItems.size()-1).setFeedTitle
                                        (channel.getChild("title").getText());
                    }  
                }
                catch (RSSFeedException e)
                {
                    System.out.println(e);
                }
                feedItems.get(feedItems.size()-1).setFeedDescription(feedDesc);
                //--------------------------------------------------------------
                // ITEM TITLE
                //--------------------------------------------------------------
                try
                {
                    if (item.getChild("title") == null)
                    {
                        feedItems.get(feedItems.size()-1).setItemTitle("Unknown item title..");
                        throw new RSSFeedException("item title", itemCount);
                    }
                    else
                    {    
                        feedItems.get(feedItems.size()-1).setItemTitle
                                            (item.getChild("title").getText());
                    }
                }
                catch (RSSFeedException e)
                {
                    System.out.println(e);
                }
                //--------------------------------------------------------------
                // ITEM LINK (URL)
                //--------------------------------------------------------------
                try
                {
                    if (item.getChild("link") == null)
                    {
                        feedItems.get(feedItems.size()-1).setItemLink("Unknown item link..");
                        throw new RSSFeedException("link", itemCount);
                    }
                    else
                    {    
                        feedItems.get(feedItems.size()-1).setItemLink
                                            (item.getChild("link").getText());
                    }  
                }
                catch (RSSFeedException e)
                {
                    System.out.println(e);
                }
                //--------------------------------------------------------------
                // ITEM DATE
                //--------------------------------------------------------------
                try
                {
                    if (item.getChild("pubDate") == null)
                    {
                        feedItems.get(feedItems.size()-1).setItemDate
                                            ("Mon, 25 Dec 1901 00:00:00 -0700");
                        throw new RSSFeedException("date", itemCount);
                    }
                    else
                    {    
                        feedItems.get(feedItems.size()-1).setItemDate
                                                (item.getChildText("pubDate"));
                    }
                }
                catch (RSSFeedException e)
                {
                    System.out.println(e);
                }
                //--------------------------------------------------------------
                // ITEM DESCRIPTION
                //--------------------------------------------------------------
                try
                {
                    if (item.getChild("description") == null)
                    {
                        feedItems.get(feedItems.size()-1).setItemDescription
                                                        ("No description found..");
                        throw new RSSFeedException("description", itemCount);
                    }
                    else
                    {    
                        feedItems.get(feedItems.size()-1).setItemDescription
                            (item.getChild("description").getTextNormalize());
                    } 
                }
                catch (RSSFeedException e)
                {
                    System.out.println(e);
                }
                //--------------------------------------------------------------
                // Author
                //--------------------------------------------------------------
                try
                {
                    if (item.getChild("author") == null)
                    {
                        feedItems.get(feedItems.size()-1).setAuthor("Unknown author..");
                        throw new RSSFeedException("author", itemCount);
                    }
                    else
                    {    
                        feedItems.get(feedItems.size()-1).setAuthor
                                            (item.getChild("author").getText());
                    }
                }
                catch (RSSFeedException e)
                {
                    System.out.println(e);
                }
                //--------------------------------------------------------------
                feedItems.get(feedItems.size()-1).setItemIndex(itemCount);
            }
        }
        return feedItems;
    }
    
}
