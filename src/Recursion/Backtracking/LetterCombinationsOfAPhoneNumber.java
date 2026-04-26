package Recursion.Backtracking;

import java.util.*;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * Example 1:
 * <p>
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * <p>
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 * https://www.youtube.com/watch?v=0snEunUacZY
 */
public class LetterCombinationsOfAPhoneNumber {


    Map<Character, List<Character>> numberToLettersMap = new HashMap<>();


    public List<String> letterCombinations(String digits) {

        //create mapping of no with letters
        populateNumberToLetterMap();


        Set<String> combinedDigitLettersResult = new HashSet<>();

        backtrackedCombinedString(0, digits, new StringBuilder(), combinedDigitLettersResult);

        return new ArrayList<>(combinedDigitLettersResult);
    }

    private void backtrackedCombinedString(int indexOfDigit, String digits, StringBuilder currentString, Set<String> combinedDigitLettersResult) {

        if (digits.length() == currentString.length()) {
            //found the string to add to results
            combinedDigitLettersResult.add(currentString.toString());
            return;
        }


        Character currentDigit = digits.charAt(indexOfDigit);
        List<Character> letters = numberToLettersMap.get(currentDigit);

        for (Character letter : letters) {
            currentString.append(letter);
            backtrackedCombinedString(indexOfDigit + 1, digits, currentString, combinedDigitLettersResult);
            // 3. BACKTRACK (Remove the last character so the next loop can use a fresh string)
            currentString.deleteCharAt(currentString.length() - 1);
        }
    }

    private void populateNumberToLetterMap() {
        numberToLettersMap.put('2'
                , new ArrayList<>(Arrays.asList('a', 'b', 'c')));
        numberToLettersMap.put('3'
                , new ArrayList<>(Arrays.asList('d', 'e', 'f')));
        numberToLettersMap.put('4'
                , new ArrayList<>(Arrays.asList('g', 'h', 'i')));
        numberToLettersMap.put('5'
                , new ArrayList<>(Arrays.asList('j', 'k', 'l')));
        numberToLettersMap.put('6'
                , new ArrayList<>(Arrays.asList('m', 'n', 'o')));
        numberToLettersMap.put('7'
                , new ArrayList<>(Arrays.asList('p', 'q', 'r', 's')));
        numberToLettersMap.put('8'
                , new ArrayList<>(Arrays.asList('t', 'u', 'v')));
        numberToLettersMap.put('9'
                , new ArrayList<>(Arrays.asList('w', 'x', 'y', 'z')));
    }

    // --- Main Method with Pretty Print Test Cases ---
    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber solver = new LetterCombinationsOfAPhoneNumber();

        runTest(solver, "23");
        runTest(solver, "2");
        runTest(solver, "");
        runTest(solver, "7");
    }

    private static void runTest(LetterCombinationsOfAPhoneNumber solver, String digits) {
        System.out.println("TESTING INPUT: \"" + digits + "\"");
        List<String> result = solver.letterCombinations(digits);

        if (result.isEmpty()) {
            System.out.println("Result: [] (Empty List)");
        } else {
            System.out.println("Total Combinations: " + result.size());
            System.out.println("Combinations: " + result);
        }
        System.out.println("--------------------------------------------------\n");
    }

}
