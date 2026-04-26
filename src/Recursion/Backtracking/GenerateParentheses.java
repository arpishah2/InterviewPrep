package Recursion.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * * TIME COMPLEXITY: O(4^n / n*sqrt(n))
 *  * Each valid sequence is a node in the recursion tree. The number of valid sequences
 *  * is the n-th Catalan number, which is bounded by O(4^n / (n * sqrt(n))).
 *  *
 *  * SPACE COMPLEXITY: O(n)
 *  * 1. Recursion Stack: The maximum depth of the recursion tree is 2n (linear).
 *  * 2. StringBuilder: Stores a string of length 2n.
 *  * Note: The space to store the results is O(4^n / sqrt(n)), but usually,
 *  * "auxiliary" space complexity focuses on the memory used to arrive at the solution.
 *
 */
public class GenerateParentheses {

    /**
     * Use backtracking to generate only valid strings.
     * This involves recursively building strings of length 2n and checking their validity as we go.
     * In case the current string is invalid, we will not continue the recursive process on it. Instead, we will backtrack to the previous valid string on the recursive path. This approach allows us to focus only on generating valid strings, thus saving us time and resources.
     * We continue the recursion only on the valid strings until we reach the ones of length 2n
     * <p>
     * Every string prefixed with ( is invalid. Also count no of opening and closing paranthese to do final validation on string
     * If left_count < n,  left parenthesis can be added, so we add one left parenthesis to cur_string, creating a new string new_string = cur_string + (, and then call backtracking(new_string, left_count + 1, right_count).
     * If left_count > right_count, a right parenthesis can be added to match a previous unmatched left parenthesis, so we add one right parenthesis to cur_string, creating a new string new_string = cur_string + ), and then call backtracking(new_string, left_count, right_count + 1).
     */
    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();
        backtracking(0, 0, n, new StringBuilder(), result);
        return result;
    }

    /**
     * STACK TRACE FOR n = 2:
     *
     * | Depth | left | right | curString | Decision/Action      | Result/Backtrack |
     * |-------|------|-------|-----------|----------------------|------------------|
     * | 1     | 0    | 0     | ""        | Add '(' (left < 2)   |                  |
     * | 2     | 1    | 0     | "("       | Add '(' (left < 2)   |                  |
     * | 3     | 2    | 0     | "(("      | Add ')' (left > right)|                  |
     * | 4     | 2    | 1     | "(()"     | Add ')' (left > right)|                  |
     * | 5     | 2    | 2     | "(())"    | **BASE CASE**        | Add "(())"       |
     * | 4     | 2    | 1     | "(()"     | Backtrack (remove ')')|                  |
     * | 3     | 2    | 0     | "(("      | Backtrack (remove ')')|                  |
     * | 2     | 1    | 0     | "("       | Add ')' (left > right)| (Switch Branch)  |
     * | 3     | 1    | 1     | "()"      | Add '(' (left < 2)   |                  |
     * | 4     | 2    | 1     | "()("     | Add ')' (left > right)|                  |
     * | 5     | 2    | 2     | "()()"    | **BASE CASE**        | Add "()()"       |
     * | 4     | 2    | 1     | "()("     | Backtrack (remove ')')|                  |
     * | 3     | 1    | 1     | "()"      | Backtrack (remove '(')|                  |
     * | 2     | 1    | 0     | "("       | Backtrack (remove '(')|                  |
     */
    private void backtracking(int openingCount, int closingCount, int n, StringBuilder currentString, List<String> result) {
        if (currentString.length() == 2 * n) {
            result.add(currentString.toString());
            return;
        }

        if (openingCount < n) {
            currentString.append("(");
            backtracking(openingCount + 1, closingCount, n, currentString, result);
            currentString.deleteCharAt(currentString.length() - 1);
        }
        if (closingCount < openingCount) {
            currentString.append(")");
            backtracking(openingCount, closingCount + 1, n, currentString, result);
            currentString.deleteCharAt(currentString.length() - 1);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses solution = new GenerateParentheses();

        // Test cases to run
        int[] testCases = {1, 2, 3};

        for (int n : testCases) {
            System.out.println("Test Case: n = " + n);
            List<String> results = solution.generateParenthesis(n);

            System.out.println("Total combinations: " + results.size());
            System.out.println("Combinations:");
            // Pretty printing each result on a new line with indentation
            for (String s : results) {
                System.out.println("  -> " + s);
            }
            System.out.println("---------------------------------------");
        }
    }

}
