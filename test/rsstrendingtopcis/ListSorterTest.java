package rsstrendingtopcis;

import assignmentunittestloader.UnitTestLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rob
 */
public class ListSorterTest
{
    List<RSSFeedItem> items;
    ListHandler handler;
    
    /**
     *
     */
    public ListSorterTest()
    {
        handler = new ListHandler();
    }
    
    /**
     * Loads the initial list and extracts the items for sorting. 
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws RSSParseException
     * @throws ParseException
     */
    public void load() throws MalformedURLException, FileNotFoundException, RSSParseException, ParseException
    {
        String testFilePrefix = "tech_rssfeed_one_filelist";   
        
        // load the expected test output.
        UnitTestLoader test = new UnitTestLoader();
        test.load(testFilePrefix + ".ut");
        
        // load the test data.
        List<Document> feeds = null;
        try {
            feeds = RSSFeedRetriever.getFeeds("feeds" + File.separator + testFilePrefix+ ".txt");
        }
        catch (JDOMException | IOException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e)
        {
            fail("Error should not occur!");
        }
        // extracts the items from the feed list.
        RSSFeedItemExtractor instance = new RSSFeedItemExtractor();
        handler.initialiseDefaultAndSorted(items = instance.extractItems(feeds));
    }
    
    /**
     * Testing sort by title. Item is compared to the result given by the test loader,
     * however, it was necessary to edit the .UT file as the reported first item title
     * was not actually the correct item title.
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws RSSParseException
     * @throws ParseException
     */
    @Test
    public void testSortByTitle() throws MalformedURLException, FileNotFoundException, RSSParseException, ParseException
    {
        load();
        UnitTestLoader test = new UnitTestLoader();
        test.load("tech_rssfeed_one_filelist.ut");
        System.out.println("Testing sorting by title:");
        
        handler.sort("itemTitle", false);
        
        String expResult = test.getFirstItemTitleSortByTitle();
        String result = handler.getSorted().get(0).getItemTitle();
        
        System.out.println("Expecting: "+expResult +"   | Actual: "+result);
        System.out.println("-----------------------------------");
        assertEquals(expResult,result);
        
    }
    
    /**
     * Testing sorting by author. Expected result is hardcoded by looking at the
     * feed manually.
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws RSSParseException
     * @throws ParseException
     */
    @Test
    public void testSortByAuthor() throws MalformedURLException, FileNotFoundException, RSSParseException, ParseException
    {
        load();
        System.out.println("Testing sorting by item author:");
        
        handler.sort("author", false);
        
        String expResult = "Agam Shah";
        String result = handler.getSorted().get(0).getAuthor();
        
        System.out.println("Expecting: "+expResult +"   | Actual: "+result);
        System.out.println("-----------------------------------");
        assertEquals(expResult,result); 
    }
    
    /**
     * Testing sorting by date. Expected result is hardcoded by looking at the
     * feed manually.
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws RSSParseException
     * @throws ParseException
     */
    @Test
    public void testSortByDate() throws MalformedURLException, FileNotFoundException, RSSParseException, ParseException
    {
        load();
        System.out.println("Testing sorting by publication date (most recent first):");
        
        handler.sort("itemDate", false);
        
        String expResult = "Even the death of Windows XP failed to jolt the PC market in Q1";
        String result = handler.getSorted().get(0).getItemTitle();
        System.out.println(handler.getSorted().get(0).getItemDate());
        
        System.out.println("Expecting: "+expResult +"   | Actual: "+result);
        System.out.println("-----------------------------------");
        assertEquals(expResult,result); 
    }
    
    /**
     * Testing sorting by description length. Expected result is hardcoded by looking at the
     * feed manually.
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws RSSParseException
     * @throws ParseException
     */
    @Test
    public void testSortByDescLength() throws MalformedURLException, FileNotFoundException, RSSParseException, ParseException
    {
        load();
        System.out.println("Testing sorting by length of description:");
        
        handler.sort("itemDescription", false);
        
        int expResult = 62; // Hardcoded: counted all item descritions -_-
        int result = handler.getSorted().get(0).getItemDescription().length();
        
        System.out.println("Expecting: "+expResult +"   | Actual: "+result);
        System.out.println("-----------------------------------");
        assertEquals(expResult,result); 
    }
    
    /**
     * Testing sorting by reverse author. Expected result is hardcoded by looking at the
     * feed manually and comparing to the non reversed result.
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws RSSParseException
     * @throws ParseException
     */
    @Test
    public void testSortAuthorReverse() throws MalformedURLException, FileNotFoundException, RSSParseException, ParseException
    {
        load();
        System.out.println("Testing REVERSE sorting by author:");
        
        handler.sort("author", true);
        
        String expResult = "Zach Miners";
        String result = handler.getSorted().get(0).getAuthor();
        
        System.out.println("Expecting: "+expResult +"   | Actual: "+result);
        System.out.println("-----------------------------------");
        assertEquals(expResult,result); 
    }
    
    /**
     * Testing sorting by reverse date. Expected result is hardcoded by looking at the
     * feed manually and comparing to the non-reversed date.
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws RSSParseException
     * @throws ParseException
     */
    @Test
    public void testSortByDateReverse() throws MalformedURLException, FileNotFoundException, RSSParseException, ParseException
    {
        load();
        System.out.println("Testing sorting by REVERSE publication date (least recent first):");
        
        handler.sort("itemDate", true);
        
        String expResult = "Shareholders slam Facebook over 'incongruent' PAC contributions on gay rights, online piracy";
        String result = handler.getSorted().get(0).getItemTitle();
        System.out.println(handler.getSorted().get(0).getItemDate());
        
        System.out.println("Expecting: "+expResult +"   | Actual: "+result);
        System.out.println("-----------------------------------");
        assertEquals(expResult,result); 
    }

    
}
