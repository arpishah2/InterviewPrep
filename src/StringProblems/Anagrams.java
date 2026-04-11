package StringProblems;

import java.util.HashSet;
import java.util.Set;

/**
 * @author arpi
 * Write a function to check whether two given strings are anagram of each other or not.
 * An anagram of a string is another string that contains same characters, only the order of characters can be different.
 * For example, “abcd” and “dabc” are anagram of each other.
 */

public class Anagrams {

    public Anagrams() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        Anagrams ana = new Anagrams();
        System.out.println(ana.isAnagram("abcde", "bcdea"));
    }

    boolean isAnagram(String word1, String word2) {

        boolean isAna = false;

        if (word1.length() != word2.length())
            return isAna;

        Set<Character> set = new HashSet<Character>();

        //Add elements from word1 to set
        for (char ch : word1.toCharArray()) {
            set.add(ch);
        }

        //remove elements from set if the char exists, otherwise 'not Anagram'
        for (char ch : word2.toCharArray()) {

            if (!set.contains(ch))
                return isAna;
            set.remove(ch);
        }

        //if set is empty, it is Anagram
        if (set.isEmpty()) {
            isAna = true;
            return isAna;
        }
        return isAna;
    }

}
