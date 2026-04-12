package Arrays;


import java.util.*;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 * Example 2:
 * <p>
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * Example 3:
 * <p>
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class ThreeSumProblem {

    int[] input;
    int target;

    public ThreeSumProblem() {
        input = new int[0];
        target = 0;
    }

    public ThreeSumProblem(int[] inp, int target) {
        this.input = inp;
        this.target = target;
    }


    /**
     * Finds all unique triplets in the array that sum up to zero.
     *
     * @param nums - input array of integers
     * @return - a list of lists containing unique triplets that sum to zero
     * @complexity - O(n^2) time, O(n) space for the HashSet
     * @solution - Sorting followed by a Two-Pointer approach for each element
     */
    public List<List<Integer>> threeSum(int[] nums) {

        /**
         * Implementation Strategy:
         *
         * The algorithm uses a sorted two-pointer approach to find all unique triplets
         * that sum to zero in O(n^2) time.
         *
         * Main Function logic:
         * 1. Sort the input array 'nums' to enable the two-pointer technique.
         * 2. Iterate through the array. If the current value is greater than zero,
         *    break the loop immediately since remaining values cannot sum to zero.
         * 3. Skip the current value if it is the same as the previous one to avoid
         *    duplicate triplets.
         * 4. For valid indices, call the twoSumII helper function.
         *
         * twoSumII Function logic:
         * 1. Initialize two pointers: 'lo' at i + 1 and 'hi' at the last index.
         * 2. While 'lo' is less than 'hi':
         *    - If the triplet sum is less than zero, increment 'lo' to increase the sum.
         *    - If the triplet sum is greater than zero, decrement 'hi' to decrease the sum.
         *    - If the sum is exactly zero, add the triplet to the result, then move
         *      both 'lo' and 'hi'.
         * 3. After finding a match, increment 'lo' as long as the next value is the
         *    same as the current one to ensure all results are unique.
         */


        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();

        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            twoSumII(nums, i, res);
        }

        return new ArrayList<>(res);

    }

    /**
     * Helper method using the Two-Pointer technique to find pairs that sum with nums[i] to zero.
     *
     * @param nums   - the sorted input array
     * @param i      - the index of the first element of the potential triplet
     * @param result - the Set used to store unique triplets found
     */
    void twoSumII(int[] nums, int i, Set<List<Integer>> result) {

        int lo = i + 1;
        int high = nums.length - 1;

        while (lo < high && high < nums.length) {
            int sum = nums[i] + nums[lo] + nums[high];

            if (sum < 0) {
                lo++;
            } else if (sum > 0) {
                high--;
            } else {
                result.add(Arrays.asList(nums[i], nums[lo], nums[high]));
                lo++;
                high--;
            }
        }
    }

    /**
     * @param input  - input array
     * @param target - target sum to be achieved
     * @return - array of int containing the index position of the numbers which sum upto target
     * @complexity - O(n3)
     * @solution- loop over array - brute force
     *
     */
    public int[] threeSumBruteForce(int[] input, int target) {
        int result[] = new int[3];
        result[0] = result[1] = result[2] = -1;
        if (input.length == 0) {
            System.out.println("No input");
            return result;
        }
        //loop over each element, and calculate sum with every other element
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                for (int k = j + 1; k < input.length; k++) {
                    int sum = input[i] + input[j] + input[k];
                    if (sum == target) {
                        result[0] = i;
                        result[1] = j;
                        result[2] = k;
                        return result;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = {2, 0, 6, 7, 15};
        int target = 17;
        ThreeSumProblem tsp = new ThreeSumProblem(input, target);
        int res0[] = tsp.threeSumBruteForce(tsp.input, tsp.target);
        System.out.println(res0[0] + " " + res0[1] + " " + res0[2]);


        //---

        ThreeSumProblem sol = new ThreeSumProblem();
        runTest(sol, 1, new int[]{-1, 0, 1, 2, -1, -4}, 2);
        runTest(sol, 2, new int[]{-100, -70, -60, 110, 120, 130, 160}, 2);
        runTest(sol, 3, new int[]{0, 0, 0, 0}, 1);
        runTest(sol, 4, new int[]{1, 2, 3}, 0);
        runTest(sol, 5, new int[]{}, 0);

    }

    private static void runTest(ThreeSumProblem sol, int testNum, int[] input, int expectedSize) {
        List<List<Integer>> result = sol.threeSum(input);
        boolean passed = result.size() == expectedSize;

        System.out.println("----------------------------------------");
        System.out.println("TEST CASE " + testNum + ": " + (passed ? "PASS" : "FAIL"));
        System.out.println("Input:  " + Arrays.toString(input));
        System.out.println("Output: " + result);
        System.out.println("----------------------------------------");
    }

}
