package StringProblems;

import java.util.HashSet;
import java.util.Set;

class StringSols {

    String input;

    StringSols() {
        this.input = "";
    }

    StringSols(String input) {
        this.input = input;
    }

    public void maxSubStrofTwoChars() {

        //Find the maximum substring of 2 characters from a string
        //bbadccdhhe -> dccd

        String inp = "bbadccdhhe";
        int beginIndex = 0, endIndex = 0;
        int count = 0;
        int maxLen = 0;
        Set<Character> hs = new HashSet<Character>();


        for (int i = 0; i < inp.length(); i++) {

            char curr = inp.charAt(i);
			
			/*System.out.println("Begin: "+beginIndex+" end: "+endIndex);
			System.out.println("Max Len: "+maxLen +" Count: "+count);
			System.out.println("Input: "+inp+" index: "+i+" current val: "+curr);
			System.out.println("---");*/


            if (hs.size() == 0) {

                System.out.println("Loop 1: No element in HS");

                beginIndex = i;
                hs.add(curr);
                count++;

                if (count > maxLen) {
                    maxLen = count;
                    System.out.println("String is " + inp.substring(beginIndex, endIndex + 1));
                }
            } else if (!hs.contains(curr) && hs.size() == 1) {

                System.out.println("Loop 2: One element in HS");

                endIndex = i;
                hs.add(curr);
                count++;

                if (count > maxLen) {
                    maxLen = count;
                    System.out.println("String is " + inp.substring(beginIndex, endIndex + 1));
                }
            } else if (!hs.contains(curr) && hs.size() == 2) {

                System.out.println("Loop 3: Already 2 element in HS");

                if (count > maxLen) {
                    maxLen = count;
                    System.out.println("String is " + inp.substring(beginIndex, endIndex + 1));
                }


                //remove the old value from hs
                //endIndex contains the curr value
                //beginIndex contains old value
                System.out.println(beginIndex + " --- " + endIndex);
                hs.remove(inp.charAt(beginIndex));
                hs.add(inp.charAt(i));
                beginIndex = endIndex;
                endIndex = i;
                count = 2;
                System.out.println(beginIndex + " --- " + endIndex);

            } else if (hs.contains(curr)) {

                System.out.println("Loop 4: element in HS");

                if (inp.charAt(i) != inp.charAt(endIndex)) {
                    endIndex = i;
                }
                count++;

                if (count > maxLen) {
                    maxLen = count;
                    System.out.println("String is " + inp.substring(beginIndex, endIndex));
                }


            }

            System.out.println("Input: " + inp + " index: " + i + " current val: " + curr);
            System.out.println("Begin: " + beginIndex + " end: " + endIndex);
            System.out.println("Max Len: " + maxLen + " Count: " + count);
            System.out.println("-------------------------------------------");

        }

    }

}

public class StringProb {


    public static void main(String args[]) {

        StringSols obj = new StringSols();
        obj.maxSubStrofTwoChars();


    }


}

