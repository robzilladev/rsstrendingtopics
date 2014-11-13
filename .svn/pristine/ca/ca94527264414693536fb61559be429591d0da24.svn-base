package rsstrendingtopcis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Takes in a list of RSSFeedItems and scans the titles for 'trending' words.
 * Excludes commonly occurring words such as 'it' and 'the', however for testing
 * purposes a boolean 'switch' is also included to turn these functions off.
 * @author Rob Bloomfield
 */
public class TrendingIndex
{
    /**
     * List of all words identified by the indexer.
     */
    private List<String> words;
    /**
     * List of 'bad' words that should be excluded from the word count.
     */
    private List<String> badWords;
    /**
     * List of indexed word objects to be populated and returned.
     */
    private List<Word> index;
    /**
     * Map to store words and their associated Word objects. A map allows easy
     * comparison between the word being analysed and those already in the list.
     */
    private TreeMap<String,Word> map;
    /**
     * Scanner object to parse each RSSFeedItem title. This scanner must be closed
     * and re-instantiated for every new title.
     */
    private Scanner scan;
    /**
     * Signals whether the list has been indexed or not.
     */
    private boolean indexed;
    /**
     * Signals whether 'cleaning' of words is ON or OFF.
     */
    private boolean cleaning;

    /**
     * Instantiates all lists and maps. Adds commonly occurring words to badwords
     * that should not be included in the word analysis.
     */
    public TrendingIndex()
    {
        words = new ArrayList<>();
        index = new ArrayList<>();
        map = new TreeMap<>();
        indexed = false;
        cleaning = true;
        
        // List of commonly appearing words that are to be removed from the
        // index list.
        badWords = new ArrayList<>();
        badWords.add("to");badWords.add("from");badWords.add("the");
        badWords.add("for");badWords.add("too");badWords.add("in");
        badWords.add("and");badWords.add("of");badWords.add("an");
        badWords.add("more");badWords.add("how");badWords.add("with");
        badWords.add("can");badWords.add("by");badWords.add("it");
        badWords.add("on");badWords.add("at");badWords.add("is");
        badWords.add("its");badWords.add("as");badWords.add("this");
        badWords.add("what");badWords.add("be");badWords.add("that");
        badWords.add("no");badWords.add("up");badWords.add("yes");
        badWords.add("all");badWords.add("will");badWords.add("your");
        badWords.add("you");badWords.add("use");badWords.add("out");
        badWords.add("but");badWords.add("i");badWords.add("still");
        badWords.add("says");badWords.add("claim");badWords.add("into");
        badWords.add("are");badWords.add("could");badWords.add("will");
        badWords.add("makes");badWords.add("a");badWords.add("through");
        badWords.add("isn't");badWords.add("it's");
    }
    
    /**
     * Identifies 'trending words' across a list of RSSFeedItem titles.
     * Checks every word in every title, if the word is not already in the map, 
     * then a new word object is created and added. If it is in the map, then the
     * count of that word within the word object is incremented.
     * 
     * @param list RSSFeedItems to be analysed.
     */
    public void indexWords(List<RSSFeedItem> list)
    {
        String currentWord;
        if (indexed == true)
        {
            map.clear();
            index.clear();
            words.clear();
            indexed = false;
        }
        
        for (RSSFeedItem i: list)
        {
            scan = new Scanner(i.getItemTitle());
            
            // Adds ALL words to a list.
            while (scan.hasNext())
            {
                currentWord = scan.next().toLowerCase();
                // Checks to words like it's and apple's and removes the 's.
                if (cleaning == true)
                {
                    if (hasApostrophe(currentWord))
                        currentWord = removeApostrophe(currentWord);
                    if (hasTailSymbol(currentWord))
                        currentWord = removeTailSymbol(currentWord);
                    if (hasHeadSymbol(currentWord))
                        currentWord = removeHeadSymbol(currentWord);
                }
                
                words.add(currentWord);
                i.addWord(currentWord.toLowerCase());
            }
            scan.close();
        }
        
        // Checks each word in list and adds to map/updates count as required.
        for (String s:words)
        {
            if (!map.containsKey(s))
            {
                map.put(s, new Word(s));
            }
            else
            {
                map.get(s).incrementCount();
            }
        }
        
        // Copy all elements from map into index list.
        // Does not copy any words with count less than 5, words that are
        // contained in BADWORDS and strings which are numbers.
        for (String s: map.keySet())
        {
            if (cleaning == true)
            {
                if (map.get(s).getCount() >= 5 && !badWords.contains(s) && !isNumeric(s))
                {
                    index.add(map.get(s));
                }
            }
            else
                index.add(map.get(s));
                
        }
        
        // Sort by count (compareTo in word class).
        Collections.sort(index);
        indexed = true;;
    } 
    
