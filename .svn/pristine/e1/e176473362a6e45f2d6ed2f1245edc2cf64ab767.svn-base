package rsstrendingtopcis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
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
import org.jdom2.input.SAXBuilder;
import proxypass.ProxyPass;

/**
 * Retrieves RSS feeds by file name OR URL. File name or URL are passed
 * to the appropriate methods which return a Document object representing
 * the feed information. The document can then be parsed for elements.
 * @author lewi0146 bloo0027
 */
public class RSSFeedRetriever {

    /**
     * A static global variable that will be incremented when parsing errors occur.
     * This variable may be accessed from any class in the package for user information purposes.
     */
    public static int errorCount = 0;

    /**
     * Retrieves a list of feeds from the file name specified as the parameter.
     * The file name is passed to the getFeeds method as a return statement.
     * @param fileName
     * @return The list of feeds.
     * @throws JDOMException
     * @throws IOException
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws RSSParseException
     */
    public static List<Document> getFeeds(String fileName) throws JDOMException, IOException, MalformedURLException, FileNotFoundException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, NoSuchPaddingException, IllegalBlockSizeException, IllegalBlockSizeException, BadPaddingException, RSSParseException 
    {
        return getFeeds(fileName, false);
    }

    /**
     * Gets feeds from the file name specified.
     * Iterates through the list of sources in the file, and attempts to parse
     * for RSS feeds. If an exception is thrown during parsing, it is caught and
     * rethrown as a new exception for better handling. In this case, the source
     * that throws the exception is skipped and processing continues at the next
     * source.
     * It is possible to retrieve from a list of FILES, or a list of URLS.
     * @param fileName Source file.
     * @param useProxy For access within Flinders private network.
     * @return List of feeds.
     * @throws JDOMException
     * @throws IOException
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws RSSParseException
     */
    public static List<Document> getFeeds(String fileName, boolean useProxy) 
            throws JDOMException, IOException, MalformedURLException, FileNotFoundException, 
            InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, 
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, RSSParseException 
        {
        List<Document> feeds = new ArrayList<>();

        File f = new File(fileName);
        Scanner feedList = new Scanner(f);

        String feedType = feedList.nextLine();
        switch (feedType) {
            case "file":
                while (feedList.hasNext()) {      
                    try
                    {
                        try
                        {
                            feeds.add(getFeedFromFile(f.getParentFile().getAbsolutePath() 
                                + File.separator + feedList.nextLine()));
                        }
                        catch (JDOMException | MalformedURLException | FileNotFoundException e)
                        {
                            throw new RSSParseException(e.getMessage());
                        }
                    }
                    catch (RSSParseException e)
                    {
                        errorCount++;
                    }
                }
                break;
            case "url":
                while (feedList.hasNext()) {
                    try
                    {
                        try
                        {
                            feeds.add(getFeedFromURL(feedList.nextLine(),useProxy));
                        }
                        catch (JDOMException | MalformedURLException | FileNotFoundException | UnknownHostException e)
                        {
                            throw new RSSParseException(e.getMessage());
                        }
                        catch (ConnectException e)
                        {
                            System.out.println(e.getStackTrace());
                        }
                    }
                    catch (RSSParseException e)
                    {
                        errorCount++;
                    }
                }
                break;
            default:
                feeds = null;
                break;
        }
        return feeds;
        

    }

    /**
     * Accepts a file name and returns a Document object.
     * @param fileName Source file.
     * @return Feed Document.
     * @throws JDOMException
     * @throws JDOMParseException
     * @throws IOException
     */
    public static Document getFeedFromFile(String fileName) throws JDOMException, JDOMParseException, IOException 
    {
       
        SAXBuilder sax = new SAXBuilder();
        File file = new File(fileName);
        
        Document doc = sax.build(file);

        return doc;

    }

    /**
     * Accepts a URL and boolean and returns a Document object.
     * @param urlName Source URL
     * @param useProxy If true, proxy credentials must be used to connect through
     * the Flinders network.
     * @return Feed Document.
     * @throws MalformedURLException
     * @throws JDOMException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static Document getFeedFromURL(String urlName, boolean useProxy) throws MalformedURLException, JDOMException, IOException, FileNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException 
    {
        if (useProxy)
        {
            ProxyPass.setup("credentials");
        }
        
        SAXBuilder sax = new SAXBuilder();
        URL url = new URL(urlName);
        Document doc = sax.build(url);

        return doc;
    }
}
