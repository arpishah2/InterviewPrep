package Arrays;

import java.util.Arrays;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 * <p>
 * Example 1:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * <p>
 * Example 2:
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * <p>
 * Constraints:
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
public class TrappingRainWater {

    /**
     * Uses dynamic programming technique
     * Time complexity: O(n)
     * Space complexity: O(n) extra space.
     */
    public int trap(int[] height) {

        int size = height.length;
        if (size == 0) return 0;

        int trappableWater = 0;

        //Find maximum height of bar from the left end upto an index i in the array left_max
        int[] maxHeightLeft = new int[size];
        maxHeightLeft[0] = height[0];

        //Find maximum height of bar from the right end upto an index i in the array right_max.
        int[] maxHeightRight = new int[size];
        maxHeightRight[size - 1] = height[size - 1];

        // since using i - 1, hence need to start with index 1
        for (int i = 1; i < size; i++) {
            maxHeightLeft[i] = Math.max(maxHeightLeft[i - 1], height[i]);
        }

        // since using j+1, hence starting at size -2
        for (int j = size - 2; j >= 0; j--) {
            maxHeightRight[j] = Math.max(maxHeightRight[j + 1], height[j]);
        }

        for (int i = 0; i < size - 1; i++) {
            trappableWater += Math.min(maxHeightLeft[i], maxHeightRight[i]) - height[i];
        }

        return trappableWater;
    }

    /**
     * Main method to execute test cases for Trapping Rain Water with pretty-printed output.
     */
    public static void main(String[] args) {
        TrappingRainWater solver = new TrappingRainWater();

        // Test Scenarios
        int[][] testInputs = {
                {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, // Standard case (Example 1)
                {4, 2, 0, 3, 2, 5},                   // Larger traps (Example 2)
                {1, 1, 1},                            // Flat surface
                {3, 0, 2, 0, 4},                      // Deep valleys
                {5, 4, 3, 2, 1},                      // Descending slope (no traps)
                {1, 2, 3, 4, 5}                       // Ascending slope (no traps)
        };

        int[] expected = {6, 9, 0, 7, 0, 0};

        System.out.println("===============================================================");
        System.out.printf("%-30s | %-8s | %-8s | %-6s%n", "Elevation Map", "Expected", "Actual", "Status");
        System.out.println("---------------------------------------------------------------");

        for (int i = 0; i < testInputs.length; i++) {
            int actual = solver.trap(testInputs[i]);
            String inputStr = Arrays.toString(testInputs[i]);

            // Format string for better table alignment
            if (inputStr.length() > 28) {
                inputStr = inputStr.substring(0, 25) + "...";
            }

            System.out.printf("%-30s | %-8d | %-8d | %-6s%n",
                    inputStr, expected[i], actual, (expected[i] == actual ? "PASS" : "FAIL"));
        }
        System.out.println("===============================================================");
    }


}