    /**
     * Returns true if string is a number. 
     * @param str String to be checked.
     * @return True if string is numeric.
     */
    public boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");
    } 
    
    /**
     * Returns true if string contains an apostrophe at index: length - 2.
     * Eg: Apple's will return true.
     * @param s String to be checked.
     * @return True if string has an apostrophe at index: length -2, false if not.
     */
    public boolean hasApostrophe(String s)
    {
        if (s.length() > 2 && s.charAt(s.length()-2) == '\'')
            return true;
        else
            return false;
    }

    /**
     * Removes the apostrophe and its trailing character. Method should only be
     * called if hasApostrophe returns true.
     * @param s String to be checked.
     * @see TrendingIndex#hasApostrophe(java.lang.String) 
     * @return 'Cleaned' word.
     */
    public String removeApostrophe(String s)
    {
        String cleaned = s.substring(0,s.length()-2);
        return cleaned;
    }
    /**
     * Returns the list of indexed words.
     * @return List of indexed words.
     */
    public List<Word> getIndexed()
    {
        return index;
    }
    
    /**
     * Returns the total word count. Returns .size of the words list.
     * @return Total word count (size of words list).
     */
    public int getWordCount()
    {
        return words.size();
    }
    /**
     * Clears all lists within object. Clears map, index, words and sets indexed
     * to false.
     */
    public void clearTrend()
    {
        map.clear();
        index.clear();
        words.clear();
        indexed = false;
    }
    /**
     * Triggers cleaning boolean on or off. Included for testing purposes, when
     * cleaning returns true, words will be stripped of apostrophes and 'bad'
     * words will not indexed. When cleaning returns false, no words will be modified
     * from their initial state.
     * @param b Desired state for 'cleaning'
     */
    public void setClean(boolean b)
    {
        cleaning = b;
    }
    
    /**
     * Returns the word count of ALL words found in ALL titles.
     * @return Total word count.
     */
    public int getAllWordsCount()
    {
        return words.size();
    }

    /**
     * Returns the map of the word strings to their word objects.
     * @return Map of word map.
     */
    public TreeMap<String, Word> getMap()
    {
        return map;
    }
    
    /**
     * Checks to see if the last character in a string is a 'symbol'. 'Symbols'
     * are hard-coded, and only include simple common occurring, such as punctuation marks.
     * @param s String to be checked.
     * @return True if the character is a symbol, false if not.
     */
    public boolean hasTailSymbol(String s)
    {
        if (s.charAt(s.length()-1) == ':' || s.charAt(s.length()-1) == ',' ||
                s.charAt(s.length()-1) == '.' || s.charAt(s.length()-1) == '\'' ||
                s.charAt(s.length()-1) == ';' || s.charAt(s.length()-1) == '!' ||
                s.charAt(s.length()-1) == '?' || s.charAt(s.length()-1) == '\"' ||
                s.charAt(s.length()-1) == ')' || s.charAt(s.length()-1) == '$')
        {
            return true;
        }
        else
            return false; 
    }
    
    /**
     * Checks to see if the first character in a string is a 'symbol'. 'Symbols'
     * are hard-coded, and only include simple common occurring, such as dollar signs.
     * @param s String to be checked.
     * @return True if the character is a symbol, false if not.
     */
    public boolean hasHeadSymbol(String s)
    {
        if (s.charAt(0) == ':' || s.charAt(0) == ',' ||
                s.charAt(0) == '.' || s.charAt(0) == '\'' ||
                s.charAt(0) == ';' || s.charAt(0) == '!' ||
                s.charAt(0) == '?' || s.charAt(0) == '\"' ||
                s.charAt(0) == ')' || s.charAt(0) == '(' || s.charAt(0) == '$')
        {
            return true;
        }
        else
            return false; 
    }
    
    /**
     * Removes the tail character IF it is a symbol. Continues removing the last
     * character until it is NOT a symbol. Method should be used in conjunction with hasTailSymbol.
     * @param s String to be manipulated.
     * @return String without tail symbol.
     * @see TrendingIndex#hasTailSymbol(java.lang.String) 
     */
    public String removeTailSymbol(String s)
    {
        String temp = s;
        while (hasTailSymbol(temp))
        {
            temp = temp.substring(0,temp.length()-1);
        }
        return temp;
    }
    
   /**
     * Removes the first character IF it is a symbol. Continues removing the first
     * character until it is NOT a symbol. Method should be used in conjunction with hasHeadSymbol.
     * @param s String to be manipulated.
     * @return String without tail symbol.
     * @see TrendingIndex#hasHeadSymbol(java.lang.String) 
     */
    public String removeHeadSymbol(String s)
    {
        String temp = s;
        while (hasHeadSymbol(temp))
        {
            temp = temp.substring(1,temp.length());
        }
        return temp;
    } 
}
