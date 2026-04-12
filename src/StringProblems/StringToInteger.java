package StringProblems;

/**
 * PROBLEM: String to Integer (myAtoi)
 * <p>
 * ALGORITHM:
 * 1. Whitespace: Skip all leading spaces " ".
 * 2. Signedness: Check for '+' or '-'. Default to positive if neither exists.
 * 3. Conversion: Read digits until a non-digit character or end of string.
 * Skip leading zeros. If no digits are found, return 0.
 * 4. Rounding:   Clamp the result within the 32-bit signed integer range:
 * [-2^31, 2^31 - 1].
 * <p>
 * EXAMPLES:
 * - "42"              -> 42
 * - "   -042"         -> -42
 * - "1337c0d3"        -> 1337 (stops at 'c')
 * - "0-1"             -> 0    (stops at '-')
 * - "words and 987"   -> 0    (stops at 'w')
 * <p>
 * CONSTRAINTS:
 * - Length: 0 <= s.length <= 200
 * - Characters: English letters, digits, ' ', '+', '-', '.'
 */

public class StringToInteger {

    /*
     * PROBLEM: String to Integer (myAtoi)
     *
     * 🚀 COMPREHENSIVE EXECUTION EXAMPLES:
     * -----------------------------------------------------------------------------------------
     * CASE 1: "   -42abc" (Leading whitespace + Sign + Garbage suffix)
     *   1. .trim()       -> "-42abc"
     *   2. i = 0 ('-')   -> sign = -1. i=0 & !digit is true. 'continue' to skip math.
     *   3. i = 1 ('4')   -> result = (0 * 10) + 4 = 4.
     *   4. i = 2 ('2')   -> result = (4 * 10) + 2 = 42.
     *   5. i = 3 ('a')   -> !digit is true. 'break' loop immediately.
     *   RESULT           -> sign(-1) * result(42) = -42
     *
     * -----------------------------------------------------------------------------------------
     * CASE 2: "99999999999" (Integer Overflow)
     *   1. .trim()       -> "99999999999"
     *   2. i = 0 to 8    -> result builds up to 999,999,999.
     *   3. i = 9 ('9')   -> result = 9,999,999,999.
     *   4. Check         -> (1 * 9,999,999,999) > 2,147,483,647? YES.
     *   RESULT           -> Returns Integer.MAX_VALUE
     *
     * -----------------------------------------------------------------------------------------
     * CASE 3: "+-12" (Double Sign Trap)
     *   1. .trim()       -> "+-12"
     *   2. i = 0 ('+')   -> sign = 1. 'continue' to skip math.
     *   3. i = 1 ('-')   -> i != 0. !digit is true.
     *   4. Action        -> 'break' loop.
     *   RESULT           -> sign(1) * result(0) = 0
     *
     * -----------------------------------------------------------------------------------------
     * CASE 4: "0000042" (Leading Zeros)
     *   1. .trim()       -> "0000042"
     *   2. i = 0 to 4    -> result remains 0 because (0 * 10) + 0 = 0.
     *   3. i = 5 ('4')   -> result = 4.
     *   4. i = 6 ('2')   -> result = 42.
     *   RESULT           -> 42
     *
     * -----------------------------------------------------------------------------------------
     * CASE 5: "  /42" (Garbage Start)
     *   1. .trim()       -> "/42"
     *   2. i = 0 ('/')   -> !digit is true. Not '+' or '-'.
     *   3. Action        -> 'else' block triggers: 'return 0' immediately.
     *   RESULT           -> 0
     *
     * -----------------------------------------------------------------------------------------
     * CASE 6: "42 56" (Middle Space)
     *   1. .trim()       -> "42 56"
     *   2. i = 0 to 1    -> result = 42.
     *   3. i = 2 (' ')   -> !digit is true. 'break' loop.
     *   RESULT           -> 42 (The '56' is ignored)
     * -----------------------------------------------------------------------------------------
     */


