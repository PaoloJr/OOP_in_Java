package spelling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class NearbyWords implements SpellingSuggest {
	// THRESHOLD to determine how many words to look through when looking
	// for spelling suggestions (stops prohibitively long searching)
	// For use in the Optional Optimization in Part 2.
	private static final int THRESHOLD = 500; 

	Dictionary dict;

	public NearbyWords (Dictionary dict) {
		this.dict = dict;
	}

	/** Return the list of Strings that are one modification away
	 * from the input string.  
	 * @param s The original String
	 * @param wordsOnly controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
	public List<String> distanceOne(String s, boolean wordsOnly )  {
		   List<String> retList = new ArrayList<String>();
		   substitution(s, retList, wordsOnly);
		   deletions(s, retList, wordsOnly);
		   insertions(s, retList, wordsOnly);
		   return retList;
	}

	
	/** Add to the currentList Strings that are one character mutation away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void substitution(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
//		System.out.println("substitions");
		for(int index = 0; index < s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the 
				// letters in the String
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char)charCode);
				String newWordSub = sb.toString();
//				System.out.println(newWordSub);
				
				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(newWordSub) && (!wordsOnly || dict.isWord(newWordSub)) && !s.equals(newWordSub)) {
					currentList.add(newWordSub);
				}
			}
		}
//		System.out.println(currentList);
	}
	
	/** Add to the currentList Strings that are one character insertion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void insertions(String s, List<String> currentList, boolean wordsOnly ) {
//		System.out.println("insertions");
		for (int i = 0; i <= s.length(); i++) {
			for (int charCode = (int) 'a'; charCode <= (int) 'z'; charCode++) {
				StringBuffer sb = new StringBuffer(s);
				sb.insert(i, (char)charCode);
				String newWordInsert = sb.toString();
//				System.out.println(newWordInsert);
				
			if (!currentList.contains(newWordInsert) && (!wordsOnly || dict.isWord(newWordInsert)) && !s.equals(newWordInsert)) {
				currentList.add(newWordInsert);
				}
			}
		}
//		System.out.println(currentList);
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly ) {
//		System.out.println("deletions");
		for (int i = 0; i < s.length(); i++) {
			for (int charCode = (int) 'a'; charCode <= (int) 'z'; charCode++) {
				StringBuffer sb = new StringBuffer(s);
				sb.deleteCharAt(i);
				String newWordDel = sb.toString();
//				System.out.println(newWordDel);
				
			if (!currentList.contains(newWordDel) && (!wordsOnly || dict.isWord(newWordDel)) && !s.equals(newWordDel)) {
				currentList.add(newWordDel);
			}
			}
		}
//		System.out.println(currentList);
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param word The misspelled word
	 * @param numSuggestions is the maximum number of suggestions to return 
	 * @return the list of spelling suggestions
	 */
	@Override
	public List<String> suggestions(String word, int numSuggestions) {

		// initial variables
		List<String> queue = new LinkedList<String>();     // String to explore
		HashSet<String> visited = new HashSet<String>();   // to avoid exploring the same  
														   // string multiple times
		List<String> retList = new LinkedList<String>();   // words to return
		
		if (word == "") {
			return Collections.emptyList();
		}
		
		// insert first node
		queue.add(word);
		visited.add(word);
		
		// to use the THRESHOLD value so that we stop the suggestions search
//		int wordsProcessed = 0;
		
		while (!queue.isEmpty() && numSuggestions > 0) {
			String currWord = queue.remove(0);
			List<String> listOfNeighbors = distanceOne(currWord, true);
//			System.out.println(listOfNeighbors);
			
			for (String n : listOfNeighbors) {
				if (!visited.contains(n) && dict.isWord(n)) {
					visited.add(n);
					queue.add(n);
					retList.add(n);
					numSuggestions--;
				}
				if(numSuggestions == 0) {
					break;
				}
//				if(wordsProcessed == THRESHOLD) {
//					break;
//				}
			}
		}					
		
		return retList;

	}	

   public static void main(String[] args) {
	   // basic testing code to get started
	   String word = "or";
	   // Pass NearbyWords any Dictionary implementation you prefer
	   Dictionary d = new DictionaryHashSet();
	   DictionaryLoader.loadDictionary(d, "data/dict.txt");
	   // create object
	   NearbyWords nearbyWords = new NearbyWords(d);
	   // return list of nearby words after running three methods (substitution, insertion and deletion); 
	   // they each check against the dictionary and existing list (for duplication);
	   List<String> listOfNearbyWords = nearbyWords.distanceOne(word, true);
	   
	   System.out.println("One away word Strings for for \"" + word + "\" are:");
	   System.out.println(listOfNearbyWords + "\n");

	   word = "or";
	   List<String> suggest = nearbyWords.suggestions(word, 10);
	   System.out.println("Spelling Suggestions for \"" + word + "\" are:");
	   System.out.println(suggest);
	   
	   word = "kangaro";
	   suggest = nearbyWords.suggestions(word, 10); 
	   System.out.println("Spelling Suggestions for \"" + word + "\" are:");
	   System.out.println(suggest); 
   }

}
