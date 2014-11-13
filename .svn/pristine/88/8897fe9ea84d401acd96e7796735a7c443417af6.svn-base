package rsstrendingtopcis;

/**
 * Represents a word identified during the word count (trending topics). Each word
 * object stores a constant string of the word it represents, and a count of how
 * many times that word is identified during the word count (trending topics).
 * @author Rob Bloomfield
 */
public class Word implements Comparable<Word>
{
    /**
     * String representation of the word that is the word object.
     */
    private final String word;
    /**
     * Number of times the word is matched during trending analysis.
     */
    private int count;

    /**
     * Initialises the word object with the word that it will represent.
     * @param word The word that this word object will represent.
     */
    public Word(String word)
    {
        this.word = word;
        count = 1;
    }

    /**
     * Gets the word that this object represents.
     * @return The word that this word object represents.
     */
    public String getWord()
    {
        return word;
    }

    /**
     * Gets the number of times the word was identified.
     * @return Count associated with word.
     */
    public int getCount()
    {
        return count;
    }
    
    /**
     * Increments the count by 1.
     */
    public void incrementCount()
    {
        count++;
    }

    /**
     * Sets all Word objects to be sorted by the count associated with them. For
     * example, Hello [45] will be sorted higher than Goodbye [5].
     * @param t The word object to be compared to.
     * @return -, 0, + (int).
     */
    @Override
    public int compareTo(Word t)
    {
        return t.getCount() - getCount();
    }
    
    /**
     * Returns the string representation of the Word object. Further, toString
     * returns the word and the count associated with it.
     * @return The word and its associated count.
     */
    @Override
    public String toString()
    {
        return word + "[" + count + "]";
    }
    
    
}
