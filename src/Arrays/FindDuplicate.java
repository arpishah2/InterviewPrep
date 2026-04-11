package Arrays;

import java.util.HashSet;

/*
 * 
 * Find Duplicate: 
 * Given an array of integers a[] of size (n+1), and every number is between 1 and n. 
 * This means that there would be at least one duplicate integer. Find it. 
 * Note: There may be more than one duplicate, return any of them. A number may appear more than twice.

Easy: O(n2) time, O(1) space
Moderate: O(n) time, O(n) space.
Moderate-Hard: O(n) time, O(1) space
 * 
 */

public class FindDuplicate {

    public static void main(String args[]) {
        int[] input = {1, 2, 3, 6, 1, 6};
        FindDuplicate fd = new FindDuplicate();
        int dupl3 = fd.findDuplicateBest(input);
        System.out.println(dupl3);
        int dupl2 = fd.findDuplicateAvg(input);
        System.out.println(dupl2);
        int dupl1 = fd.findDuplicateBruteForce(input);
        System.out.println(dupl1);

    }

    /**
     * @param input - array in which duplicate is to be searched
     * @return the first duplicate element
     * @complexity O(n) time, O(1) space
     * @category use of the same array
     * @idea go through each location, see what is the value and place the value(say 5) at i=5.
     */
    public int findDuplicateBest(int[] input) {

        for (int i = 0; i < input.length; i++) {
            if (input[i] != i) {
                int val = input[i];
            }
        }

        return -1;
    }

    /**
     * @param input - array in which duplicate is to be searched
     * @return the first duplicate element
     * @complexity O(n) time, O(n) space
     * @category use of hashSet
     */
    public int findDuplicateAvg(int[] input) {
        if (input.length == 0)
            return -1;
        HashSet<Integer> hs = new HashSet<Integer>();
        for (int i = 0; i < input.length; i++) {
            if (!hs.contains(input[i]))
                hs.add(input[i]);
            else
                return input[i];
        }
        return -1;
    }

    /**
     * @param input - array in which duplicate is to be searched
     * @return the first duplicate element
     * @complexity O(n2)
     * @category bruteforce - loop through entire array
     */
    public int findDuplicateBruteForce(int[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] == input[j])
                    return input[i];
            }
        }
        return -1;
    }
}
