package StringProblems;

import java.util.NoSuchElementException;

/*
 * Write a function to implement parseInt functionality from  Integer class in java
 *  i.e. a function that takes a string as input and converts it into an integer
 */
public class ParseIntImpl {

    public static void main(String args[]) {
        ParseIntImpl pi = new ParseIntImpl();
        System.out.println("ParseInt(200) = " + pi.parseIntFromString("200"));
        System.out.println("ParseInt() = " + pi.parseIntFromString(""));
        System.out.println("ParseInt(null) = " + pi.parseIntFromString(null));
        System.out.println("ParseInt(0579) = " + pi.parseIntFromString("0579"));
        System.out.println("ParseInt(0050790) = " + pi.parseIntFromString("0050790"));
        //System.out.println("ParseInt(2*00) = "+pi.parseIntFromString("2*00"));

    }

    /**
     * @param s - String to be converted to int
     * @return - int value out of string
     * @implementation - find that the input you are entering should not exceed the ascii value 57 and should not be less than 48,
     * if so, multiply the number with 10 and add the (number - 48) value to it.
     */
    public int parseIntFromString(String s) {
        if (s == null)
            return 0;
        char[] input = s.toCharArray();
        int sum = 0;                    //for 200, we have to multiply by 100 = 10^2

        for (char c : input) {
            int asciival = (int) c;                                //ASCII val of current char
            if (asciival >= 48 && asciival <= 57)                    //Note 48 is ASCII value of 0
                sum = sum * 10 + (asciival - 48);
            else
                throw new NoSuchElementException("Invalid string " + s + " !! Cannot be converted to int!!");
        }
        return sum;
    }

}
