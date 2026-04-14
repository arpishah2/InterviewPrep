package Arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LC Easy
 * Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 * The input is generated such that a majority element will exist in the array.
 */
public class MajorityElement {


    /**
     *
     * Time complexity : O(n)
     *
     * We iterate over nums once and make a constant time HashMap insertion
     * on each iteration. Therefore, the algorithm runs in O(n) time.
     *
     * pace complexity : O(n)
     *
     * At most, the HashMap can contain n−⌊n/2] associations, so it occupies O(n) space.
     * This is because an arbitrary array of length n can contain n distinct values, but nums is
     * guaranteed to contain a majority element, which will occupy (at minimum)
     * ⌊n/2 + 1] indices.
     */
    public int majorityElementUsingMap(int[] nums) {


        //While scanning the array, the counter is incremented if you encounter an element which is exactly same as the potential candidate but decremented otherwise.
        // When the counter reaches zero, the element which will be encountered next will become the potential candidate.
        // Keep doing this procedure while scanning the array.
        // However, when you have exhausted the array, you have to make sure that the element recorded in the potential candidate variable is the majority element by checking whether it occurs more than ⌊n/2⌋ times in the array.

        // stores number and its frequency
        Map<Integer, Integer> noFrequencyMap = new HashMap<>();

        // create map with nos and frequency
        for (int num : nums) {
            if (noFrequencyMap.containsKey(num)) {
                noFrequencyMap.put(num, 1 + noFrequencyMap.get(num));
            } else {
                noFrequencyMap.put(num, 1);
            }
        }

        // iterate over map and return no with frequency more than n/2
        for (Map.Entry<Integer, Integer> entry : noFrequencyMap.entrySet()) {
            if (entry.getValue() > nums.length / 2) {
                return entry.getKey();
            }
        }

        return -1;
    }

    /**
     * If the elements are sorted in monotonically increasing (or decreasing) order,
     * the majority element can be found at index n/2 or n/2-1 index in even and odd array length cases
     *
     * Time complexity : O(nlgn)
     * Sorting the array costs O(nlgn) time in Python and Java, so it
     * dominates the overall runtime.
     *
     * Space complexity : O(1) or (O(n))
     * We sorted nums in place here - if that is not allowed, then we must
     * spend linear additional space on a copy of nums and sort the copy
     * instead.
     */
    public int majorityElementUsingSorting(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    /**
     * Boyer-Moore Voting Algorithm
     * Visit: https://www.youtube.com/watch?v=n5QY3x_GNDg
     *
     * Complexity Analysis
     * Time complexity : O(n)
     * Boyer-Moore performs constant work exactly n times, so the algorithm
     * runs in linear time.
     *
     * Space complexity : O(1)
     * Boyer-Moore allocates only constant additional memory.

     */
    public int majorityElementUsingBoyerMooreVotingAlgorithm(int[] nums) {

        int majorityElement = -1;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];

            if (count == 0) {
                majorityElement = currentNum;
            }
            if (majorityElement == currentNum) {
                count++;
            } else {
                if (count == 1) {
                    majorityElement = currentNum;
                } else {
                    count--;
                }
            }
        }

        return majorityElement;
    }

    public static void main(String[] args) {
        MajorityElement solver = new MajorityElement();

        // Test Cases
        int[][] testCases = {
                {3, 2, 3},
                {2, 2, 1, 1, 1, 2, 2},
                {1},
                {1, 1, 2, 2, 2, 2, 2},
                {-1, 1, 1, 1, 2, 1}
        };

        System.out.println("===============================================================");
        System.out.printf("%-30s | %-10s | %-10s%n", "Input Array", "Method", "Result");
        System.out.println("---------------------------------------------------------------");

        for (int[] nums : testCases) {
            String arrayStr = Arrays.toString(nums);

            // Testing Boyer-Moore (Most efficient)
            printRow(arrayStr, "Boyer-Moore", solver.majorityElementUsingBoyerMooreVotingAlgorithm(nums));

            // Testing Map approach
            printRow("", "HashMap", solver.majorityElementUsingMap(nums));

            // Testing Sorting approach
            // (Note: this modifies the array, so we do it last for the test case)
            printRow("", "Sorting", solver.majorityElementUsingSorting(nums));

            System.out.println("---------------------------------------------------------------");
        }
    }

    private static void printRow(String input, String method, int result) {
        System.out.printf("%-30s | %-10s | %-10d%n", input, method, result);
    }
}
