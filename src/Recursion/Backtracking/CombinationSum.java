package Recursion.Backtracking;

import java.util.*;

/**
 * Combination Sum
 * <p>
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 * <p>
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * <p>
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * <p>
 * Example 3:
 * Input: candidates = [2], target = 1
 * Output: []
 * <p>
 * Constraints:
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * All elements of candidates are distinct.
 * 1 <= target <= 40
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        int currentSum = 0;
        LinkedList<Integer> currentCandidates = new LinkedList<Integer>();
        backtrack(currentSum, currentCandidates, candidates, target, result);

        Set<List<Integer>> uniqueResults = new HashSet<>();
        for (List<Integer> combinationSum : result) {
            Collections.sort(combinationSum);
            uniqueResults.add(combinationSum);
        }
        return uniqueResults.stream().toList();
    }

    private void backtrack(int currentSum, LinkedList<Integer> currentCandidates, int[] inputCandidates, int target, List<List<Integer>> result) {
        if (currentSum == target) {
            //if desired sum is achieved
            result.add(new ArrayList<>(currentCandidates));
            return;
        } else if (currentSum > target) {
            return;
        }

        //add to input candidates till sum is target
        for (int i = 0; i < inputCandidates.length; i++) {
            int currentElement = inputCandidates[i];

            if (currentSum + currentElement <= target) {
                currentCandidates.add(currentElement);
                int newSum = currentSum + currentElement;
                backtrack(newSum, currentCandidates, inputCandidates, target, result);

                //reset state
                currentCandidates.removeLast();
            }
        }
    }
}
