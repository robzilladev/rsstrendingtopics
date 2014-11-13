/*
 * J Unit tests for methods contained in the TrendingIndex class.
 * Trivial methods have been left out.
 */

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
import java.util.ArrayList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Rob
 */
public class TrendingIndexTest
{
    private List<RSSFeedItem> items;
    String testFilePrefix = "";
    
    /**
     *
     */
    public TrendingIndexTest()
    {
    }
    
    /**
     * Called before every test is run. Method sets up all the required fields
     * that are needed to run the tests. In this case, setUp takes a list
     * containing one xml item and extracts the data to a new RSSFeedItem object
     * for trending word testing.
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws RSSParseException
     * @throws ParseException 
     */
    @Before
    public void setUp() throws MalformedURLException, FileNotFoundException, RSSParseException, ParseException
    {
        
    }
    
    /**
     * Sets up the list of RSSFeedItem's for trending analysis. If 1 is passed to the
     * method, then a single RSSFeedItem is created, if 2 is passed, then multiple
     * items are created.
     * @param i Integer to determine which list file is read by the extractor.
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws RSSParseException
     * @throws ParseException 
     */
    public void loadItems(int i) throws MalformedURLException, FileNotFoundException, RSSParseException, ParseException
    {
        RSSFeedItemExtractor extractor = new RSSFeedItemExtractor();
        List<Document> feeds = null;
        
        if (i == 1)
        {
            // File name to be passed to the retriever and unit loader.
            testFilePrefix = "tech_rssfeed_one_file_item";
            try 
            {
                feeds = RSSFeedRetriever.getFeeds("feeds" + File.separator + testFilePrefix+ ".txt");
            }
            catch (JDOMException | IOException | InvalidKeyException | NoSuchAlgorithmException 
                    | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException 
                    | BadPaddingException e)
            {
                fail("Should not occur, exception: "+ e.getMessage());
            }
        }
        else if (i == 2)
        {
            testFilePrefix = "tech_rssfeed_one_filelist";
            try 
            {
                feeds = RSSFeedRetriever.getFeeds("feeds" + File.separator + testFilePrefix+ ".txt");
            }
            catch (JDOMException | IOException | InvalidKeyException | NoSuchAlgorithmException 
                    | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException 
                    | BadPaddingException e)
            {
                fail("Should not occur, exception: "+ e.getMessage());
            }
            
        }
        else if (i == 3)
        {
            testFilePrefix = "tech_rssfeed_one_file_item_clean";
            try 
            {
                feeds = RSSFeedRetriever.getFeeds("feeds" + File.separator + testFilePrefix+ ".txt");
            }
            catch (JDOMException | IOException | InvalidKeyException | NoSuchAlgorithmException 
                    | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException 
                    | BadPaddingException e)
            {
                fail("Should not occur, exception: "+ e.getMessage());
            }
            
        }
        items = new ArrayList<>();
        items = extractor.extractItems(feeds);
    }

    /**
     * Testing word count of one XML item. ExpResult is hard-coded in as 14 so
     * this test SHOULD also return a result of 14.
     * @throws java.net.MalformedURLException
     * @throws java.io.FileNotFoundException
     * @throws java.text.ParseException
     * @throws rsstrendingtopcis.RSSParseException
     */
    @Test
    public void testIndexWords() throws MalformedURLException, FileNotFoundException, ParseException, RSSParseException
    {
        loadItems(1);
        System.out.println("Testing simple title extraction/word count: \nItems in list: " + items.size());
        TrendingIndex instance = new TrendingIndex();
        instance.setClean(false);
        instance.indexWords(items);
        
        int expResult = 14; // Hardcoded (manually counted)
        System.out.println(items.get(0).getItemTitle());
        int result = instance.getAllWordsCount();
        System.out.println("Expected : [" + expResult + "] Actual: [" + result + "]");
        
        assertEquals(expResult,result);
        System.out.println("-----------------------------");
    }
    /**
     * Testing indexing of words in one XML item. Looking at word 'the'. The word
     * should the same count as is returned by the test loader.
     * @throws java.net.MalformedURLException
     * @throws java.io.FileNotFoundException
     * @throws java.text.ParseException
     * @throws rsstrendingtopcis.RSSParseException
     */
    @Test
    public void testWordCounts() throws MalformedURLException, FileNotFoundException, ParseException, RSSParseException
    {
        loadItems(1);
        testFilePrefix = "tech_feed_7_item";
        // load the expected test output
        UnitTestLoader test = new UnitTestLoader();
        test.load(testFilePrefix + ".ut");
        
        System.out.println("Testing word count (checking 'the'):");
        
        TrendingIndex instance = new TrendingIndex();
        instance.setClean(false); // Removes any extra processing done to words.
        instance.indexWords(items);
        
        int expResult = test.getWordCount("the");
        int result = instance.getMap().get("the").getCount();
        System.out.println("Expected : [" + expResult + "] Actual: [" + result + "]");
        assertEquals(expResult,result);
        System.out.println("-----------------------------");
    }
    
