package Arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * <p>
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 */
public class MeetingRoomsII {


    /**
     * Time Complexity: O(NlogN)
     * Space Complexity: O(N)
     */
    public int minMeetingRooms(int[][] intervals) {


        //Sorting - considering the meetings in a sorted order of their start times.

        // At any point in time we have multiple rooms that can be occupied and we don't really care which room is free as long as we find one when required for a new meeting.
        // keep all the rooms in a min heap where the key for the min heap would be the ending time of meeting
        // Every time we want to check if any room is free or not, simply check the topmost element of the min heap as that would be the room that would get free the earliest out of all the other rooms currently occupied
        // If the room we extracted from the top of the min heap isn't free, then no other room is


        /**
         * Algorithm
         *
         * Sort the given meetings by their start time.
         * Initialize a new min-heap and add the first meeting's ending time to the heap. We simply need to keep track of the ending times as that tells us when a meeting room will get free.
         * For every meeting room check if the minimum element of the heap i.e. the room at the top of the heap is free or not.
         * If the room is free, then we extract the topmost element and add it back with the ending time of the current meeting we are processing.
         * If not, then we allocate a new room and add it to the heap.
         * After processing all the meetings, the size of the heap will tell us the number of rooms allocated. This will be the minimum number of rooms needed to accommodate all the meetings.
         */

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        //sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        //Min heap
        PriorityQueue<Integer> allocator = new PriorityQueue<>(intervals.length, (o1, o2) -> o1 - o2);


        // Add the first meeting
        allocator.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            int startTime = intervals[i][0];
            if (startTime >= allocator.peek()) {
                // If the room due to free up the earliest is free, assign that room to this meeting.
                allocator.poll();
            }

            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            allocator.add(intervals[i][1]);
        }

        return allocator.size();
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

        MeetingRoomsII obj = new MeetingRoomsII();

        System.out.println("========================================");
        System.out.print("Input: ");
        printIntervals(intervals);
        System.out.println();

        // Copy for visualization
        int[][] copy = Arrays.stream(intervals).map(int[]::clone).toArray(int[][]::new);

        Arrays.sort(copy, (a, b) -> a[0] - b[0]);

        System.out.print("Sorted: ");
        printIntervals(copy);
        System.out.println();

        int rooms = obj.minMeetingRooms(intervals);

        System.out.println("Minimum Rooms Required → " + rooms);
        System.out.println("========================================\n");
    }

    // ===================== PSVM =====================
    public static void main(String[] args) {

        // Example 1 (overlap)
        runTest(new int[][]{{0, 30}, {5, 10}, {15, 20}});

        // Example 2 (no overlap)
        runTest(new int[][]{{7, 10}, {2, 4}});

        // All overlapping → max rooms
        runTest(new int[][]{{1, 5}, {2, 6}, {3, 7}, {4, 8}});

        // No overlaps
        runTest(new int[][]{{1, 2}, {3, 4}, {5, 6}});

        // Touching intervals (reuse room)
        runTest(new int[][]{{1, 5}, {5, 10}, {10, 15}});

        // Random order
        runTest(new int[][]{{8, 9}, {1, 3}, {4, 7}, {2, 6}});

        // Single meeting
        runTest(new int[][]{{1, 10}});

        // Empty input
        runTest(new int[][]{});
    }


}
