package StringProblems;

import java.util.*;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * There is no string in strs that can be rearranged to form "bat".
 * The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
 * The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
 * <p>
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 * <p>
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 * Constraints:
 * <p>
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
public class GroupAnagrams {

    /*
    Two strings are anagrams if and only if their sorted strings are equal.
    Maintain a map ans: {String -> List} where each key K is a sorted string, and each value is the list of strings
    from the initial input that when sorted, are equal to K.
    Time Complexity: O(NKlogK), where N is the length of strs, and K is the maximum length of a string in strs.
    complexity O(N) as we iterate through each string. Then, we sort each string in O(KlogK) time.
    Space Complexity: O(NK), the total information content stored in ans.
     */
    public List<List<String>> groupAnagrams_UsingStringReversal(String[] inputStrings) {

        Map<String, List<String>> anagramGroupBySortedInput = new HashMap<>();
        for (String currentStr : inputStrings) {
            char[] currentCharArr = currentStr.toCharArray();
            Arrays.sort(currentCharArr);
            String sortedString = String.valueOf(currentCharArr);

            if (!anagramGroupBySortedInput.containsKey(sortedString)) {
                anagramGroupBySortedInput.put(sortedString, new ArrayList<>());
            }
            anagramGroupBySortedInput.get(sortedString).add(currentStr);

        }
        return new ArrayList<>(anagramGroupBySortedInput.values());
    }


    /*
    Two strings are anagrams if and only if their character counts (respective number of occurrences of each character) are the same.
    For example, abbccc will be #1#2#3#0#0#0...#0 where there are 26 entries total.
    Time Complexity: O(NK), where N is the length of strs, and K is the maximum length of a string in strs
    Space Complexity: O(NK), the total information content stored in ans.
     */
    public List<List<String>> groupAnagrams_UsingHash(String[] inputStrings) {

        if (inputStrings.length == 0) return new ArrayList();

        //Hash to strings with same hash
        Map<String, List<String>> anagramGroup = new HashMap<>();
        int[] count = new int[26];

        for (String currentStr : inputStrings) {

            //count occurence of each char
            Arrays.fill(count, 0);
            char[] currentCharArr = currentStr.toCharArray();
            for (char c : currentCharArr) {
                count[c - 'a']++;
            }

            //generate key
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();

            if (!anagramGroup.containsKey(key)) {
                anagramGroup.put(key, new ArrayList<>());
            }
            anagramGroup.get(key).add(currentStr);

        }
        return new ArrayList<>(anagramGroup.values());
    }

    public static void main(String[] args) {
        GroupAnagrams sol = new GroupAnagrams();

        System.out.println("==============================================");
        System.out.println("        RUNNING GROUP ANAGRAMS TESTS          ");
        System.out.println("==============================================");

        // Case 1: Standard variety
        runTest(sol, 1, new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}, 3);

        // Case 2: Empty string
        runTest(sol, 2, new String[]{""}, 1);

        // Case 3: Single character
        runTest(sol, 3, new String[]{"a"}, 1);

        // Case 4: No anagrams
        runTest(sol, 4, new String[]{"abc", "def", "ghi"}, 3);

        // Case 5: Many duplicates
        runTest(sol, 5, new String[]{"bob", "bob", "obb", "bbo"}, 1);

        System.out.println("==============================================");


        System.out.println("==============================================");
        System.out.println("        RUNNING GROUP ANAGRAMS TESTS          ");
        System.out.println("==============================================");

        // Case 1: Standard variety
        runTestMapWithHashedKey(sol, 1, new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}, 3);

        // Case 2: Empty string
        runTestMapWithHashedKey(sol, 2, new String[]{""}, 1);

        // Case 3: Single character
        runTestMapWithHashedKey(sol, 3, new String[]{"a"}, 1);

        // Case 4: No anagrams
        runTestMapWithHashedKey(sol, 4, new String[]{"abc", "def", "ghi"}, 3);

        // Case 5: Many duplicates
        runTestMapWithHashedKey(sol, 5, new String[]{"bob", "bob", "obb", "bbo"}, 1);

        System.out.println("==============================================");
    }

    private static void runTest(GroupAnagrams sol, int testNum, String[] input, int expectedGroups) {
        List<List<String>> result = sol.groupAnagrams_UsingStringReversal(input);
        boolean passed = (result.size() == expectedGroups);
        String status = passed ? "✅ PASS" : "❌ FAIL";
        System.out.printf("TEST #%d | %s\n", testNum, status);
        System.out.println("Input    : " + Arrays.toString(input));
        System.out.println("Output   : " + result);
        System.out.println("Groups   : " + result.size() + " (Expected: " + expectedGroups + ")");
        System.out.println("----------------------------------------------");
    }

    private static void runTestMapWithHashedKey(GroupAnagrams sol, int testNum, String[] input, int expectedGroups) {
        List<List<String>> result = sol.groupAnagrams_UsingHash(input);
        boolean passed = (result.size() == expectedGroups);
        String status = passed ? "✅ PASS" : "❌ FAIL";
        System.out.printf("TEST #%d | %s\n", testNum, status);
        System.out.println("Input    : " + Arrays.toString(input));
        System.out.println("Output   : " + result);
        System.out.println("Groups   : " + result.size() + " (Expected: " + expectedGroups + ")");
        System.out.println("----------------------------------------------");
    }
}
