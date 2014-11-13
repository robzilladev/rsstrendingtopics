package rsstrendingtopcis;

import assignmentunittestloader.UnitTestLoader;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.JDOMParseException;
import org.junit.Test;
import static org.junit.Assert.*;
import static rsstrendingtopcis.RSSFeedRetriever.getFeedFromFile;

/**
 *
 * @author Rob
 */
public class RSSFeedRetrieverTest
{
    UnitTestLoader test;
    
    /**
     *
     */
    public RSSFeedRetrieverTest()
    {
    }
    
    /**
     * Tests the retrieval and extraction of 1 RSSFeed. Should return 30 items.
     * @throws Exception Generic exception.
     */
        @Test
    public void testExtractItems30() throws Exception
    {
        System.out.println("Testing RSSFeedItemExtractor: 1 RSS Feed...");
        String testFilePrefix = "tech_rssfeed_one_filelist";   
        
        // load the expected test output
        UnitTestLoader test = new UnitTestLoader();
        test.load(testFilePrefix + ".ut");
        
        // load the test data
        List<Document> feeds = null;
        try {
            feeds = RSSFeedRetriever.getFeeds("feeds" + File.separator + testFilePrefix+ ".txt");
        }
        catch (JDOMException | IOException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e)
        {
            fail("Error should not occur!");
        }

        // test
        RSSFeedItemExtractor instance = new RSSFeedItemExtractor();
        feeds = instance.extractItems(feeds);
        int expResult = test.getNumberOfItems();
        int result = feeds.size();
        assertEquals(expResult, result);
        System.out.println("Expecting: "+expResult +"   | Actual: "+result);
        System.out.println("-----------------------------------");
        
    }
    /**
     * Tests the retrieval and extraction of ALL feeds in the list. Should return
     * a list of 492 RSSFeedItem objects.
     * @throws Exception General exception
     */
    @Test
    public void testExtractItems492() throws Exception
    {
        System.out.println("Testing RSSFeedItemExtractor: All RSS Feeds...");
        String testFilePrefix = "tech_rssfeed_filelist";   
        
        // load the expected test output
        UnitTestLoader test = new UnitTestLoader();
        test.load(testFilePrefix + ".ut");
        
        // load the test data
        List<Document> feeds = null;
        try {
            feeds = RSSFeedRetriever.getFeeds("feeds" + File.separator + testFilePrefix+ ".txt");
        }
        catch (JDOMException | IOException | InvalidKeyException | NoSuchAlgorithmException 
                | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException 
                | BadPaddingException e)
        {
            fail("Error should not occur!");
        }

        // test
        RSSFeedItemExtractor instance = new RSSFeedItemExtractor();
        feeds = instance.extractItems(feeds);
        int expResult = test.getNumberOfItems();
        int result = feeds.size();
        assertEquals(expResult, result);
        System.out.println("Expecting: "+expResult +"   | Actual: "+result);
        System.out.println("-----------------------------------");
        
    }
    
    /**
     * Testing 1 XML file that DOES contain malformed XML tags. Should throw
     * a JDOMParseException.
     * @throws Exception General exception
     */
        @Test(expected=JDOMParseException.class)
    public void testExtractItemsMalformed() throws Exception
    {
        System.out.println("Testing 1 XML file with malformed XML tags..");
        
        // load the test data..
        List<Document> feeds = new ArrayList<>();
        
        // should throw exception..
        feeds.add(getFeedFromFile("tech_feed_7_malformed.xml"));
        
        // should never be executed..
        System.out.println("Expecting: EXCEPTION");
        System.out.println("-----------------------------------");
        
    }
    //--------------------------------------------------------------------------
    // Test the file list with 4 files, 3 good and 1 malformed.
    // Method should load 2, fail 1, then load the 4th.
    //--------------------------------------------------------------------------

    /**
     * Testing a list of files, one of which is malformed. Should retrieve and extract
     * 2 files, fail the third, and then succeed on the 4th. Should return a list of
     * 3 feeds.
     * @throws Exception
     */
        @Test
    public void testExtractItemsIncludeMalformed() throws Exception
    {
        System.out.println("Testing RSSFeedItemExtractor: 4 feeds (1 malformed)...");  
        
        // load the test data
        List<Document> feeds = new ArrayList<Document>();
        File f = new File("list_one_malformed.txt");
        Scanner feedList = new Scanner(f);

        String feedType = feedList.nextLine();
        switch (feedType) {
            case "file":
                while (feedList.hasNext()) {      
                    try
                    {
                        feeds.add(getFeedFromFile(feedList.nextLine()));
                    }
                    catch (JDOMParseException e)
                    {
                        System.out.println(e);
                        System.out.println("This feed has been skipped.");
                    }
                }
        // test
        int expResult = 3;
        int result = feeds.size();
        assertEquals(expResult, result);
        System.out.println("Expecting: "+expResult +"   | Actual: "+result);
        System.out.println("-----------------------------------");
        }
    }
}
    
        
