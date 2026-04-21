package Graph.BidirectionalGraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * <p>
 * Example 2:
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 */
public class NumberOfIsland {

    /**
     * Linear scan the 2d grid map, if a node contains a '1', then it is a root node
     * that triggers a Depth First Search. During DFS, every visited node should be
     * set as '0' to mark as visited node. Count the number of root nodes that trigger
     * DFS, this number would be the number of islands since each DFS starting at some
     * root identifies an island.
     * Time complexity : O(M×N)
     * Space complexity : case O(M×N)
     */
    public int numIslandsDFS(char[][] grid) {
        int num_islands = 0;
        if (grid == null) {
            return 0;
        }


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    num_islands++;
                    dfsIslands(grid, i, j);
                }
            }
        }

        return num_islands;
    }

    void dfsIslands(char[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        dfsIslands(grid, i - 1, j);
        dfsIslands(grid, i + 1, j);
        dfsIslands(grid, i, j - 1);
        dfsIslands(grid, i, j + 1);
    }

    /**
     * if a node contains a '1', then it is a root node
     * that triggers a Breadth First Search. Put it into a queue and set its value
     * as '0' to mark as visited node. Iteratively search the neighbors of enqueued
     * nodes until the queue becomes empty.
     * Time complexity : O(M×N)
     * Space complexity : O(min(M,N))
     */
    public int numOfIslandsBfs(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int numOfIslands = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    ++numOfIslands;
                    grid[i][j] = '0'; // mark as visited

                    Queue<Integer> neighbours = new LinkedList<>();
                    neighbours.add(i * cols + j);

                    while (!neighbours.isEmpty()) {
                        int id = neighbours.remove();
                        int row = id / j;
                        int col = id % j;

                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            neighbours.add((row - 1) * j + col);
                            grid[row - 1][col] = '0';
                        }

                        if (row + 1 >= 0 && grid[row + 1][col] == '1') {
                            neighbours.add((row + 1) * j + col);
                            grid[row + 1][col] = '0';
                        }

                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            neighbours.add(row * j + col - 1);
                            grid[row][col - 1] = '0';
                        }

                        if (col + 1 < j && grid[row][col + 1] == '1') {
                            neighbours.add(row * j + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }
        return numOfIslands;
    }

}
