package net.saisimon.leetcode;

/**
 * <h1>520. Detect Capital</h1>
 * 	<p>Given a word, you need to judge whether the usage of capitals in it is right or not.
 *
 *	<p>We define the usage of capitals in a word to be right when one of the following cases holds:
 *		<br>&nbsp; All letters in this word are capitals, like "USA".
 *		<br>&nbsp; All letters in this word are not capitals, like "leetcode".
 *		<br>&nbsp; Only the first letter in this word is capital if it has more than one letter, like "Google".
 *
 *	<p>Otherwise, we define that this word doesn't use capitals in a right way.
 *
 *	<p>Example 1:
 *		<br>&nbsp; Input: "USA"
 *		<br>&nbsp; Output: True
 *
 *	<p>Example 2:
 *		<br>&nbsp; Input: "FlaG"
 *		<br>&nbsp; Output: False
 *
 *	<p>Note: 
 *		<br>&nbsp; The input will be a non-empty word consisting of uppercase and lowercase latin letters.
 * 
 * @author Saisimon
 *
 */
public class DetectCapital {
	
	public boolean detectCapitalUse(String word) {
    	String tail = word.substring(1);
    	return tail.toLowerCase().equals(tail) ? true : 
    		!Character.isLowerCase(word.charAt(0)) && tail.toUpperCase().equals(tail);
    }
	
}
