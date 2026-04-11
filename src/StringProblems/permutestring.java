package StringProblems;

import java.util.ArrayList;

/*
 * Problem:
 * A permutation, also called an “arrangement number” or “order,” is a rearrangement of the elements of an ordered list S into a one-to-one correspondence with S itself.
 * A string of length n has n! permutation.
 *
 * Below are the permutations of string ABC.
 * ABC ACB BAC BCA CBA CAB
 *
 * Solution:
 * Use recursion :-
 * 1. Try each of the letters in turn as the first letter and then find all the permutations of the remaining letters using a recursive call.
 * 2. The base case is when the input is an empty string the only permutation is the empty string.
 */


public class permutestring {


    public static void main(String args[]) {
        String input = new String("123");
        permutestring ps = new permutestring();

        //Better method - recursive
        ps.permuteRec2("", input);

		/*
		//Naive method
		ArrayList<String> res = ps.permuteRec1(input);
		System.out.println("Size: "+res.size());
		for(String s: res)
			System.out.println(s);
		*/
    }

    /**
     * Recursive Better -
     * 1. Try each of the letters in turn as the first letter and then find all the permutations of the remaining letters using a recursive call.
     * 2. The base case is when the input is an empty string the only permutation is the empty string.
     *
     * @param input - string to be permuted
     * @return
     */
    public void permuteRec2(String prefix, String input) {
        if (input.isEmpty()) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < input.length(); i++) {
                permuteRec2(prefix + input.charAt(i), input.substring(0, i) + input.substring(i + 1));
            }
        }
    }

    /**
     * Naive Recursive -
     * 1. Try each of the letters in turn as the first letter and then find all the permutations of the remaining letters using a recursive call.
     * 2. The base case is when the input is an empty string the only permutation is the empty string.
     *
     * @param input - string to be permuted
     * @return
     */
    public ArrayList<String> permuteRec1(String input) {
        ArrayList<String> prevPerm = new ArrayList<String>();
        ArrayList<String> perm = new ArrayList<String>();
        if (input.length() == 0) {
            perm.add("");
            return perm;
        }
        for (int i = 0; i < input.length(); i++) {
            char prefix = input.charAt(i);
            String remain = input.substring(0, i) + input.substring(i + 1);
            prevPerm = permuteRec1(remain);
            for (int j = 0; j < prevPerm.size(); j++) {
                String val = prefix + prevPerm.get(j);
                perm.add(val);
            }
        }
        return perm;
    }

}
