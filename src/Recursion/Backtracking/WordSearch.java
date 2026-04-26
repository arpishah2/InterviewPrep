package Recursion.Backtracking;

import java.util.Arrays;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 */
public class WordSearch {

    private char[][] board;
    private int rows;
    private int cols;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.rows = board.length;
        this.cols = board[0].length;

        for (int row = 0; row < this.rows; ++row) {
            // The entire inner loop is the "one statement" of the outer loop
            for (int col = 0; col < this.cols; ++col) {
                // This 'if' is the "one statement" of the inner loop
                if (this.backtrack(row, col, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtrack(int row, int col, String word, int index) {

        /* Step 1). check the bottom case. */
        if (index >= word.length()) return true;

        /* Step 2). Check the boundaries. */
        if (
                row < 0 ||
                        row == this.rows ||
                        col < 0 ||
                        col == this.cols ||
                        this.board[row][col] != word.charAt(index)
        ) return false;

        /* Step 3). explore the neighbors in DFS */
        // mark the path before the next exploration
        this.board[row][col] = '#';

        int[] rowOffsets = {0, 1, 0, -1};
        int[] colOffsets = {1, 0, -1, 0};
        boolean ret = false;

        for (int d = 0; d < 4; ++d) {
            ret = this.backtrack(
                    row + rowOffsets[d],
                    col + colOffsets[d],
                    word,
                    index + 1
            );
            if (ret) break;
        }

        /* Step 4). clean up and return the result. */
        this.board[row][col] = word.charAt(index);
        return ret;
    }

    public static void main(String[] args) {
        WordSearch solver = new WordSearch();

        // Test Case 1: Word exists
        char[][] board1 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word1 = "ABCCED";

        System.out.println("Board:");
        printBoard(board1);
        System.out.println("Searching for: " + word1);
        System.out.println("Result: " + solver.exist(board1, word1)); // Expected: true

        System.out.println("\n-------------------\n");

        // Test Case 2: Word does not exist
        char[][] board2 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word2 = "ABCB";
        System.out.println("Searching for: " + word2);
        System.out.println("Result: " + solver.exist(board2, word2)); // Expected: false
    }

    // Helper method for pretty printing the board
    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}
