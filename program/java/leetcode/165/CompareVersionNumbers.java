package net.saisimon.leetcode;

/**
 * 165. Compare Version Numbers
 * 	Compare two version numbers version1 and version2.
 * 	If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * 		
 * 	You may assume that the version strings are non-empty and contain only digits and the . character.
 * 	The . character does not represent a decimal point and is used to separate number sequences.
 * 	For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 * 	
 * 	Here is an example of version numbers ordering:
 * 		0.1 < 1.1 < 1.2 < 13.37
 * 
 * @author Saisimon
 *
 */
public class CompareVersionNumbers {
	public int compareVersion(String version1, String version2) {
		// 根据 . 分割字符串
        String[] vs1 = version1.split("\\.");
        String[] vs2 = version2.split("\\.");
        int i = 0;
        // 遍历比较大小
        for (; i < Math.min(vs1.length, vs2.length); i++) {
			int t1 = Integer.parseInt(vs1[i]);
			int t2 = Integer.parseInt(vs2[i]);
			if (t1 > t2) {
				return 1;
			} else if (t1 < t2) {
				return -1;
			}
		}
        // 循环结束， 还没有比较出结果，就按照更长的那个字符串遍历查找，看是否剩余版本号为 0 
        if (i < vs1.length) {
        	for (int j = i; j < vs1.length; j++) {
				if (Integer.parseInt(vs1[j]) != 0) {
					return 1;
				}
			}
        } else if (i < vs2.length) {
        	for (int j = i; j < vs2.length; j++) {
				if (Integer.parseInt(vs2[j]) != 0) {
					return -1;
				}
			}
        }
        return 0;
    }
}
