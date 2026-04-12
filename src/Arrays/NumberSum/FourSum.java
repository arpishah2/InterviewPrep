package Arrays.NumberSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * <p>
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * Example 2:
 * <p>
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 */
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {


        //Sort the array for 2 sum, also helps with disregarding duplicates
        //For 3Sum, we enumerate each value in a single loop, and use the two pointers pattern for the rest of the array
        //For K sum, we will have k - 2 nested loops (using recurssion) to enumerate all combinations of k - 2 values.

        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);

    }

    public List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();

        // If we have run out of numbers to add, return res.
        if (start == nums.length) {
            return res;
        }

        // There are k remaining values to add to the sum. The
        // average of these values is at least target / k.
        long average_value = target / k;

        //If the smallest number available is already bigger than the average needed, or the biggest number is smaller than the average, it is physically impossible to reach the target. We stop early to save time.
        if (nums[start] > average_value || average_value > nums[nums.length - 1]) {
            return res;
        }

        //Base case
        if (k == 2) {
            return twoSum(nums, target, start);
        }


        // Recurssion
        for (int i = start; i < nums.length; ++i) {
            if (i == start || nums[i - 1] != nums[i]) { // Skip duplicates

                // For kth, fix i
                // call sum on k-1, with i+1 start

                // If I use this number, I now need to find k-1 more numbers that sum up to target - nums[i].
                List<List<Integer>> kSumResult = kSum(nums, target - nums[i], i + 1, k - 1);

                for (List<Integer> subset : kSumResult) {

                    //When the recursive call returns a list of solutions, this block takes each solution and adds the "fixed" number nums[i] back to the front of it.
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(subset);
                }
            }
        }
        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, long target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = nums.length - 1;

        while (lo < hi) {
            int currSum = nums[lo] + nums[hi];
            if (currSum < target || (lo > start && nums[lo] == nums[lo - 1])) {
                ++lo;
            } else if (currSum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) {
                --hi;
            } else {
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        FourSum sol = new FourSum();

        System.out.println("==============================================");
        System.out.println("          RUNNING 4SUM TEST SUITE             ");
        System.out.println("==============================================");

        // Case 1: Standard case from example
        runTest(sol, 1, new int[]{1, 0, -1, 0, -2, 2}, 0, 3);

        // Case 2: All same values
        runTest(sol, 2, new int[]{2, 2, 2, 2, 2}, 8, 1);

        // Case 3: No valid quadruplets
        runTest(sol, 3, new int[]{1, 2, 3, 4}, 20, 0);

        // Case 4: Target with negative numbers
        runTest(sol, 4, new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0, 8);

        // Case 5: Large values (Overflow check)
        runTest(sol, 5, new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296, 0);

        System.out.println("==============================================");
    }

    private static void runTest(FourSum sol, int testNum, int[] nums, int target, int expectedCount) {
        List<List<Integer>> result = sol.fourSum(nums, target);
        boolean passed = (result.size() == expectedCount);

        String status = passed ? "✅ PASS" : "❌ FAIL";

        System.out.printf("TEST #%d | %s\n", testNum, status);
        System.out.println("Input Array : " + Arrays.toString(nums));
        System.out.println("Target      : " + target);
        System.out.println("Output      : " + result);
        System.out.println("Quadruplets : " + result.size() + " (Expected: " + expectedCount + ")");
        System.out.println("----------------------------------------------");
    }
}
