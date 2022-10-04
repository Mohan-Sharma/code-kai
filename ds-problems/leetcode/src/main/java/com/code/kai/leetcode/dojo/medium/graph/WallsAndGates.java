package com.code.kai.leetcode.dojo.medium.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * @author Mohan Sharma
 */
public class WallsAndGates {

    // Problem: 0 means gate, -1 means wall, Integer.MAX means empty room, from each empty room find path to closest gate
    // Use DFS, if current is empty room go 4 directions to find the minimum distance for that room
    // Since from some of the rooms it won't be possible to reach the gates, hence it's better if we start from gates and
    // see which rooms can be reached.
    public void wallsAndGates(int[][] rooms) {
        int rows = rooms.length, cols = rooms[0].length;

        for (int i = 0; i <rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == 0) {
                    findClosestGate(rooms, i, j, 0);
                }
            }
        }
    }

    private void findClosestGate(int[][] rooms, int row, int col, int distance) {
        if (row < 0 || col < 0 || row >= rooms.length || col >= rooms[0].length || rooms[row][col] == -1) // overbound or reached wall from a gate
            return;
        if (rooms[row][col] < distance) // allow current gate but return when reached another gate or already populated small distance empty room so returned, since for empty space this will be false
            return;
        // else we reached a room, check if the current distance is small then already populated distance from some other gate and populate
        rooms[row][col] = distance; // initially will either be 0 if empty or populated with some distance from another gate
        findClosestGate(rooms, row + 1, col, distance + 1);
        findClosestGate(rooms, row - 1, col, distance + 1);
        findClosestGate(rooms, row, col + 1, distance + 1);
        findClosestGate(rooms, row, col - 1, distance + 1);
    }

    public void wallsAndGatesBFS(int[][] rooms) {
        int rows = rooms.length, cols = rooms[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i <rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == 0) {
                   q.add(new int[] {i, j});
                }
            }
        }
        List<int[]> directions = List.of(new int[]{0, 1}, new int[]{0, -1}, new int[]{1, 0}, new int[]{-1, 0});

        while (!q.isEmpty()) {
            int[] gate = q.poll();

            for (int[] direction : directions) {
                int row = gate[0] + direction[0];
                int col = gate[1] + direction[1];
                if (row < 0 || col < 0 || row >= rooms.length || col >= rooms[0].length || rooms[row][col] <= rooms[gate[0]][gate[1]] + 1) // overbound or reached wall from a gate
                    continue;
                rooms[row][col] = rooms[gate[0]][gate[1]] + 1;
                q.add(new int[] {row, col});
            }
        }
    }
    public static void main(String[] args) {
        int[][] rooms = {{2147483647, -1, 0, 2147483647}, {2147483647, 2147483647, 2147483647, -1}, {2147483647, -1, 2147483647, -1}, {0, -1, 2147483647, 2147483647}};
        new WallsAndGates().wallsAndGates(rooms);
        System.out.println(Arrays.deepToString(rooms));
    }
}
