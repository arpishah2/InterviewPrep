package Recursion.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currSubSet = new ArrayList<>();

        backtrack(0, currSubSet, nums, result);
        return result;
    }

    private void backtrack(int n, List<Integer> currSubSet, int[] nums, List<List<Integer>> result) {

        result.add(new ArrayList<>(currSubSet));

        for (int i = n; i < nums.length; i++) {
            {
                currSubSet.add(nums[i]);
                backtrack(i + 1, currSubSet, nums, result);
                //reset for backtracking
                currSubSet.removeLast();
            }
        }
    }
}
