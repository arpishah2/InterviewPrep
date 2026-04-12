package Arrays;


import java.util.Arrays;

/**
 * 🚀 PROBLEM: Two Sum II - Input Array Is Sorted
 * <p>
 * Given a 1-indexed array of integers 'numbers' that is already sorted in
 * non-decreasing order, find two numbers such that they add up to a specific
 * target number.
 * <p>
 * 🛠️ RULES:
 * 1. The result should be an integer array [index1, index2] of length 2.
 * 2. Indices are 1-indexed (1 <= index1 < index2 <= numbers.length).
 * 3. Exactly one solution exists.
 * 4. You may not use the same element twice.
 * 5. MUST use only constant extra space O(1).
 * <p>
 * 📝 EXAMPLES:
 * - Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2] (2 + 7 = 9)
 * <p>
 * - Input: numbers = [2,3,4], target = 6
 * Output: [1,3] (2 + 4 = 6)
 * <p>
 * - Input: numbers = [-1,0], target = -1
 * Output: [1,2] (-1 + 0 = -1)
 * <p>
 * ⚙️ CONSTRAINTS:
 * - 2 <= numbers.length <= 3 * 10^4
 * - -1000 <= numbers[i] <= 1000
 * - numbers is sorted in non-decreasing order.
 * - -1000 <= target <= 1000
 */

public class TwoSumSortedArrayProblem {

    /**
     * ⚡ ALGORITHM: Two-Pointer Technique (Two Sum II)
     * <p>
     * We leverage the fact that the input array is already SORTED to narrow
     * down the search space from both ends simultaneously.
     * <p>
     * 1. INITIALIZE: Start with two pointers:
     * - 'left' pointing to the first element (index 0).
     * - 'right' pointing to the last element (index n-1).
     * <p>
     * 2. COMPARE: Calculate the sum of elements at these two pointers.
     * - IF sum == target: We found the unique solution. Return [left + 1, right + 1].
     * - IF sum < target: Since the array is sorted, we need a larger value.
     * Increment the 'left' pointer to move toward larger numbers.
     * - IF sum > target: We need a smaller value.
     * Decrement the 'right' pointer to move toward smaller numbers.
     * <p>
     * 3. REPEAT: Continue moving the indices and comparing until the sum
     * matches the target.
     * <p>
     * -------------------------------------------------------------------------
     * 📊 COMPLEXITY ANALYSIS:
     * <p>
     * TIME COMPLEXITY: O(n)
     * - In the worst case, each element in the array is visited at most once
     * as the two pointers move toward each other.
     * <p>
     * SPACE COMPLEXITY: O(1)
     * - We only use a fixed amount of extra space to store the two pointers
     * (indices) and the current sum, regardless of the input size.
     * -------------------------------------------------------------------------
     */

    public int[] twoSumSortedInput(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {

            int first = numbers[left];
            int last = numbers[right];
            int sum = first + last;
            if (sum == target) {
                // The problem specifically states that the input is a "1-indexed array."
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        TwoSumSortedArrayProblem sol = new TwoSumSortedArrayProblem();

        // Example 1: Basic positive numbers
        test("Basic Example", new int[]{2, 7, 11, 15}, 9, new int[]{1, 2}, sol);

        // Example 2: Target in the middle
        test("Target Mid", new int[]{2, 3, 4}, 6, new int[]{1, 3}, sol);

        // Example 3: Negative numbers
        test("Negatives", new int[]{-1, 0}, -1, new int[]{1, 2}, sol);

        // Example 4: Large gap
        test("Large Gap", new int[]{5, 25, 75, 100}, 105, new int[]{1, 4}, sol);

        // Example 5: Numbers are the same
        test("Same Values", new int[]{0, 0, 3, 4}, 0, new int[]{1, 2}, sol);
    }

    private static void test(String name, int[] numbers, int target, int[] expected, TwoSumSortedArrayProblem sol) {
        int[] result = sol.twoSumSortedInput(numbers, target);
        boolean passed = Arrays.equals(result, expected);

        System.out.printf("%-15s | Input: %-15s | Target: %-3d | Result: %-8s | %s%n",
                name, Arrays.toString(numbers), target, Arrays.toString(result),
                (passed ? "✅ PASS" : "❌ FAIL"));
    }

}
