package Arrays;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Merges all overlapping intervals in the provided array.
 * Two intervals [a, b] and [c, d] are considered overlapping if the
 * start of one falls within the range of the other (inclusive). For example,
 * [1, 4] and [4, 5] merge into [1, 5].
 * <p>
 * Example Usage:
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 *
 * @param intervals An array of intervals where {@code intervals[i] = [start_i, end_i]}.
 *                  Must have a length between 1 and 10,000.
 * @return An array of the non-overlapping intervals that cover all intervals in the input.
 * @throws IllegalArgumentException if the input array is null or contains invalid intervals.
 */

public class MergeIntervals {

    /**
     * Time complexity : O(nlogn)
     * Space complexity : O(logN) (or O(n))
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        // 1. Sort intervals by their start time (index 0)
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        LinkedList<int[]> mergedIntervals = new LinkedList<>();
        for (int[] interval : intervals) {

            if (mergedIntervals.isEmpty() || mergedIntervals.getLast()[1] < interval[0]) {
                // 2. If list is empty or current interval doesn't overlap (start > last end), add it
                mergedIntervals.add(interval);
            } else {
                // 3. There is an overlap, update the end of the last interval to the maximum end
                mergedIntervals.getLast()[1] = Math.max(interval[1], mergedIntervals.getLast()[1]);
            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();

        // Example 1: Standard overlap
        int[][] input1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println("Result 1: " + Arrays.deepToString(solution.merge(input1)));

        // Example 2: Touching boundaries
        int[][] input2 = {{1, 4}, {4, 5}};
        System.out.println("Result 2: " + Arrays.deepToString(solution.merge(input2)));

        // Example 3: Unsorted input
        int[][] input3 = {{4, 7}, {1, 4}};
        System.out.println("Result 3: " + Arrays.deepToString(solution.merge(input3)));
    }

}
