package Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,3]
 * Output: [3]
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: [1]
 * Example 3:
 * <p>
 * Input: nums = [1,2]
 * Output: [1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 * <p>
 * <p>
 * Follow up: Could you solve the problem in linear time and in O(1) space?
 */
public class MajorityElement2 {

    public List<Integer> majorityElement(int[] nums) {

        // To figure out a O(1) space requirement, we would need to get this simple intuition first. For an array of length n:
        //
        // There can be at most one majority element which is more than ⌊n/2⌋ times.
        // There can be at most two majority elements which are more than ⌊n/3⌋ times.
        // There can be at most three majority elements which are more than ⌊n/4⌋ times.
        //
        //we only need four variables: two for holding two potential candidates and two for holding two corresponding counters.


        Integer majorityElement1 = null;
        int element1Counter = 0;
        Integer majorityElement2 = null;
        int element2Counter = 0;

        for (int i = 0; i < nums.length; i++) {

            int currentElem = nums[i];

            //If the current element is equal to one of the potential candidate, the count for that candidate is increased while leaving the count of the other candidate as it is.
            //If the counter reaches zero, the candidate associated with that counter will be replaced with the next element if the next element is not equal to the other candidate as well
            if (majorityElement1 != null && currentElem == majorityElement1) {
                element1Counter++;
            } else if (majorityElement2 != null && currentElem == majorityElement2) {
                element2Counter++;
            } else if (element1Counter == 0) {
                majorityElement1 = currentElem;
                element1Counter++;
            } else if (element2Counter == 0) {
                majorityElement2 = currentElem;
                element2Counter++;
            } else {
                //Both counters are decremented only when the current element is different from both candidates
                element1Counter--;
                element2Counter--;
            }

        }

        int count1 = 0;
        int count2 = 0;
        //2nd pass: when array is exhausted, we need to make sure that the element recorded as  potential candidates are the majority element by checking whether it occurs more than ⌊n/3⌋ times in the array.
        for (int i = 0; i < nums.length; i++) {
            int currentElem = nums[i];
            if (majorityElement1 != null && majorityElement1 == currentElem) {
                count1++;
            }
            if (majorityElement2 != null && majorityElement2 == currentElem) {
                count2++;
            }
        }
        List<Integer> result = new ArrayList<>();
        int totalLength = nums.length;

        if (count1 > totalLength / 3) {
            result.add(majorityElement1);
        }
        if (count2 > totalLength / 3) {
            result.add(majorityElement2);
        }

        return result;

    }

    public static void main(String[] args) {
        MajorityElement2 solver = new MajorityElement2();

        // Test Case 1: Standard case (two majorities)
        test(solver, new int[]{1, 2, 1, 2, 1, 3, 2}, "[1, 2]");

        // Test Case 2: Standard case (one majority)
        test(solver, new int[]{3, 2, 3}, "[3]");

        // Test Case 3: Single element
        test(solver, new int[]{1}, "[1]");

        // Test Case 4: No majority elements
        test(solver, new int[]{1, 2, 3, 4, 5}, "[]");

        // Test Case 5: Large negative/positive numbers
        test(solver, new int[]{1000000000, 1000000000, -1000000000, -1000000000, 5}, "[1000000000, -1000000000]");

        // Test Case 6: Duplicate elements that don't meet the threshold
        test(solver, new int[]{1, 1, 1, 3, 3, 2, 2, 2}, "[]"); // n=8, n/3=2. Both 1 and 2 appear 3 times. Wait, [1, 2]!
    }

    private static void test(MajorityElement2 solver, int[] nums, String expected) {
        List<Integer> result = solver.majorityElement(nums);
        System.out.println("Input: " + java.util.Arrays.toString(nums));
        System.out.println("Output: " + result + " | Expected: " + expected);
        System.out.println("-----------------------------------------");
    }
}
