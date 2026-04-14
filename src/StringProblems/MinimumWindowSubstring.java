package StringProblems;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 * The testcases will be generated such that the answer is unique.
 * Example 1:
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * <p>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 */
public class MinimumWindowSubstring {

    /**
     * Once we have a window with all the characters, we can move the left pointer ahead one by one.
     * If the window is still a desirable one we keep on updating the minimum window size.
     * left pointer - contract a given window
     * right pointer - expand the current window.
     * Expand the window till we find all the desirable characters.
     * Once found move left pointer and start creating new window to find another window
     *
     * Time Complexity: O(∣S∣+∣T∣) where |S| and |T| represent the lengths of strings S and T.
     * In the worst case we might end up visiting every element of string S twice, once by left pointer and once by right pointer. ∣T∣ represents the length of string T.
     *
     * Space Complexity: O(∣S∣+∣T∣). ∣S∣ when the window size is equal to the entire string S. ∣T∣ when T has all unique characters.
     */
    public String minWindow(String input, String desriedCharacterString) {

        // create dictionary of characters to look for in substring
        Map<Character, Integer> desiredDict = new HashMap<>();
        int desiredDictLength = desriedCharacterString.length();

        for (int i = 0; i < desiredDictLength; i++) {
            char key = desriedCharacterString.charAt(i);
            if (!desiredDict.containsKey(key)) {
                desiredDict.put(key, 1);
            } else {
                int count = desiredDict.get(key);
                desiredDict.put(key, count + 1);
            }
        }
        int desiredDictSize = desiredDict.size();

        // keeps track of the character counts in the current sliding window
        Map<Character, Integer> visitedDict = new HashMap<>();

        int left = 0, right = 0;

        // keeps track of how many unique characters were found in the current window, comparing with the desired char map
        int uniqueCharFoundLength = 0;

        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};

        while (left <= right && right < input.length()) {

            char charAtRight = input.charAt(right);
            if (desiredDict.containsKey(charAtRight)) {

                // updated visited dict with current char info
                if (visitedDict.containsKey(charAtRight)) {
                    visitedDict.compute(charAtRight, (k, currentCount) -> currentCount + 1);
                } else {
                    visitedDict.put(charAtRight, 1);
                }

                int countOfCurrentCharInTheWindow = visitedDict.get(charAtRight);

                // if count of char in window is same as count of same char in the desired string, we found one character
                int countOfDesiredChar = desiredDict.get(charAtRight);
                if (countOfCurrentCharInTheWindow == countOfDesiredChar) {
                    uniqueCharFoundLength++;
                }

                //shrink the window from left to find the smaller substring that has all desired characters
                while (left <= right && uniqueCharFoundLength == desiredDictSize) {

                    char charAtLeft = input.charAt(left);

                    //save the small window
                    int windowLength = right - left + 1;
                    if (ans[0] == -1 || windowLength < ans[0]) {
                        ans = new int[]{windowLength, left, right};
                    }

                    // The character at the position pointed by the
                    // `Left` pointer is no longer a part of the window.

                    // update the visited dict to remove info regarding left char frequency as we move left pointer forwared
                    if (visitedDict.containsKey(charAtLeft)) {
                        visitedDict.put(charAtLeft, visitedDict.get(charAtLeft) - 1);
                    }

                    if (desiredDict.containsKey(charAtLeft)) {
                        Integer desiredCharFrequency = desiredDict.get(charAtLeft);
                        Integer leftCharFrequency = visitedDict.get(charAtLeft);
                        if (leftCharFrequency < desiredCharFrequency) {
                            uniqueCharFoundLength--;
                        }
                    }


                    // Move the left pointer ahead, this would help to look for a new window.
                    left++;


                }
            }

            // Keep expanding the window once we are done contracting.
            right++;
        }


        int leftResultIndex = ans[1];
        int rightResultIndex = ans[2];

        return ans[0] == -1 ? "" : input.substring(leftResultIndex, rightResultIndex + 1);
    }

    public static void main(String[] args) {

        MinimumWindowSubstring sol = new MinimumWindowSubstring();

        runTest(sol, "ADOBECODEBANC", "ABC", "BANC");
        runTest(sol, "a", "a", "a");
        runTest(sol, "a", "aa", "");
        runTest(sol, "ab", "b", "b");
        runTest(sol, "aa", "aa", "aa");
        runTest(sol, "aaflslflsldkalskaaa", "aaa", "aaa");
        runTest(sol, "this is a test string", "tist", "t stri");
        runTest(sol, "geeksforgeeks", "ork", "ksfor");
        runTest(sol, "figehaeci", "aei", "aeci");

    }

    private static void runTest(MinimumWindowSubstring sol,
                                String s, String t, String expected) {

        String result = sol.minWindow(s, t);

        System.out.println("========================================");
        System.out.println("Input String (s):     \"" + s + "\"");
        System.out.println("Target String (t):    \"" + t + "\"");
        System.out.println("----------------------------------------");
        System.out.println("Expected Output:      \"" + expected + "\"");
        System.out.println("Actual Output:        \"" + result + "\"");

        if (result.equals(expected)) {
            System.out.println("✅ PASS");
        } else {
            System.out.println("❌ FAIL");
        }
        System.out.println("========================================\n");
    }

}
