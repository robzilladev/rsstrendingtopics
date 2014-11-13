package rsstrendingtopcis;

/**
 * Thrown when an element of an RSSFeedItem cannot be displayed by the JFrame.
 * This will occur when an error is not extracted during parsing, likely causing
 * the value to be null.
 * @author Rob Bloomfield
 */
public class RSSFeedException extends Exception
{
    private String type;
    private int itemIndex;

    /**
     * Sets 'type' of exception, and the index at which it was thrown. Type refers
     * to the element which threw the exception, such as author or date. Index
     * refers to the list index where the exception occurred.
     * @param string Type of exception to be thrown.
     * @param index Index of occurrence.
     */
    public RSSFeedException(String string, int index)
    {
        super(string);
        type = string;
        itemIndex = index;
    }
    
    /**
     * Returns string representation of the exception. Reports the type and index
     * of the particular exception.
     * @return String representation of the exception.
     */
    @Override
    public String toString()
    {
        return "There was a problem retrieving the [" + type + "] from RSS item ["
                + itemIndex + "].\nA default value has been set.\n"
                + "-----------------------------------------------------------";
    }
}
