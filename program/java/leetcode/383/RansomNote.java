package net.saisimon.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 383. Ransom Note
 * 	Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters from  all  the  magazines,  
 * 	write  a  function  that  will  return  true  if  the  ransom   note  can  be  constructed  from  the  magazines ;  otherwise,  it  will  return  false.   
 * 	Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.
 * 	canConstruct("a", "b") -> false
 *	canConstruct("aa", "ab") -> false
 *	canConstruct("aa", "aab") -> true
 * 
 * @author Saisimon
 *
 */
public class RansomNote {
	// 使用 HashMap 存储
	public boolean canConstructA(String ransomNote, String magazine) {
        char[] ransomChars = ransomNote.toCharArray();
        char[] magazineChars = magazine.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazineChars.length; i++) {
			if (map.containsKey(magazineChars[i])) {
				map.put(magazineChars[i], map.get(magazineChars[i]) + 1);
			} else {
				map.put(magazineChars[i], 1);
			}
		}
        for (int i = 0; i < ransomChars.length; i++) {
			if (!map.containsKey(ransomChars[i])) {
				return false;
			} else {
				Integer count = map.get(ransomChars[i]);
				if (count == 0) {
					return false;
				} else {
					map.put(ransomChars[i], count - 1);
				}
			}
		}
        return true;
    }
	
	// 使用数组存储，更加高效
	public boolean canConstructB(String ransomNote, String magazine) {
		int[] arr = new int[26];
		for (int i = 0; i < magazine.length(); i++) {
			arr[magazine.charAt(i) - 'a']++;
		}
		for (int i = 0; i < ransomNote.length(); i++) {
			if (--arr[ransomNote.charAt(i) - 'a'] < 0) {
				return false;
			}
		}
		return true;
    }
	
}
