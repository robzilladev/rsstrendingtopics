package rsstrendingtopcis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Rob
 */
public class RSSTrendingTopcis {

    /**
     * Static global variable to represent the number of errors encountered during
     * parsing of initial feeds.
     */
    static int errorCount = 0;

    /**
     * Main method. Never called as RSSTrendingTopicsFrame is set
     * to be default entry point.
     * @param args command line arguments.
     * @throws IOException
     * @throws JDOMException
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws RSSParseException
     */
    public static void main(String[] args) throws IOException, JDOMException, MalformedURLException, FileNotFoundException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, RSSParseException {
        
        List<Document> feeds = RSSFeedRetriever.getFeeds("feeds" + File.separator + "tech_rssfeed_filelist.txt");
        
        if (feeds != null)
        {
            System.out.println("numbers of feeds: " + feeds.size());
            for (Document feed : feeds)
            {
                System.out.println(feed);
            }
        }
        else
        {
            System.out.println("feed retriever returned null! probably couldn't find the file.");
        }
        

    }
}