package rsstrendingtopcis;

import assignmentunittestloader.UnitTestLoader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.JDOMParseException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Rob
 */
public class RSSFeedItemExtractorTest
{
    RSSFeedItem instance;
    UnitTestLoader test;
    
    /**
     *
     */
    public RSSFeedItemExtractorTest()
    {
    }
    
    /**
     *
     * @throws ParseException
     * @throws JDOMException
     * @throws JDOMParseException
     * @throws IOException
     */
    @Before
    public void setUp() throws ParseException, JDOMException, JDOMParseException, IOException
    {
        instance = new RSSFeedItem();
        Document doc = RSSFeedRetriever.getFeedFromFile("tech_feed_7_item.xml");
        
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
                //--------------------------------------------------------------
                // FEED TITLE & DESCRIPTION
                //--------------------------------------------------------------
                try
                {
                    if (channel.getChild("title") == null)
                    {
                        instance.setFeedTitle("Unknown feed title..");
                        throw new RSSFeedException("feed title", 0);
                    }
                    else
                    {    
                        instance.setFeedTitle(channel.getChild("title").getText());
                    }  
                }
                catch (RSSFeedException e)
                {
                    System.out.println(e);
                }
                instance.setFeedDescription(feedDesc);
                //--------------------------------------------------------------
                // ITEM TITLE
                //--------------------------------------------------------------
                try
                {
                    if (item.getChild("title") == null)
                    {
                        instance.setItemTitle("Unknown item title..");
                        throw new RSSFeedException("item title", 0);
                    }
                    else
                    {    
                        instance.setItemTitle(item.getChild("title").getText());
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
                        instance.setItemLink("Unknown item link..");
                        throw new RSSFeedException("link", 0);
                    }
                    else
                    {    
                        instance.setItemLink(item.getChild("link").getText());
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
                        instance.setItemDate("Mon, 25 Dec 1901 00:00:00 -0700");
                        throw new RSSFeedException("date", 0);
                    }
                    else
                    {    
                        instance.setItemDate(item.getChildText("pubDate"));
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
                        instance.setItemDescription("No description found..");
                        throw new RSSFeedException("description", 0);
                    }
                    else
                    {    
                        instance.setItemDescription
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
                        instance.setAuthor("Unknown author..");
                        throw new RSSFeedException("author", 0);
                    }
                    else
                    {    
                        instance.setAuthor(item.getChild("author").getText());
                    }
                }
                catch (RSSFeedException e)
                {
                    System.out.println(e);
                }
                //--------------------------------------------------------------
            test = new UnitTestLoader();
            test.load("tech_feed_7_item.ut");
    }
    }
    
    /**
     * Test of getItemTitle method, of class RSSFeedItem.
     */
    @Test
    public void testGetItemTitle() {
        String result = instance.getItemTitle();
        String expResult = test.getFirstItemTitle();
        System.out.println("getItemTitle [ Expecting: " + expResult + " | Actual: " + result+"]");
        assertEquals(expResult, result);
    }
    /**
     * Test of getItemDate method, of class RSSFeedItem.
     */
    @Test
    public void testGetDate() {
        String result = instance.getStringDate();
        String expResult = test.getFirstItemDate();
        System.out.println("getItemDate [ Expecting: " + expResult + " | Actual: " + result+"]");
        assertEquals(expResult, result);
    }
    /**
     * Test of getItemDescription method, of class RSSFeedItem.
     */
    @Test
    public void testGetDescription() {
        String result = instance.getOriginalDesc();
        String expResult = test.getFirstItemDescription();
        System.out.println("getItemDescription [ Expecting: " + expResult + " | Actual: " + result+"]");
        assertEquals(expResult, result);
    }
    
    /**
     * Test for null. (no author tags in XML)
     */
    @Test
    public void testNullValue() {
        
        String result = instance.getAuthor();
        String expResult = "Unknown author..";
        System.out.println("Null Value [ Expecting: " + expResult + " | Actual: " + result+"]");
        assertEquals(expResult, result);
    }
}
