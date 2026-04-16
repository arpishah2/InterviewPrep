package Stack;

import java.util.*;

/**
 * Solution for the "Valid Parentheses" problem.
 * <p>
 * This class determines if an input string containing brackets is valid based on
 * specific closing rules. It uses a Stack-based approach to ensure proper nesting
 * and ordering.
 * </p>
 *
 * <h2>Problem Description</h2>
 * A string is valid if:
 * <ul>
 *   <li>Open brackets are closed by the same type of brackets.</li>
 *   <li>Open brackets are closed in the correct order.</li>
 *   <li>Every close bracket has a corresponding open bracket.</li>
 * </ul>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 &le; s.length &le; 10⁴</li>
 *   <li>s consists of parentheses only: '()[]{}'.</li>
 * </ul>
 *
 * @author YourName
 */
public class ValidParentheses {

    /**
     * Checks if the input string of brackets is valid.
     * <p>
     * Time Complexity: O(n) where n is the length of the string.
     * Space Complexity: O(n) in the worst case where all characters are opening brackets.
     * </p>
     *
     * @param input the string to validate
     * @return {@code true} if valid, {@code false} otherwise
     */
    public boolean isValid(String input) {

        if (input.isEmpty()) {
            return false;
        }

        Set<Character> validOpeningBrace = new HashSet<>(Arrays.asList('(', '{', '['));

        Map<Character, Character> bracePairMappings = new HashMap<>();
        bracePairMappings.put(')', '(');
        bracePairMappings.put('}', '{');
        bracePairMappings.put(']', '[');

        Stack<Character> strStack = new Stack<>();

        for (char brace : input.toCharArray()) {

            if (validOpeningBrace.contains(brace)) {
                //opening brace
                strStack.push(brace);
            } else if (bracePairMappings.containsKey(brace)) {
                if (!strStack.isEmpty() && bracePairMappings.get(brace).equals(strStack.peek())) {
                    //closing brace for a matched opening brace
                    strStack.pop();
                } else {
                    return false;
                }
            }
        }

        return strStack.isEmpty();

    }

    /**
     * Main method to execute test cases with pretty-printed output.
     */
    public static void main(String[] args) {
        ValidParentheses validator = new ValidParentheses();

        String[] testCases = {
                "()",
                "()[]{}",
                "(]",
                "([])",
                "([)]",
                "((",
                "]]]"
        };

        boolean[] expectedResults = {true, true, false, true, false, false, false};

        System.out.println("===============================================================");
        System.out.printf("%-15s | %-10s | %-10s | %-10s%n", "Input String", "Expected", "Actual", "Status");
        System.out.println("---------------------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            String input = testCases[i];
            boolean expected = expectedResults[i];
            boolean actual = validator.isValid(input);
            String status = (expected == actual) ? "PASS" : "FAIL";

            System.out.printf("%-15s | %-10b | %-10b | %-10s%n", input, expected, actual, status);
        }
        System.out.println("===============================================================");
    }
}
