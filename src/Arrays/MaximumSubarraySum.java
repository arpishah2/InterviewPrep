package Arrays;

import java.util.Arrays;

/**
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * <p>
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * <p>
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 */
public class MaximumSubarraySum {

    /**
     * Approach: Dynamic Programming, Kadane's Algorithm
     * Figure out what part of array is worth keepping - any subarray whose sum is positive is worth keeping
     * For each number, add it to the currentSubarray being built. If currentSubarray becomes negative, it isn't worth keeping, so throw it away.
     * Update maxSubarray every time we find a new maximum.
     */
    public int maxSubArray(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        int max_sum = nums[0];
        int curr_sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Kadane's Algorithm - any subarray whose sum is positive is worth keeping.
            // If curr_sum becomes negative, we know it isn't worth keeping, so throw it away.
            curr_sum = Math.max(nums[i], curr_sum + nums[i]);
            max_sum = Math.max(max_sum, curr_sum);

        }
        return max_sum;

    }

    public static void runTest(int[] nums) {
        MaximumSubarraySum obj = new MaximumSubarraySum();

        System.out.println("========================================");
        System.out.println("Input Array: " + Arrays.toString(nums));
        System.out.println("----------------------------------------");

        int curr_sum = nums[0];
        int max_sum = nums[0];

        System.out.printf("%-5s %-8s %-12s %-12s\n", "Index", "Value", "Curr Sum", "Max Sum");
        System.out.println("----------------------------------------");

        System.out.printf("%-5d %-8d %-12d %-12d\n", 0, nums[0], curr_sum, max_sum);

        for (int i = 1; i < nums.length; i++) {
            int prev_curr = curr_sum;

            curr_sum = Math.max(nums[i], curr_sum + nums[i]);
            max_sum = Math.max(max_sum, curr_sum);

            System.out.printf(
                    "%-5d %-8d %-12d %-12d  (%s)\n",
                    i,
                    nums[i],
                    curr_sum,
                    max_sum,
                    (nums[i] > prev_curr + nums[i]) ? "restart" : "extend"
            );
        }

        int result = obj.maxSubArray(nums);

        System.out.println("----------------------------------------");
        System.out.println("Final Max Subarray Sum = " + result);
        System.out.println("========================================\n");
    }

    /**
     * PSVM (main method)
     */
    public static void main(String[] args) {

        // Test Case 1
        runTest(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});

        // Test Case 2
        runTest(new int[]{1});

        // Test Case 3
        runTest(new int[]{5, 4, -1, 7, 8});

        // Extra tricky test
        runTest(new int[]{-3, -2, -1, -5}); // all negatives
    }
}
