package StringProblems;

/*
 * Input : Alive is awesome
 * Output: emosewa si evilA
 *
 * Points:
 * 1. String class in java do not have reverse() method , StringBuilder class does have built in reverse() method.
 * 2. StringBuilder class do not have toCharArray() method , while String class does have toCharArray() method.
 */
public class ReverseString {


    public static void main(String[] args) {
        ReverseString rs = new ReverseString();
        rs.reverseString("Java is a great language");
        rs.reverseStringSwap("Java is a great language");
        System.out.println("\nJava is a great language -> " + rs.reverseStringRecurse("Java is a great language"));
    }

    /**
     * O(n) - traverse string form right to left
     */
    public void reverseString(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            result.append(str.charAt(i));
        }
        System.out.println(str + " -> " + result.toString());
    }

    /**
     * O(n) - swapping value at equal positions from right to left
     */
    public void reverseStringSwap(String str) {
        char[] input = str.toCharArray();
        for (int i = 0, j = input.length - 1; i <= j; i++, j--) {
            char temp = input[i];
            input[i] = input[j];
            input[j] = temp;
        }
        System.out.print(str + " -> ");
        for (char c : input) {
            System.out.print(c);
        }
    }

    /**
     * O(n) - recursion for reversal
     * Note: "hamburger".substring(4, 8) returns "urge"
     */
    public String reverseStringRecurse(String str) {
        if (str.length() == 1)
            return str;
        else {
            char current = str.charAt(0);
            String remaining = str.substring(1, str.length());
            return reverseStringRecurse(remaining) + current;
        }
    }

}
