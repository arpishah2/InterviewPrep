package Heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 * <p>
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * <p>
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * Example 3:
 * Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 * Output: [1,2]
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 * <p>
 * <p>
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {

        //store number and its frequency in map
        Map<Integer, Integer> noToFrequencyMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            noToFrequencyMap.put(nums[i], noToFrequencyMap.getOrDefault(nums[i], 0) + 1);
        }

        //Create min heap logic to run using the frequency from frequency map  (element with min frequency at top)
        Queue<Integer> freqHeap = new PriorityQueue<>((n1, n2) -> noToFrequencyMap.get(n1) - noToFrequencyMap.get(n2));

        //Insert k elements in the heap
        //Both add and poll operations in a heap of size take time
        //Total: N * O(log k) = O(N * log k)

        for (int n : noToFrequencyMap.keySet()) {
            // add elements to min heap
            freqHeap.add(n);

            //maintain size of k elements
            if (freqHeap.size() > k) {
                //removes the element with least frequency from minHeap
                freqHeap.poll();
            }
        }

        //Build result : O(K* log K)
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            result[i] = freqHeap.poll(); //O(log K)
        }
        return result;
    }

    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();

        System.out.println("--- Top K Frequent Elements Test Results ---");
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-30s | %-3s | %-15s%n", "Input Array", "k", "Result");
        System.out.println("---------------------------------------------------------");

        // Test Case 1
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        printTestResult(nums1, k1, solution.topKFrequent(nums1, k1));

        // Test Case 2
        int[] nums2 = {1};
        int k2 = 1;
        printTestResult(nums2, k2, solution.topKFrequent(nums2, k2));

        // Test Case 3: Mixed frequencies
        int[] nums3 = {1, 2, 1, 2, 1, 2, 3, 1, 3, 2};
        int k3 = 2;
        printTestResult(nums3, k3, solution.topKFrequent(nums3, k3));

        // Test Case 4: Multiple unique elements, k = 3
        int[] nums4 = {4, 4, 4, 6, 6, 8, 8, 8, 8, 2, 2, 2, 2, 2};
        int k4 = 3;
        printTestResult(nums4, k4, solution.topKFrequent(nums4, k4));

        System.out.println("---------------------------------------------------------");
    }

    /**
     * Helper method to format the output of each test case nicely.
     */
    private static void printTestResult(int[] nums, int k, int[] result) {
        // Convert array to string for display
        StringBuilder inputStr = new StringBuilder("[");
        for (int i = 0; i < nums.length; i++) {
            inputStr.append(nums[i]);
            if (i < nums.length - 1) inputStr.append(",");
        }
        inputStr.append("]");

        // Convert result to string for display
        StringBuilder resultStr = new StringBuilder("[");
        for (int i = 0; i < result.length; i++) {
            resultStr.append(result[i]);
            if (i < result.length - 1) resultStr.append(",");
        }
        resultStr.append("]");

        // If input string is too long for the table, truncate it
        String displayInput = inputStr.toString();
        if (displayInput.length() > 28) {
            displayInput = displayInput.substring(0, 25) + "...";
        }

        System.out.printf("%-30s | %-3d | %-15s%n", displayInput, k, resultStr.toString());
    }

}
