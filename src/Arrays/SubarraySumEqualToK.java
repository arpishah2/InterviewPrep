package Arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Solution for the "Subarray Sum Equals K" problem.
 * <p>
 * This class provides a method to find the total number of continuous subarrays
 * whose sum equals a given value {@code k}.
 * </p>
 *
 * <h2>Problem Description</h2>
 * Given an array of integers {@code nums} and an integer {@code k}, return the
 * total number of subarrays whose sum equals to {@code k}.
 *
 * <p>A subarray is a contiguous non-empty sequence of elements within an array.</p>
 *
 * <h2>Example</h2>
 * <pre>
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * </pre>
 *
 * <h2>Constraints</h2>
 * <ul>
 *   <li>1 &le; nums.length &le; 2 * 10<sup>4</sup></li>
 *   <li>-1000 &le; nums[i] &le; 1000</li>
 *   <li>-10<sup>7</sup> &le; k &le; 10<sup>7</sup></li>
 * </ul>
 *
 * @author YourName
 */
public class SubarraySumEqualToK {

    /**
     * Finds the number of subarrays that sum up to k.
     * <p>
     * Implementation typically uses a Hashmap to store the cumulative sum
     * frequencies to achieve O(n) time complexity.
     * </p>
     *
     * @param nums an array of integers
     * @param k    the target sum
     * @return the total number of subarrays whose sum equals {@code k}
     */
    public int subarraySumUsingHashMap(int[] nums, int k) {

        if (nums.length == 0) {
            return 0;
        }

        // Idea: if the cumulative sum up to two indices, say i and j is at a difference of k i.e. if sum[i]−sum[j]=k, the sum of elements lying between indices i and j is k.


        // Hashmap (sum no.ofoccurrencesofsum) stores the cumulative sum up to all the indices possible along with the number of times the same sum occurs.
        Map<Integer, Integer> sumOccurenceMap = new HashMap<>();
        sumOccurenceMap.put(0, 1);

        //Traverse array nums and keep on finding the cumulative sum.
        int sum = 0;
        int countOfSubArraySum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            // determine the number of times the sum sum−k has occurred already, since it will determine the number of times a subarray with sum k has occurred up to the current index.
            // Increment the count by the same amount.
            if (sumOccurenceMap.containsKey(sum - k)) {
                countOfSubArraySum += sumOccurenceMap.get(sum - k);
            }

            // Every time there is a new sum, make a new entry in the hashmap corresponding to that sum.
            // If the same sum occurs again, we increment the count corresponding to that sum in the hashmap.
            sumOccurenceMap.put(sum, sumOccurenceMap.getOrDefault(sum, 0) + 1);

        }

        //Return the count gives the required result
        return countOfSubArraySum;
    }

    /**
     * Main method to execute test cases.
     */
    public static void main(String[] args) {
        SubarraySumEqualToK solution = new SubarraySumEqualToK();

        // Test Case 1: Standard case
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        System.out.println("Test Case 1: " + (solution.subarraySumUsingHashMap(nums1, k1) == 2 ? "PASSED" : "FAILED"));

        // Test Case 2: Array with different numbers
        int[] nums2 = {1, 2, 3};
        int k2 = 3;
        System.out.println("Test Case 2: " + (solution.subarraySumUsingHashMap(nums2, k2) == 2 ? "PASSED" : "FAILED")); // [1,2] and [3]

        // Test Case 3: Negative numbers
        int[] nums3 = {1, -1, 0};
        int k3 = 0;
        System.out.println("Test Case 3: " + (solution.subarraySumUsingHashMap(nums3, k3) == 3 ? "PASSED" : "FAILED")); // [1,-1], [0], [1,-1,0]

        // Test Case 4: Single element match
        int[] nums4 = {5};
        int k4 = 5;
        System.out.println("Test Case 4: " + (solution.subarraySumUsingHashMap(nums4, k4) == 1 ? "PASSED" : "FAILED"));

        // Test Case 5: No match
        int[] nums5 = {1, 2, 3};
        int k5 = 7;
        System.out.println("Test Case 5: " + (solution.subarraySumUsingHashMap(nums5, k5) == 0 ? "PASSED" : "FAILED"));
    }
}
