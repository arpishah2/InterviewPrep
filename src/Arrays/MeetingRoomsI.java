package Arrays;

import java.util.Arrays;

/**
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
 * <p>
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * <p>
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 */
public class MeetingRoomsI {

    /**
     * Time complexity: O(nlogn)
     * Space complexity: O(logn) or O(n)
     */
    public boolean canAttendMeetings(int[][] intervals) {

        if (intervals.length == 0 || intervals == null) {
            return true;
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < intervals.length - 1; i++) {
            int currentIntervalEnd = intervals[i][1];
            int nextIntervalStart = intervals[i + 1][0];
            if (currentIntervalEnd > nextIntervalStart) {
                return false;
            }
        }
        return true;
    }

    // ===================== PRETTY PRINT =====================
    public static void printIntervals(int[][] intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            System.out.print(Arrays.toString(intervals[i]));
            if (i != intervals.length - 1) System.out.print(", ");
        }
        System.out.print("]");
    }

    // ===================== TEST RUNNER =====================
    public static void runTest(int[][] intervals) {

        MeetingRoomsI obj = new MeetingRoomsI();

        System.out.println("========================================");
        System.out.print("Input Intervals: ");
        printIntervals(intervals);
        System.out.println();

        // Make a copy for visualization
        int[][] copy = Arrays.stream(intervals).map(int[]::clone).toArray(int[][]::new);

        // Sort copy for display
        Arrays.sort(copy, (a, b) -> Integer.compare(a[0], b[0]));

        System.out.print("Sorted Intervals: ");
        printIntervals(copy);
        System.out.println();

        boolean result = obj.canAttendMeetings(intervals);

        System.out.println("Can attend all meetings? → " + result);
        System.out.println("========================================\n");
    }

    // ===================== PSVM =====================
    public static void main(String[] args) {

        // Example 1 (overlap)
        runTest(new int[][]{{0, 30}, {5, 10}, {15, 20}});

        // Example 2 (no overlap)
        runTest(new int[][]{{7, 10}, {2, 4}});

        // Edge case: single meeting
        runTest(new int[][]{{1, 5}});

        // Edge case: empty input
        runTest(new int[][]{});

        // Touching intervals (valid)
        runTest(new int[][]{{1, 5}, {5, 10}, {10, 15}});

        // Full overlap
        runTest(new int[][]{{1, 10}, {2, 6}, {3, 5}});

        // Random order
        runTest(new int[][]{{8, 9}, {1, 3}, {4, 7}});
    }
}
