package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary {

	private LinkedList<String> dict;
	private int size;
	
    // TODO: Add a constructor
	public DictionaryLL() {
		dict = new LinkedList<String>();
		size = 0;		
	}


    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	String lower = word.toLowerCase();
    	if (!dict.contains(lower)) {
    		dict.add(lower);
    		size++;
    		return true;
    	}
        return false;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        // TODO: Implement this method
        return this.size;
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //TODO: Implement this method
    	String lower = s.toLowerCase();
    	return dict.contains(lower);
    }

    
}
