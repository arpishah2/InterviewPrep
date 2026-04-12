package Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralMatrix {

    /**
     * Solution for traversing a 2D matrix in spiral order.
     * <p>
     * Strategy:
     * 1. Define four boundaries: 'up', 'down', 'left', and 'right'.
     * 2. Traverse the outer perimeter (top row, right column, bottom row, left column).
     * 3. After finishing each side, shrink the boundary (e.g., after the top row, increment 'up').
     * 4. Continue until the result list contains all elements (rows * columns).
     *
     * @param matrix The 2D grid to traverse.
     * @return A list of integers in spiral order.
     * @complexity Time: O(M * N) where M is rows and N is columns (visits every cell once).
     * @complexity Space: O(1) additional space (not counting the result list).
     */
    public List<Integer> spiralOrder(int[][] matrix) {

        if (matrix == null || matrix.length == 0) return new ArrayList<>();

        int rows = matrix.length;
        int columns = matrix[0].length;

        List<Integer> result = new ArrayList<>();

        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (result.size() < rows * columns) {

//            1, 2, 3
//            4, 5, 6
//            7, 8, 9

            // Step 1: Traverse from Left to Right along the current 'up' boundary
            if (left <= right) {
                // Traverse left to right
                for (int j = left; j <= right && result.size() < rows * columns; j++) {
                    result.add(matrix[up][j]);
                }

                // Move the 'up' boundary down to skip the row we just finished
                up++;
            }

//            up=1
//            4, 5, 6
//            7, 8, 9

            // Step 2: Traverse Downwards along the current 'right' boundary
            if (up <= down) {
                // Traverse downwards
                for (int i = up; i <= down && result.size() < rows * columns; i++) {
                    result.add(matrix[i][right]);
                }

                // Move the 'right' boundary left to skip the column we just finished
                right--;
            }


//            right=1
//            4, 5,
//            7, 8,

            // Step 3: Traverse from Right to Left along the current 'down' boundary
            if (left <= right) {
                // Traverse left
                for (int j = right; j >= left && result.size() < rows * columns; j--) {
                    result.add(matrix[down][j]);
                }

                // Move the 'down' boundary up to skip the row we just finished
                down--;
            }

//            down=1
//            4, 5,

            // Step 4: Traverse Upwards along the current 'left' boundary
            if (down >= up) {
                // Traverse up
                for (int i = down; i >= up && result.size() < rows * columns; i--) {
                    result.add(matrix[i][left]);
                }

                // Move the 'left' boundary right to skip the column we just finished
                left++;
            }


            //left = 1
            //5

        } // This brace correctly closes the WHILE loop

        return result;
    } // This brace correctly closes the METHOD


    /**
     * Given that we are at (row, col), where row is the row index, and col is the column index.
     * <p>
     * move right: (row, col + 1)
     * move downwards: (row + 1, col)
     * move left: (row, col - 1)
     * move upwards: (row - 1, col)
     * <p>
     * Time complexity: O(M⋅N). This is because we visit each element once.
     * Space complexity: O(1). This is because we don't use other data structures. Remember that we don't include the output array in the space complexity.
     */
    public List<Integer> spiralOrderImproved(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int rows = matrix.length;
        int columns = matrix[0].length;
        int up = 0;
        int left = 0;
        int right = columns - 1;
        int down = rows - 1;

        while (result.size() < rows * columns) {
            // Traverse from left to right.
            for (int col = left; col <= right; col++) {
                result.add(matrix[up][col]);
            }
            // Traverse downwards.
            for (int row = up + 1; row <= down; row++) {
                result.add(matrix[row][right]);
            }
            // Make sure we are now on a different row.
            if (up != down) {
                // Traverse from right to left.
                for (int col = right - 1; col >= left; col--) {
                    result.add(matrix[down][col]);
                }
            }
            // Make sure we are now on a different column.
            if (left != right) {
                // Traverse upwards.
                for (int row = down - 1; row > up; row--) {
                    result.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            up++;
            down--;
        }

        return result;
    }


    public static void main(String[] args) {
        SpiralMatrix sol = new SpiralMatrix();

        System.out.println("==============================================");
        System.out.println("        RUNNING SPIRAL MATRIX TESTS           ");
        System.out.println("==============================================");

        // Case 1: Square Matrix (3x3)
        int[][] m1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> exp1 = Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5);
        runTest(sol, 1, m1, exp1);

        // Case 2: Rectangular Matrix (3x4)
        int[][] m2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        List<Integer> exp2 = Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7);
        runTest(sol, 2, m2, exp2);

        // Case 3: Single Row
        int[][] m3 = {{1, 2, 3, 4}};
        List<Integer> exp3 = Arrays.asList(1, 2, 3, 4);
        runTest(sol, 3, m3, exp3);

        // Case 4: Single Column
        int[][] m4 = {{1}, {2}, {3}};
        List<Integer> exp4 = Arrays.asList(1, 2, 3);
        runTest(sol, 4, m4, exp4);

        System.out.println("==============================================");
    }

    private static void runTest(SpiralMatrix sol, int testNum, int[][] matrix, List<Integer> expected) {
        List<Integer> result = sol.spiralOrder(matrix);
        boolean passed = result.equals(expected);

        String status = passed ? "✅ PASS" : "❌ FAIL";

        System.out.printf("TEST #%d | %s\n", testNum, status);
        System.out.println("Input Matrix: ");
        for (int[] row : matrix) System.out.println("  " + Arrays.toString(row));
        System.out.println("Expected    : " + expected);
        System.out.println("Actual      : " + result);
        System.out.println("----------------------------------------------");


        List<Integer> result2 = sol.spiralOrderImproved(matrix);
        boolean passed2 = result2.equals(expected);
        String status2 = passed2 ? "✅ PASS" : "❌ FAIL";

        System.out.printf("TEST #%d | %s\n", testNum, status2);
        System.out.println("Input Matrix: ");
        for (int[] row : matrix) System.out.println("  " + Arrays.toString(row));
        System.out.println("Expected    : " + expected);
        System.out.println("Actual      : " + result);
        System.out.println("----------------------------------------------");
    }

}
