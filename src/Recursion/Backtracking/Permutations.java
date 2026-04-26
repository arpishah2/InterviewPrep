package Recursion.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * Example 2:
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * <p>
 * Example 3:
 * Input: nums = [1]
 * Output: [[1]]
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        backtrack(new ArrayList<>(), nums, result);
        return result;
    }

    private void backtrack(List<Integer> currentPermutation, int[] nums, List<List<Integer>> result) {
        if (currentPermutation.size() == nums.length) {
            result.add(new ArrayList<>(currentPermutation));
            return;
        } else if (currentPermutation.size() > nums.length) {
            return;
        }

        for (int currElem : nums) {

            if (!currentPermutation.contains(currElem)) {
                currentPermutation.add(currElem);
                backtrack(currentPermutation, nums, result);
                //Reset
                currentPermutation.removeLast();
            }
        }
    }

    // Helper method to make the output look nice
    private static void printResults(int[] input, List<List<Integer>> output) {
        System.out.println("--- Permutations for " + Arrays.toString(input) + " ---");
        System.out.println("Total count: " + output.size());
        System.out.print("[ ");
        for (int i = 0; i < output.size(); i++) {
            System.out.print(output.get(i));
            if (i < output.size() - 1) System.out.print(", ");
        }
        System.out.println(" ]\n");
    }

    public static void main(String[] args) {
        Permutations sol = new Permutations();

        // Test Case 1: Standard 3-element array
        int[] nums1 = {1, 2, 3};
        printResults(nums1, sol.permute(nums1));

        // Test Case 2: 2-element array
        int[] nums2 = {10, 20};
        printResults(nums2, sol.permute(nums2));
    }
}