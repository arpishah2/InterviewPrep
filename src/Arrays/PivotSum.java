package Arrays;

import java.util.Arrays;

/**
 * Given an array of integers nums, calculate the pivot index of this array.
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
 * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
 * Return the leftmost pivot index. If no such index exists, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation:
 * The pivot index is 3.
 * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
 * Right sum = nums[4] + nums[5] = 5 + 6 = 11
 * <p>
 * Example 2:
 * Input: nums = [1,2,3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 * <p>
 * Example 3:
 * Input: nums = [2,1,-1]
 * Output: 0
 * Explanation:
 * The pivot index is 0.
 * Left sum = 0 (no elements to the left of index 0)
 * Right sum = nums[1] + nums[2] = 1 + -1 = 0
 */
public class PivotSum {


    /**
     * If we knew S as the sum of the numbers, and we are at index i.
     * If we knew the sum of numbers leftsum that are to the left of index i, then the other sum to the right of the index would just be S - nums[i] - leftsum.
     */
    public int pivotIndex(int[] nums) {

        if (nums.length == 0) {
            return -1;
        }

        // [1 ,7 ,3 ,6 ,5 ,6] = 28
        //sum.0 1. 8. 11 17 22 28

        // nums[i] + leftsum[i] = sum - leftsum[i]
        // leftsum[i] = sum - lsum[i] - nums[i]

        int totalSum = 0;
        for (int x : nums) {
            totalSum += x;
        }

        int lsum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] + lsum == totalSum - lsum) {
                return i;
            }
            lsum += nums[i];
        }

        return -1;

    }


    /**
     * Pretty print with step-by-step visualization
     */
    public static void runTest(int[] nums) {
        PivotSum obj = new PivotSum();

        System.out.println("========================================");
        System.out.println("Input Array: " + Arrays.toString(nums));
        System.out.println("----------------------------------------");

        if (nums.length == 0) {
            System.out.println("Empty array → Pivot Index = -1");
            System.out.println("========================================\n");
            return;
        }

        int totalSum = 0;
        for (int x : nums) totalSum += x;

        System.out.println("Total Sum = " + totalSum);
        System.out.println("----------------------------------------");

        System.out.printf("%-5s %-8s %-10s %-10s %-10s\n",
                "Idx", "Value", "LeftSum", "RightSum", "Match?");
        System.out.println("----------------------------------------");

        int leftSum = 0;

        for (int i = 0; i < nums.length; i++) {
            int rightSum = totalSum - leftSum - nums[i];

            System.out.printf("%-5d %-8d %-10d %-10d %-10s\n",
                    i,
                    nums[i],
                    leftSum,
                    rightSum,
                    (leftSum == rightSum ? "YES ✅" : "NO ❌")
            );

            leftSum += nums[i];
        }

        int result = obj.pivotIndex(nums);

        System.out.println("----------------------------------------");
        System.out.println("Pivot Index = " + result);
        System.out.println("========================================\n");
    }

    /**
     * PSVM
     */
    public static void main(String[] args) {

        // Test Case 1 (classic)
        runTest(new int[]{1, 7, 3, 6, 5, 6});

        // Test Case 2
        runTest(new int[]{1, 2, 3});

        // Test Case 3
        runTest(new int[]{2, 1, -1});

        // Edge Case: single element
        runTest(new int[]{5});

        // Edge Case: all zeros
        runTest(new int[]{0, 0, 0, 0});

        // Edge Case: empty
        runTest(new int[]{});

        // Extra tricky
        runTest(new int[]{-1, -1, -1, 0, 1, 1});
    }

}
