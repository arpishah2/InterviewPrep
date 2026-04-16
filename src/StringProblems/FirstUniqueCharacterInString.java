package StringProblems;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find the first non-repeating character in it and return its index.
 * If it does not exist, return -1.
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of only lowercase English letters.
 * <p>
 * Example 1:
 * Input: s = "leetcode" | Output: 0
 * <p>
 * Example 2:
 * Input: s = "loveleetcode" | Output: 2
 */
public class FirstUniqueCharacterInString {


    /**
     * Finds the index of the first non-repeating character.
     * Uses a frequency array for O(n) time and O(1) space (fixed 26 chars).
     *
     * @param s The input string consisting of lowercase English letters.
     * @return The index of the first unique character, or -1 if none exist.
     */
    public int firstUniqChar(String s) {
        int length = s.length();
        if (length == 0) {
            return -1;
        }

        //Build map to see how many times each character occured in the string
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            charFrequencyMap.put(s.charAt(i), charFrequencyMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        //return the first character whose frequency in map is 1
        for (int i = 0; i < length; i++) {
            if (charFrequencyMap.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FirstUniqueCharacterInString solution = new FirstUniqueCharacterInString();

        // Test Case 1
        String s1 = "leetcode";
        System.out.println("Test Case 1: " + s1);
        System.out.println("Expected: 0, Result: " + solution.firstUniqChar(s1));

        // Test Case 2
        String s2 = "loveleetcode";
        System.out.println("\nTest Case 2: " + s2);
        System.out.println("Expected: 2, Result: " + solution.firstUniqChar(s2));

        // Test Case 3
        String s3 = "aabb";
        System.out.println("\nTest Case 3: " + s3);
        System.out.println("Expected: -1, Result: " + solution.firstUniqChar(s3));
    }
}
