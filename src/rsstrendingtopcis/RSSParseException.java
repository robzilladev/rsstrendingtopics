package rsstrendingtopcis;
/**
 * Thrown when a parsing error occurs. Exception will be thrown when the
 * RSSFeedRetriever encounters an anomaly during parsing. The original exception
 * shall be thrown, followed by this exception.
 * @author Rob
 */
public class RSSParseException extends Exception
{
    /**
     * Prints the message generated by the original thrown exception. Also prints
     * information for the user about the action taken.
     * @param string The message generated by the original thrown exception.
     */
    public RSSParseException(String string)
    {
        System.out.println(string);
        System.out.println("This feed has been skipped.");
    }
    
}