    /**
     * Testing indexing of words when multiple RSSFeedItem's are created.
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws RSSParseException 
     */
    @Test
    public void testMultipleItemsWordCount() throws MalformedURLException, FileNotFoundException, ParseException, RSSParseException
    {
        System.out.println("Testing multiple RSS items word indexing (checking 'to'):");
        
        loadItems(2);
        UnitTestLoader test = new UnitTestLoader();
        test.load("tech_rssfeed_one_filelist.ut");
        
        TrendingIndex instance = new TrendingIndex();
        instance.setClean(false); // Removes any extra processing done to words.
        instance.indexWords(items);
        
        int expResult = test.getWordCount("to");
        int result = instance.getMap().get("to").getCount();
        
        System.out.println("Expected : [" + expResult + "] Actual: [" + result + "]");
        System.out.println("-----------------------------");
        
        assertEquals(expResult,result);
    }
    
    /**
     * Testing the 'cleaning' of words. Apple's should be counted as apple.
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws RSSParseException
     * @throws ParseException
     */
    @Test
    public void testWordCleaning() throws MalformedURLException, FileNotFoundException, RSSParseException, ParseException
    {
        System.out.println("Testing the 'cleaning' of words, for example 'Apple's' should be counted for 'apple'");
        
        loadItems(3);
        
        TrendingIndex instance = new TrendingIndex();
        instance.indexWords(items);
        
        int expResult = 10; // Hardcoded 10, xml file was defined by me for this test.
        int result = instance.getMap().get("apple").getCount();
        
        System.out.println("Expected : [" + expResult + "] Actual: [" + result + "]");
        System.out.println("-----------------------------");
        
        assertEquals(expResult,result);
    }
   
    /**
     * Tests the identification of a symbol at the end of a string.
     */
    @Test
    public void testTailSymbol()
    {
        System.out.println("Testing tail symbol checker: Hello!");
        TrendingIndex instance = new TrendingIndex();
        
        boolean expResult = true;
        boolean result = instance.hasTailSymbol("Hello!");
        
        System.out.println("Expected : [" + expResult + "] Actual: [" + result + "]");
        System.out.println("-----------------------------");
        assertEquals(expResult,result);
        
    }
    
    /**
     * Tests the identification of NO symbol at the end of a string.
     */
    @Test
    public void testTailSymbolFalse()
    {
        System.out.println("Testing tail symbol checker: Hello");
        TrendingIndex instance = new TrendingIndex();
        
        boolean expResult = false;
        boolean result = instance.hasTailSymbol("Hello");
        
        System.out.println("Expected : [" + expResult + "] Actual: [" + result + "]");
        System.out.println("-----------------------------");
        assertEquals(expResult,result);
        
    }
    
    /**
     * Tests the identification of a symbol at the start of a string.
     */
    @Test
    public void testHeadSymbol()
    {
        System.out.println("Testing head symbol checker: $CashMoney");
        TrendingIndex instance = new TrendingIndex();
        
        boolean expResult = true;
        boolean result = instance.hasHeadSymbol("$CashMoney");
        
        System.out.println("Expected : [" + expResult + "] Actual: [" + result + "]");
        System.out.println("-----------------------------");
        assertEquals(expResult,result);
        
    }

    /**
     * Tests the identification of NO symbol at the start of a string.
     */
    @Test
    public void testHeadSymbolFalse()
    {
        System.out.println("Testing tail symbol checker: CashMoney");
        TrendingIndex instance = new TrendingIndex();
        
        boolean expResult = false;
        boolean result = instance.hasTailSymbol("CashMoney");
        
        System.out.println("Expected : [" + expResult + "] Actual: [" + result + "]");
        System.out.println("-----------------------------");
        
        assertEquals(expResult,result);
    }
    
    /**
     * Tests the removal of a symbol at the start of a string.
     */
    @Test
    public void removeHeadSymbol()
    {
        System.out.println("Testing head symbol remover: $$$richrichrich");
        TrendingIndex instance = new TrendingIndex();
        
        String expResult = "richrichrich";
        String result = instance.removeHeadSymbol("$$$richrichrich");
        
        System.out.println("Expected : [" + expResult + "] Actual: [" + result + "]");
        System.out.println("-----------------------------");
        
        assertEquals(expResult,result);
    }
     
    /**
     * Tests the removal of a symbol at the end of a string.
     */
    @Test
    public void removeTailSymbol()
    {
        System.out.println("Testing tail symbol remover: YO!!!");
        TrendingIndex instance = new TrendingIndex();
        
        String expResult = "YO";
        String result = instance.removeTailSymbol("YO!!!");
        
        System.out.println("Expected : [" + expResult + "] Actual: [" + result + "]");
        System.out.println("-----------------------------");
        
        assertEquals(expResult,result);
    }

    
}