    /*
     * ⚡ ALGORITHM EXPLAINED:
     *
     * 1. WHITESPACE CLEANING:
     *    Used .trim() to satisfy the "Leading Whitespace" rule. This ensures
     *    index 0 is always the start of actual data (sign or digit).
     *
     * 2. THE SIGN POINTER:
     *    We check index 0 for '+' or '-'. Crucially, we use 'continue' to skip
     *    the math for that iteration so the sign symbol itself doesn't get
     *    processed as a digit.
     *
     * 3. THE "HARD STOP":
     *    The loop 'breaks' the moment it hits a non-digit character (like a
     *    space, letter, or second sign). This handles cases like "42abc"
     *    where only the "42" is converted.
     *
     * 4. ASCII MATH:
     *    Converted characters to integers by subtracting the character '0'
     *    (currentChar - '0'). This is faster and more reliable than Integer.valueOf().
     *
     * 5. PROACTIVE OVERFLOW:
     *    We use a 'long' and check against Integer.MAX_VALUE / MIN_VALUE
     *    INSIDE the loop. This prevents the number from "wrapping around"
     *    if the input string is longer than a 'long' can hold.
     *
     * -------------------------------------------------------------------------
     * 📊 COMPLEXITY ANALYSIS:
     *
     * TIME COMPLEXITY: O(N)
     * - We perform a single pass through the string of length N.
     * - Each character is inspected exactly once.
     * - Even though .trim() technically does its own pass first, O(N + N)
     *   simplifies to O(N) in Big O notation.
     *
     * SPACE COMPLEXITY: O(N)
     * - .trim() creates a brand new String object in memory to store the
     *   cleaned version of the input.
     * - The amount of extra space used grows linearly with the input size.
     * - (Optimization Note: Using a manual index pointer to skip spaces
     *   would reduce this to O(1) space).
     * -------------------------------------------------------------------------
     */


    public int myAtoi(String s) {
        String input = s.trim();
        if (input.isEmpty()) return 0;

        long result = 0;
        long sign = 1;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            // Handle start-of-string logic
            if (i == 0 && !Character.isDigit(currentChar)) {
                if (currentChar == '-') {
                    sign = -1;
                    continue; // Skip math for the '-' char
                } else if (currentChar == '+') {
                    sign = 1;
                    continue; // Skip math for the '+' char
                } else {
                    return 0; // Immediate garbage like "/42"
                }
            }

            // Handle non-digits after the start
            if (!Character.isDigit(currentChar)) {
                break; // Stop and return current progress
            }

            // Perform math
            int currentDigit = currentChar - '0';
            result = result * 10 + currentDigit;

            // Check for overflow inside the loop
            if (sign * result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign * result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }

        return (int) (sign * result);

        /**
         * <p>
         * 🧠 MISTAKES TO AVOID:
         * 1. THE CHAR-TO-INT TRAP:
         * - Don't use Integer.valueOf(char). It returns the ASCII code (e.g., '4' -> 52).
         * - Always use (currentChar - '0') to get the actual numeric value.
         * <p>
         * 2. THE SIGN "FALL-THROUGH":
         * - If you handle a sign (+/-) at index 0, you MUST use 'continue'.
         * - Without 'continue', the code will try to perform math on the '+' or '-'
         * character itself, corrupting the result.
         * <p>
         * 3. THE "LONG" OVERFLOW LIMIT:
         * - Don't wait until after the loop to check for overflow.
         * - If an input has 20+ digits, even a 'long' will overflow and wrap around.
         * - Always check (sign * result) against Integer.MAX/MIN_VALUE INSIDE the loop.
         * <p>
         * 4. THE DOUBLE-SIGN FLIP:
         * - Avoid: result = result * sign; return (int) (result * sign);
         * - If you multiply by the sign twice, a negative number will flip back to positive.
         * - Keep 'result' positive in the loop and apply 'sign' only during checks and the final return.
         * <p>
         * 5. THE GARBAGE STOP:
         * - Use 'break' when hitting a non-digit (like 'a' in "42a").
         * - This ensures you return the 42 you've already collected instead of continuing or crashing.
         */
    }


    public static void main(String[] args) {
        StringToInteger sol = new StringToInteger();

        // Standard cases
        test("Basic Positive", "42", sol.myAtoi("42"), 42);
        test("Leading Spaces", "   -42", sol.myAtoi("   -42"), -42);

        // Mixed alpha-numeric
        test("Middle Garbage", "1337c0d3", sol.myAtoi("1337c0d3"), 1337);
        test("Start Garbage", "words and 987", sol.myAtoi("words and 987"), 0);

        // Edge cases: Signs and Zeros
        test("Double Sign", "+-12", sol.myAtoi("+-12"), 0);
        test("Leading Zeros", "00000-42a123", sol.myAtoi("00000-42a123"), 0);
        test("Space After Sign", "  +  42", sol.myAtoi("  +  42"), 0);

        // Overflow cases
        test("Large Positive", "91283472332", sol.myAtoi("91283472332"), Integer.MAX_VALUE);
        test("Large Negative", "-91283472332", sol.myAtoi("-91283472332"), Integer.MIN_VALUE);
        test("Beyond Long Capacity", "20000000000000000000", sol.myAtoi("20000000000000000000"), Integer.MAX_VALUE);
    }

    private static void test(String name, String input, int result, int expected) {
        System.out.printf("%-20s | Input: %-15s | Result: %-11d | %s%n", name, "'" + input + "'", result, (result == expected ? "✅ PASS" : "❌ FAIL"));
    }
}
