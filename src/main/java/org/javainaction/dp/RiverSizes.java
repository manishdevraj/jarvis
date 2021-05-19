package org.javainaction.dp;

import java.util.ArrayList;
import java.util.List;

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
        nodesToExplore.add(new Integer[] {i, j});

        while(!nodesToExplore.isEmpty()) {
            Integer[] currentNode = nodesToExplore.get(nodesToExplore.size() - 1);
            nodesToExplore.remove(nodesToExplore.size() - 1);
            i = currentNode[0];
            j = currentNode[1];
            if(visited[i][j]) {
                continue;
            }
            visited[i][j] = true;
            if(matrix[i][j] == 0) {
                continue;
            }
            currentRiverSize++;
            List<Integer[]> unvisitedNeighbors = getUnvisitedNeighbors(i, j, matrix, visited);
            for(Integer[] neighbor : unvisitedNeighbors) {
                nodesToExplore.add(neighbor);
            }
        }
        if(currentRiverSize > 0) {
            sizes.add(currentRiverSize);
        }
    }

    public static List<Integer[]> getUnvisitedNeighbors(int i, int j,
                                                        int[][] matrix,
                                                        boolean[][] visited){
        List<Integer[]> unvisitedNeighbors = new ArrayList<>();
        if( i > 0 && !visited[i - 1][j]
                && matrix[i - 1][j] == 1){
            unvisitedNeighbors.add(new Integer[]{i - 1, j});
        } else if(i > 0) {
            visited[i - 1][j] = true;
        }

        if( i < matrix.length - 1 && !visited[i + 1][j]
                && matrix[i + 1][j] == 1){
            unvisitedNeighbors.add(new Integer[]{i + 1, j});
        } else if(i < matrix.length - 1) {
            visited[i + 1][j] = true;
        }

        if( j > 0 && !visited[i][j - 1]
                && matrix[i][j - 1] == 1){
            unvisitedNeighbors.add(new Integer[]{i, j - 1});
        } else if( j > 0) {
            visited[i][j - 1] = true;
        }

        if( j < matrix[0].length - 1 && !visited[i][j + 1]
                &&  matrix[i][j + 1] == 1){
            unvisitedNeighbors.add(new Integer[]{i, j + 1});
        } else if( j < matrix[0].length - 1 ) {
            visited[i][j + 1] = true;
        }

        return unvisitedNeighbors;
    }
}
