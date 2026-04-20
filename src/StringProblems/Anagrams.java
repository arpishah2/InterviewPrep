package StringProblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author arpi
 * Write a function to check whether two given strings are anagram of each other or not.
 * An anagram of a string is another string that contains same characters, only the order of characters can be different.
 * For example, “abcd” and “dabc” are anagram of each other.
 */

public class Anagrams {

    boolean isAnagram_HashTable(String word1, String word2) {

        boolean isAna = false;

        if (word1.length() != word2.length())
            return isAna;

        Map<Character, Integer> charFrequencyMap = new Hashtable<>();

        //Add elements from word1 to set
        for (char ch : word1.toCharArray()) {
            charFrequencyMap.put(ch, charFrequencyMap.getOrDefault(ch, 0) + 1);
        }

        //remove elements from set if the char exists, otherwise 'not Anagram'
        for (char ch : word2.toCharArray()) {
            if (!charFrequencyMap.containsKey(ch))
                return false;
            int freq = charFrequencyMap.get(ch);
            if (freq != 1) {
                charFrequencyMap.put(ch, charFrequencyMap.get(ch) - 1);
            } else {
                charFrequencyMap.remove(ch);
            }
        }

        //if set is empty, it is Anagram
        if (charFrequencyMap.isEmpty()) {
            return true;
        }
        return isAna;
    }

    boolean isAnagram_FrequencyCounter(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] charFreq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charFreq[s.charAt(i) - 'a']++;
            charFreq[t.charAt(i) - 'a']--;
        }
        for (int count : charFreq) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Time complexity = O(n log n)
     * Space complexity = O(1)
     */
    public boolean isAnagram_Sorting(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        char[] sArr = s.toCharArray();
        Arrays.sort(sArr);
        char[] tArr = t.toCharArray();
        Arrays.sort(tArr);

        return Arrays.equals(sArr, tArr);

    }

    // ===================== PRETTY PRINT =====================
    public static void runTest(String s, String t) {

        Anagrams obj = new Anagrams();

        System.out.println("========================================");
        System.out.println("String 1: " + s);
        System.out.println("String 2: " + t);
        System.out.println("----------------------------------------");

        System.out.printf("%-30s %-10s\n", "Approach", "Result");
        System.out.println("----------------------------------------");

        System.out.printf("%-30s %-10s\n", "HashTable", obj.isAnagram_HashTable(s, t));
        System.out.printf("%-30s %-10s\n", "Frequency Counter", obj.isAnagram_FrequencyCounter(s, t));
        System.out.printf("%-30s %-10s\n", "Sorting", obj.isAnagram_Sorting(s, t));

        System.out.println("========================================\n");
    }

    // ===================== STEP VISUALIZATION =====================
    public static void visualizeHashMap(String s, String t) {

        System.out.println("🔍 Step-by-step (HashMap approach)");
        System.out.println("----------------------------------------");

        Map<Character, Integer> map = new HashMap<>();

        System.out.println("Building frequency map from: " + s);
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            System.out.println("Add " + ch + " → " + map);
        }

        System.out.println("\nProcessing second string: " + t);
        for (char ch : t.toCharArray()) {
            System.out.println("Checking " + ch + " → " + map);

            if (!map.containsKey(ch)) {
                System.out.println("❌ Not found → Not anagram");
                return;
            }

            int freq = map.get(ch);
            if (freq == 1) map.remove(ch);
            else map.put(ch, freq - 1);
        }

        System.out.println("\nFinal Map: " + map);
        System.out.println(map.isEmpty() ? "✅ Anagram" : "❌ Not Anagram");
        System.out.println("----------------------------------------\n");
    }

    // ===================== PSVM =====================
    public static void main(String[] args) {

        // Basic tests
        runTest("abcde", "bcdea");
        runTest("listen", "silent");
        runTest("triangle", "integral");

        // Negative cases
        runTest("hello", "world");
        runTest("rat", "car");

        // Edge cases
        runTest("", "");
        runTest("a", "a");

        // Duplicate characters (important)
        runTest("aabbcc", "abcabc");
        runTest("aabbcc", "abcccd");

        // Visualization (important for interview explanation)
        visualizeHashMap("listen", "silent");
    }

}
