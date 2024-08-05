package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()	{
		root = new TrieNode();
		size = 0;
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)	{
	    //TODO: Implement this method.
		String lower = word.toLowerCase();
		TrieNode temp = root;
		
		for (int i = 0; i < lower.length(); i++) {
			char currChar = lower.charAt(i);
			
			// getChild returns the HashMap values (TrieNode)
			if(temp.getChild(currChar) == null) {
				temp.insert(currChar);
			}
			// reassign temp to the next node from HashMap values
			temp = temp.getChild(currChar);
		}
		
		if (temp.endsWord()) {
			return false;
		} else {
			temp.setEndsWord(true);
			size++;
			return true;
		}
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size() {
	    //TODO: Implement this method
	    return this.size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) {
	    // TODO: Implement this method
		
		if (s.isEmpty()) {
			return false;
		}
		
		String lower = s.toLowerCase();
		TrieNode temp = root;
		for (int i = 0; i < lower.length(); i++) {
			char currChar = lower.charAt(i);
			
			if (temp.getChild(currChar) == null) {
				return false;
			} else {
				temp = temp.getChild(currChar);
			}
		}
		return temp.endsWord();
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 String lower = prefix.toLowerCase();
    	 TrieNode temp = root;
    	 Queue<TrieNode> q = new LinkedList<TrieNode>();
    	 List<String> list = new ArrayList<String>();
    	 
    	 // if stem not found, return empty list
    	 for (int i = 0; i < prefix.length(); i++) {
    		 char currChar = lower.charAt(i);
    		 
    		 if (temp.getChild(currChar) == null) {
    			 return Collections.emptyList();
    		 } else {
    			 temp = temp.getChild(currChar);
    		 }
    	 }
    	 // when a TrieNode is found (re-assigned above), add it to the queue
    	 q.add(temp);

    	 // the queue and list portion (Breadth-First Search BFS)
    	 while(!q.isEmpty() && numCompletions > 0) {
    		 // curr is the removed TrieNode
    		 TrieNode curr = q.remove();
    	
    		 // is curr TrieNode a word
    		 if(curr != null && curr.endsWord()) {
    			 list.add(curr.getText());
    			 numCompletions--;
    		 }
    		 
			 Set<Character> nextChars = curr.getValidNextCharacters();
			 // add child TrieNode of curr and continue while loop
			 for (char c : nextChars) {
				 q.add(curr.getChild(c));
			 }
		}
         return list;
     }

 	// For debugging
 	public void printTree() {
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr) {
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
 	public static void main(String args[]) {
 		String testString = "Something";
 		String second = "some";
 		AutoCompleteDictionaryTrie acdt = new AutoCompleteDictionaryTrie();
 		acdt.addWord(testString);
 		acdt.addWord(second);
 		acdt.addWord("SomeThing");
 		acdt.addWord("tester");
 		acdt.printTree();
 		System.out.println(acdt.isWord(second));
 		System.out.println(acdt.isWord("Something"));
 		System.out.println(acdt.isWord("tester"));
 		System.out.println(acdt.isWord("F"));
 		System.out.println("Size --> " + acdt.size());
 		List<String> completions = acdt.predictCompletions("so", 5);
 		System.out.println(completions);
 	}

	
}