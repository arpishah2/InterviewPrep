package Arrays.NumberSum;

import java.util.Arrays;

/**
 * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
 * Return the running sum of nums.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [1,3,6,10]
 * Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
 * <p>
 * Example 2:
 * Input: nums = [1,1,1,1,1]
 * Output: [1,2,3,4,5]
 * Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].
 * <p>
 * Example 3:
 * Input: nums = [3,1,2,10,1]
 * Output: [3,4,6,16,17]
 */
public class RunningSumOfArray {

    public int[] runningSum(int[] nums) {
        if (nums.length == 0) {
            return null;
        }

        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }

        return nums;
    }

    /**
     * Pretty print with step-by-step visualization
     */
    public static void runTest(int[] input) {
        RunningSumOfArray obj = new RunningSumOfArray();

        int[] nums = Arrays.copyOf(input, input.length); // avoid modifying original

        System.out.println("========================================");
        System.out.println("Original Array: " + Arrays.toString(input));
        System.out.println("----------------------------------------");

        if (nums.length == 0) {
            System.out.println("Empty array → Output: null");
            System.out.println("========================================\n");
            return;
        }

        System.out.printf("%-5s %-10s %-20s\n", "Index", "Value", "Running Array State");
        System.out.println("----------------------------------------");

        System.out.printf("%-5d %-10d %-20s\n", 0, nums[0], Arrays.toString(nums));

        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i];
            System.out.printf(
                    "%-5d %-10d %-20s\n",
                    i,
                    nums[i],
                    Arrays.toString(nums)
            );
        }

        int[] result = obj.runningSum(Arrays.copyOf(input, input.length));

        System.out.println("----------------------------------------");
        System.out.println("Final Running Sum: " + Arrays.toString(result));
        System.out.println("========================================\n");
    }

    /**
     * PSVM
     */
    public static void main(String[] args) {

        // Test Case 1
        runTest(new int[]{1, 2, 3, 4});

        // Test Case 2
        runTest(new int[]{1, 1, 1, 1, 1});

        // Test Case 3
        runTest(new int[]{3, -2, 5, 1});

        // Edge Case
        runTest(new int[]{});

        // Single element
        runTest(new int[]{10});
    }
}
