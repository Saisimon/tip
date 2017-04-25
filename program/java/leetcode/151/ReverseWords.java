package net.saisimon.leetcode;

/**
 * <h1>151. Reverse Words in a String</h1>
 * 	<p>Given an input string, reverse the string word by word.
 * 
 * 	<p>For example,
 * 		<br>&nbsp; Given s = "the sky is blue",
 * 		<br>&nbsp; return "blue is sky the".
 * 
 * @author Saisimon
 *
 */
public class ReverseWords {
	
	public String reverseWords(String s) {
		if (s == null) {
			return s;
		}
		s = s.trim();
        String[] words = s.split("\\s+");
        StringBuffer sb = new StringBuffer();
        for (int i = words.length - 1; i >= 0; i--) {
        	sb.append(" ").append(words[i]);
        }
        String result = "";
        if (sb.length() != 0) {
        	result = sb.substring(1);
        }
        return result;
    }
	
}
