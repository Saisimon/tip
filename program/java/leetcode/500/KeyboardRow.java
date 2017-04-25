package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>500. Keyboard Row</h1>
 * 	<p>Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.
 * 
 * 	<p>Example 1:
 * 		<br>&nbsp; Input: ["Hello", "Alaska", "Dad", "Peace"]
 * 		<br>&nbsp; Output: ["Alaska", "Dad"]
 * 
 * 	<p>Note:
 * 		<br>&nbsp; You may use one character in the keyboard more than once.
 * 		<br>&nbsp; You may assume the input string will only contain letters of alphabet.
 * 
 * @author Saisimon
 *
 */
public class KeyboardRow {
	
	private static Set<Character> firstLine = new HashSet<>(Arrays.asList(new Character[]{'q','w','e','r','t','y','u','i','o','p'}));
	private static Set<Character> secondLine = new HashSet<>(Arrays.asList(new Character[]{'a','s','d','f','g','h','j','k','l'}));
	private static Set<Character> thirdLine = new HashSet<>(Arrays.asList(new Character[]{'z','x','c','v','b','n','m'}));
	
	private static List<Set<Character>> allLine = new ArrayList<>();
	
	static {
		allLine.add(firstLine);
		allLine.add(secondLine);
		allLine.add(thirdLine);
	}
	
	public String[] findWords(String[] words) {
        if (words == null) {
        	return words;
        }
        List<String> result = new ArrayList<>();
        for (String word : words) {
        	String w = word.toLowerCase();
        	char c = w.charAt(0);
        	Set<Character> selectLine = null;
        	for (Set<Character> line : allLine) {
        		if (line.contains(c)) {
        			selectLine = line;
        			break;
        		}
        	}
        	boolean push = true;
        	for (int i = 1; i < w.length(); i++) {
        		if (!selectLine.contains(w.charAt(i))) {
        			push = false;
        			break;
        		}
        	}
        	if (push) {
        		result.add(word);
        	}
        }
        return result.toArray(new String[0]);
    }
	
}
