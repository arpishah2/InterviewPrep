package Arrays;

import java.util.Arrays;

/**
 * Given an integer array nums of length n and an integer target, find three integers at distinct indices in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * Example 2:
 * <p>
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 * Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
 * Constraints:
 * 3 <= nums.length <= 500
 * -1000 <= nums[i] <= 1000
 * -104 <= target <= 104
 */
public class ThreeSumClosest {

    /**
     * Solution for the 3Sum Closest problem using a sorted two-pointer approach.
     * <p>
     * Strategy:
     * 1. Sort the array to enable efficient searching with pointers.
     * 2. Iterate through each element as a fixed 'anchor'.
     * 3. Use two pointers (low and high) to find the pair that, combined with
     * the anchor, gets nearest to the target.
     * 4. Update the 'diff' whenever a closer sum is found.
     * 5. Short-circuit the loop if an exact match (diff == 0) is found.
     *
     * @param nums   An array of integers.
     * @param target The integer goal for the sum of three numbers.
     * @return The sum of the three integers that is closest to the target.
     * @complexity Time: O(n^2) due to the nested loop and two-pointer scan.
     * @complexity Space: O(log n) to O(n) depending on the sort implementation.
     */

    public int threeSumClosest(int[] nums, int target) {
        //Sum should be closest to the target

        Arrays.sort(nums);

        //Track the smallest absolute difference between the sum and the target.
        int diff = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length && diff != 0; i++) {

            int low = i + 1;
            int high = nums.length - 1;

            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];

                if (Math.abs(target - sum) < Math.abs(diff)) {
                    diff = target - sum;
                }

                if (sum < target) {
                    low++;
                } else {
                    high--;
                }

            }
        }

        //Return the value of the closest triplet, which is target - diff
        return target - diff;

    }

    public static void main(String[] args) {
        ThreeSumClosest sol = new ThreeSumClosest();

        System.out.println("==============================================");
        System.out.println("       RUNNING 3SUM CLOSEST TEST SUITE        ");
        System.out.println("==============================================");

        // Case 1: Standard case - Target is 1
        runTest(sol, 1, new int[]{-1, 2, 1, -4}, 1, 2);

        // Case 2: Exact match exists
        runTest(sol, 2, new int[]{0, 1, 2}, 3, 3);

        // Case 3: Target is a large positive number
        runTest(sol, 3, new int[]{1, 1, 1, 0}, 100, 3);

        // Case 4: Array with duplicate values
        runTest(sol, 5, new int[]{1, 1, 1, 1}, 0, 3);

        // Case 5: Your specific large-value array
        runTest(sol, 6, new int[]{-100, -70, -60, 110, 120, 130, 160}, 0, 0);

//        // Case 4: Far negative target (FIXED EXPECTED VALUE)
//        // Sums: -4 (dist 6) vs -3 (dist 7). -4 wins.
//        runTest(sol, 4, new int[]{-4, -1, 1, 2}, -10, -4);
//

        System.out.println("==============================================");
    }

    private static void runTest(ThreeSumClosest sol, int testNum, int[] nums, int target, int expected) {
        int result = sol.threeSumClosest(nums, target);
        boolean passed = (result == expected);

        String status = passed ? "✅ PASS" : "❌ FAIL";

        System.out.printf("TEST #%d | %s\n", testNum, status);
        System.out.println("Input Array : " + Arrays.toString(nums));
        System.out.println("Target      : " + target);
        System.out.println("Expected    : " + expected);
        System.out.println("Actual      : " + result);
        System.out.println("----------------------------------------------");
    }
}
