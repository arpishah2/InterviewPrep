package Arrays;

import java.util.Arrays;

/**
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation:
 * n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
 * <p>
 * Example 2:
 * Input: nums = [0,1]
 * Output: 2
 * Explanation:
 * n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
 * <p>
 * Example 3:
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation:
 * <p>
 * n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
 */
public class MissingNumber {

    /**
     * Gauss' Formula
     * Sum of all nos = n * (n+1)/2
     * <p>
     * Time complexity : O(n)
     * Space complexity : O(1)
     */
    public int missingNumber(int[] nums) {

        int sunOfAllNos = nums.length * (nums.length + 1) / 2;

        int sumOfCurrentNos = 0;
        for (int i = 0; i < nums.length; i++) {
            sumOfCurrentNos += nums[i];
        }
        return sunOfAllNos - sumOfCurrentNos;

        //Other approaches can be use of hashSet to find missing no, Sorting and searching for missing no
    }

    public static void main(String[] args) {
        MissingNumber solution = new MissingNumber();

        // Example 1
        int[] nums1 = {3, 0, 1};
        System.out.println("Test Case 1: " + Arrays.toString(nums1));
        System.out.println("Expected: 2, Result: " + solution.missingNumber(nums1));
        System.out.println();

        // Example 2
        int[] nums2 = {0, 1};
        System.out.println("Test Case 2: " + Arrays.toString(nums2));
        System.out.println("Expected: 2, Result: " + solution.missingNumber(nums2));
        System.out.println();

        // Example 3
        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println("Test Case 3: " + Arrays.toString(nums3));
        System.out.println("Expected: 8, Result: " + solution.missingNumber(nums3));
    }

}
