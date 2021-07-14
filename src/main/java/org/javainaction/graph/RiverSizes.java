package org.javainaction.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given a 2D array potentially with different height and width. 0 in array represents land and 1 represents
 * river. A river can take turn meaning you can have an L shaped river either going vertically or horizontally or both.
 *
 * Find all river sizes in the matrix
 *
 * @see MaxAreaIsland where we find maximum of all islands, vs in this case we are finding all areas
 */
public class RiverSizes {
    //O(wh) time | O(wh) space
    public static List<Integer> riverSizes(int[][] matrix) {
        List<Integer> sizes = new ArrayList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(visited[i][j]) {
                    continue;
                }
                traverseNode(i, j, matrix, visited, sizes);
            }
        }
        return sizes;
    }

    public static void traverseNode(int i, int j,
                                    int[][] matrix,
                                    boolean[][] visited,
                                    List<Integer> sizes) {

        int currentRiverSize = 0;
        List<Integer[]> nodesToExplore = new ArrayList<>();
        //start with roo node
        nodesToExplore.add(new Integer[] {i, j});

        while(!nodesToExplore.isEmpty()) {
            //pop out the top element from list and explore all its neighbours
            Integer[] currentNode = nodesToExplore.get(nodesToExplore.size() - 1);
            nodesToExplore.remove(nodesToExplore.size() - 1);
            i = currentNode[0];
            j = currentNode[1];
            //if visited continue exploring something else
            if(visited[i][j]) {
                continue;
            }
            visited[i][j] = true;
            //if not a river continue exploring something else
            if(matrix[i][j] == 0) {
                continue;
            }
            currentRiverSize++;
            //find all neighbours
            List<Integer[]> unvisitedNeighbors = getUnvisitedNeighbors(i, j, matrix, visited);
            nodesToExplore.addAll(unvisitedNeighbors);
        }
        //if we found any river then add it's size to result
        if(currentRiverSize > 0) {
            sizes.add(currentRiverSize);
        }
    }

    public static List<Integer[]> getUnvisitedNeighbors(int i, int j,
                                                        int[][] matrix,
                                                        boolean[][] visited){
        List<Integer[]> unvisitedNeighbors = new ArrayList<>();
        //top left corner
        if( i > 0 && !visited[i - 1][j]
                && matrix[i - 1][j] == 1){
            unvisitedNeighbors.add(new Integer[]{i - 1, j});
        } else if(i > 0) {
            visited[i - 1][j] = true;
        }

        //bottom left corner
        if( i < matrix.length - 1 && !visited[i + 1][j]
                && matrix[i + 1][j] == 1){
            unvisitedNeighbors.add(new Integer[]{i + 1, j});
        } else if(i < matrix.length - 1) {
            visited[i + 1][j] = true;
        }

        //top left corner
        if( j > 0 && !visited[i][j - 1]
                && matrix[i][j - 1] == 1){
            unvisitedNeighbors.add(new Integer[]{i, j - 1});
        } else if( j > 0) {
            visited[i][j - 1] = true;
        }

        //top right corner
        if( j < matrix[0].length - 1 && !visited[i][j + 1]
                &&  matrix[i][j + 1] == 1){
            unvisitedNeighbors.add(new Integer[]{i, j + 1});
        } else if( j < matrix[0].length - 1 ) {
            visited[i][j + 1] = true;
        }

        return unvisitedNeighbors;
    }

    //O(wh) time | O(wh) space
    public static List<Integer> riverSizesShort(int[][] matrix) {
        List<Integer> sizes = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //if not a river continue exploring something else
                if(matrix[i][j] == 0) {
                    continue;
                }
                sizes.add(getRiverSize(matrix, i, j));
            }
        }
        return sizes;
    }

    public static int getRiverSize(int[][] grid, int i, int j) {
        //all edge cases
        if (i >=0 && i < grid.length
                && j >=0 && j < grid[0].length
                && grid[i][j] == 1) {

            //we have done in place visited marked this can be also changed back to
            // visited[i][j] = true and don't traverse those are visited in if condition
            grid[i][j] = 0;

            //size of river is itself + size from all its neighbouring rivers from (top, bottom, left, right)
            return 1 + getRiverSize (grid, i - 1, j) + getRiverSize (grid, i + 1, j)
                    + getRiverSize (grid, i, j - 1) + getRiverSize (grid, i, j + 1);
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] input = {
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0},
        };
        //int[] expected = {1, 2, 2, 2, 5};
        List<Integer> output = riverSizes(input);
        System.out.println(Arrays.deepToString(input) + " has these many rivers : " + output);

        output = riverSizesShort(new int[][]{
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0}});

        System.out.println(Arrays.deepToString(new int[][]{
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0}}) + " has these many rivers : " + output);
    }
}
