package StringProblems;

/*
 * A palindrome is a word, phrase, number, or other sequence of characters which reads the same backward or forward
 * eg. aibohphobia, racecar
 * Note: converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters. Alphanumeric characters include letters and numbers.


 */
public class Palindrome {

    public static void main(String args[]) {
        Palindrome p = new Palindrome();
        String input[] = {"", "a", "ab", "roar", "borrow", "racecar", "aibohphobia"};
        for (String s : input) {
            System.out.println(s + " is StringProblems.Palindrome? " + p.checkPalindromeRecurse(s));
            System.out.println(s + " is StringProblems.Palindrome? " + p.checkPalindromeIter(s));
            System.out.println(s + " is StringProblems.Palindrome? " + p.checkPalindromeRev(s));
        }

        String[] testCases = {
                "A man, a plan, a canal: Panama",
                "race a car",
                " "
        };
        for (String testCase : testCases) {
            boolean result = p.isPalindromeTwoPointers(testCase);
            System.out.println("Input: \"" + testCase + "\"");
            System.out.println("Output: " + result);
            System.out.println("---");
        }
    }


    public boolean isPalindromeTwoPointers(String s) {

        if (s.length() == 0) {
            return false;
        }

        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {

            //ignore non-alphanumeric characters
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }

            //comapre lower case version of character
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
        }
        return true;

    }

    /**
     * O(n) - recurse through string
     *
     * @param str: Input string
     * @return true: is a StringProblems.Palindrome, false: not a StringProblems.Palindrome
     */
    public boolean checkPalindromeRecurse(String str) {
        if (str.length() <= 1)
            return true;
        else {
            if (str.charAt(0) == str.charAt(str.length() - 1)) {
                String remaining = str.substring(1, str.length() - 1);
                return checkPalindromeRecurse(remaining);
            }
            return false;
        }
    }

    /**
     * O(n) - loop through string using 2 pointers from left and right
     *
     * @param str: Input string
     * @return true: is a StringProblems.Palindrome, false: not a StringProblems.Palindrome
     */
    public boolean checkPalindromeIter(String str) {
        boolean isBoolean = true;
        char[] input = str.toCharArray();
        for (int i = 0, j = input.length - 1; i <= j; i++, j--) {
            if (input[i] != input[j])
                return false;
        }
        return isBoolean;
    }

    /**
     * O(n) - revrse string and compare reverse with original
     *
     * @param str: Input string
     * @return true: is a StringProblems.Palindrome, false: not a StringProblems.Palindrome
     * @note: String does not have reverse method
     */
    public boolean checkPalindromeRev(String str) {
        StringBuilder st = new StringBuilder(str);
        StringBuilder reverse = st.reverse();
        if (str.equals(reverse.toString())) {
            return true;
        } else return false;
    }
}
