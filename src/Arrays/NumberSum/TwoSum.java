package Arrays.NumberSum;/*
 * 2SUM Problem: 
 * Given an array of integers a[] and a target sum T, find two indices (i and j) such that a[i] + a[j] adds up to T. 
 * Return both the indices
 * int[] TwoSum(int a[], int T)
 * Solution:Easy: O(n2)
		Easy-Moderate: O(n log n)
		Moderate: O(n), Can use extra space
 */

import java.util.Arrays;
import java.util.Hashtable;

public class TwoSum {
    int[] input;
    int target;

    public TwoSum() {
        input = new int[0];
        target = 0;
    }

    public TwoSum(int[] inp, int target) {
        this.input = inp;
        this.target = target;
    }

    public static void main(String[] args) {
        int[] input = {2, 0, 6, 7, 15};
        int target = 2;
        TwoSum tsp = new TwoSum(input, target);
        int res1[] = tsp.twoSumBest(tsp.input, tsp.target);
        int res2[] = tsp.twoSumAverage(tsp.input, tsp.target);
        int res3[] = tsp.twoSumBruteForce(tsp.input, tsp.target);

        System.out.println(res1[0] + " " + res1[1]);
        System.out.println(res2[0] + " " + res2[1]);
        System.out.println(res3[0] + " " + res3[1]);

    }

    /**
     * @param input1  - input array
     * @param target - target sum to be achieved
     * @return - array of int containing the index position of the numbers which sum upto target
     * @complexity - T: O(n), S:O(n)
     * @solution- use of hashtable
     */

    public int[] twoSumBest(int[] input1, int target) {

        int[] result = new int[2];
        result[0] = result[1] = -1;
        //store value,index
        Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();

        for (int i = 0; i < input1.length; i++) {
            int remains = target - input[i];
            if (ht.containsKey(remains)) {
                result[0] = ht.get(remains);
                result[1] = i;
                return result;
            } else {
                ht.put(input1[i], i);
            }
        }
        return result;

    }

    /**
     * @param input1  - input array
     * @param target - target sum to be achieved
     * @return - array of int containing the index position of the numbers which sum upto target
     * @complexity - O(n log n)
     * @solution- use of sorting
     */

    public int[] twoSumAverage(int[] input1, int target) {

        int[] input = new int[input1.length];
        System.arraycopy(input1, 0, input, 0, input1.length);

        int result[] = new int[2];
        result[0] = result[1] = -1;
        //check input length - no further processing required if no input
        if (input.length == 0) {
            System.out.println("No input");
            return result;
        }
        //sort the array
        Arrays.sort(input);

        //have 2 pointers one at end and one at beginning
        int start = 0;
        int end = input.length - 1;
        while (start < end) {
            int startVal = input[start];
            int endVal = input[end];
            int currSum = startVal + endVal;
            if (currSum == target) {
                result[0] = getArrayIndex(input1, startVal);
                result[1] = getArrayIndex(input1, endVal); //get the index vals from original array, cos sort modifies the array
                return result;
            } else if (currSum < target)
                start++;
            else
                end--;
        }
        return result;
    }

    /**
     * @param input  - input array
     * @param target - target sum to be achieved
     * @return - array of int containing the index position of the numbers which sum upto target
     * @complexity - O(n2)
     * @solution- loop over array - brute force
     *
     */

    public int[] twoSumBruteForce(int[] input, int target) {

        int result[] = new int[2];
        result[0] = result[1] = -1;

        if (input.length == 0) {
            System.out.println("No input");
            return result;
        }
        //loop over each element, and calculate sum with every other element
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                int sum = input[i] + input[j];
                if (sum == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * @param arr   - array in which search is to be performed
     * @param value value to search for
     * @return index of the value in arr
     * @about method return the index of a value in the array
     */

    public int getArrayIndex(int[] arr, int value) {
        int k = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                k = i;
                break;
            }
        }
        return k;
    }

}
