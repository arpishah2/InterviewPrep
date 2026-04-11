package StringProblems;/*
 *Given a string, print the first unique (non-repeated) letter in it.
 *char firstUniqueLetter(String word)
 */

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FirstUniqueCharacter {

    public FirstUniqueCharacter() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String args[]) {
        String input = "nonorepeat";
        FirstUniqueCharacter fst = new FirstUniqueCharacter();
        System.out.println("First non repeated character is " + fst.firstUniqueLetter(input));
    }

    char firstUniqueLetter(String word) {
        Map<Character, Integer> m = new LinkedHashMap<Character, Integer>();

        //Add character and count in linked hash map
        for (char ch : word.toCharArray()) {
            if (!m.containsKey(ch))
                m.put(ch, 1);
            else
                m.put(ch, 1 + m.get(ch));
        }

        //Iterate over map to get first non repeated char
        for (Entry<Character, Integer> entry : m.entrySet()) {
            if (entry.getValue() == 1)
                return entry.getKey();
        }

        //if no unique char
        //'\u0000' is the default value for a character. It's decimal equivalent is 0.
        return '\u0000';


    }
}
