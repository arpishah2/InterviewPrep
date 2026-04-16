package Arrays;

import java.util.Arrays;

/**
 * Solution for the "Squares of a Sorted Array" problem.
 * <p>
 * This class provides an efficient way to square elements of a sorted array
 * and return them in sorted order using a two-pointer approach.
 * </p>
 *
 * <h2>Problem Description</h2>
 * Given an integer array {@code nums} sorted in non-decreasing order, return
 * an array of the squares of each number sorted in non-decreasing order.
 *
 * <h2>Example</h2>
 * <pre>
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * </pre>
 */
public class SquaresOfSortedArray {

    /**
     * Time Complexity: O(NlogN), where N is the length of A
     * Space complexity : O(N) or O(logN)
     */
    public int[] sortedSquaresUsingSorting(int[] nums) {

        int[] result = new int[nums.length];

        //compute square
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] * nums[i];
        }

        // sort resultant array
        Arrays.sort(result);
        return result;

    }

    /**
     * Time Complexity: O(N)
     * Space complexity : O(N)
     */
    public int[] sortedSquaresUsingTwoPointers(int[] nums) {

        int[] result = new int[nums.length];

        // Use two pointers to read the positive and negative parts of the array
        // one pointer right in the positive direction, and another left in the negative direction.

        int left = 0;
        int right = nums.length - 1;


        //compute square and store bigger value at larger index.
        //trick is array is sorted so highest negative values will be at the lowest index
        for (int i = nums.length - 1; i >= 0; i--) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                result[i] = nums[left] * nums[left];
                left++;
            } else {
                result[i] = nums[right] * nums[right];
                right--;
            }
        }

        return result;

    }

    /**
     * Main method to execute test cases with pretty-printed output.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SquaresOfSortedArray solution = new SquaresOfSortedArray();

        // Define test inputs
        int[][] testInputs = {
                {-4, -1, 0, 3, 10},
                {-7, -3, 2, 3, 11},
                {-5, -4, -3, -2, -1},
                {1, 2, 3, 4, 5}
        };

        System.out.println("===============================================================");
        System.out.printf("%-20s | %-20s | %-10s%n", "Input Array", "Result (2-Pointer)", "Status");
        System.out.println("---------------------------------------------------------------");

        for (int[] input : testInputs) {
            // Get expected by using the sorting method
            int[] expected = solution.sortedSquaresUsingSorting(input);
            // Get actual by using the optimized two-pointer method
            int[] actual = solution.sortedSquaresUsingTwoPointers(input);

            String inputStr = Arrays.toString(input);
            String resultStr = Arrays.toString(actual);
            String status = Arrays.equals(expected, actual) ? "PASS" : "FAIL";

            System.out.printf("%-20s | %-20s | %-10s%n", inputStr, resultStr, status);
        }
        System.out.println("===============================================================");
    }

}
