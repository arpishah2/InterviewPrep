package StringProblems;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find the length of the longest substring without duplicate characters.
 * A substring is a contiguous sequence of characters within a larger string
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /*
     * PROBLEM: Longest Substring Without Repeating Characters
     * STRATEGY: Optimized Sliding Window (HashMap)
     *
     * 🚀 LOGIC WALKTHROUGH: "pwwkewabca"
     *
     *      j = Scout (Right boundary)
     *      i = Anchor (Left boundary)
     *
     *      [ Index ]  [ Char ]      [ Action ]           [ i (Anchor) Jump ]              [ Current Window ]   [ Max ]
     *      -----------------------------------------------------------------------------------------------------------
     *
     *        j = 0      'p'         First seen           i = 0                            [ p ]                 1
     *
     *        j = 1      'w'         First seen           i = 0                            [ p w ]               2
     *
     *        j = 2      'w'         REPEAT (w)           i = max(0, prev_w_idx + 1) = 2   [ w ]                 2
     *                                                    // i jumps to index 2
     *
     *        j = 3      'k'         First seen           i = 2                            [ w k ]               2
     *
     *        j = 4      'e'         First seen           i = 2                            [ w k e ]             3
     *
     *        j = 5      'w'         REPEAT (w)           i = max(2, prev_w_idx + 1) = 3   [ k e w ]             3
     *                                                    // i jumps to index 3
     *
     *        j = 6      'a'         First seen           i = 3                            [ k e w a ]           4
     *
     *        j = 7      'b'         First seen           i = 3                            [ k e w a b ]         5
     *
     *        j = 8      'c'         First seen           i = 3                            [ k e w a b c ]       6  <-- WINNER
     *
     *        j = 9      'a'         REPEAT (a)           i = max(3, prev_a_idx + 1) = 7   [ b c a ]             6
     *                                                    // i jumps to index 7
     *
     *      -----------------------------------------------------------------------------------------------------------
     *      FINAL RESULT: 6
     *      TIME COMPLEXITY: O(n) - Each character is visited once.
     *      SPACE COMPLEXITY: O(min(m, n)) - For the HashMap.
     *
     *
     */

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charLastSeen = new HashMap<>();
        int maxLength = 0;

        for (int i = 0, j = 0; j < s.length(); j++) {

            if (!charLastSeen.containsKey(s.charAt(j))) {
                charLastSeen.put(s.charAt(j), j);
            } else {

                //char was seen previously
                int previousLocationOfChar = charLastSeen.get(s.charAt(j));
                charLastSeen.put(s.charAt(j), j);

                /*
                 * STRATEGY: THE SMART JUMP
                 * If we see a repeat, we want to jump 'i' to 'previousLocation + 1'.
                 * BUT, we must use Math.max because that 'previousLocation' might
                 * be outside our current window (e.g., in "abba", when we hit the last 'a',
                 * the first 'a' is at index 0, but our anchor 'i' is already at 2).
                 * Math.max prevents the window from "expanding backwards".
                 */
                i = Math.max(previousLocationOfChar + 1, i);
            }

            maxLength = Math.max(j - i + 1, maxLength);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters sol = new LongestSubstringWithoutRepeatingCharacters();

        // Test Case 1: Standard example with multiple unique stretches
        printResult("abcabcbb", sol.lengthOfLongestSubstring("abcabcbb"), 3);

        // Test Case 2: Back-to-back repeats
        printResult("bbbbb", sol.lengthOfLongestSubstring("bbbbb"), 1);

        // Test Case 3: The "Teleport" example from our notes
        printResult("pwwkew", sol.lengthOfLongestSubstring("pwwkew"), 3);

        // Test Case 4: The "Backward Trap" (ensures Math.max works)
        printResult("abba", sol.lengthOfLongestSubstring("abba"), 2);

        // Test Case 5: Empty string
        printResult("'' (empty)", sol.lengthOfLongestSubstring(""), 0);
    }

    private static void printResult(String input, int result, int expected) {
        System.out.printf("Input: %-12s | Result: %d | Expected: %d | %s%n",
                input, result, expected, (result == expected ? "✅ PASS" : "❌ FAIL"));
    }

}
