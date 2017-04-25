package net.saisimon.leetcode;

/**
 * <h1>557. Reverse Words in a String III</h1>
 * 	<p>Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 * 
 * 	<p>Example 1:
 * 		<br>&nbsp; Input: "Let's take LeetCode contest"
 * 		<br>&nbsp; Output: "s'teL ekat edoCteeL tsetnoc"
 * 	<p>Note: 
 * 		<br>&nbsp; In the string, each word is separated by single space and there will not be any extra space in the string.
 * 
 * @author Saisimon
 *
 */
public class ReverseWordsThree {
	
	public String reverseWords(String s) {
		if (s == null) {
			return s;
		}
		s = s.trim();
        String[] words = s.split("\\s+");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < words.length; i++) {
        	StringBuffer wordSb = new StringBuffer(words[i]);
        	wordSb = wordSb.reverse();
        	sb.append(" ").append(wordSb);
        }
        String result = "";
        if (sb.length() != 0) {
        	result = sb.substring(1);
        }
        return result;
    }
	
}
