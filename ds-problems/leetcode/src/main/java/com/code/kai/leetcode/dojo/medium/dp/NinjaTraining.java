package com.code.kai.leetcode.dojo.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
/*
Problem:
    A Ninja has an ‘N’ Day training schedule. He has to perform one of these three activities each day.
    There are merit points associated with performing an activity each day. The same activity can’t be
    performed on two consecutive days. We need to find the maximum merit points the ninja can attain in N Days.

    e.g. If the given ‘POINTS’ array is [[1,2,5], [3 ,1 ,1] ,[3,3,3] ],the answer will be 11 as 5 + 3 + 3.
 */
public class NinjaTraining {

    public int maxMeritPoints(int[][] points) {
        int[][] dp = new int[points.length][points[0].length + 1];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        return maxMeritPointsTopDownMemoization(points.length - 1, points[0].length, points, dp);
    }

    private int maxMeritPointsTopDown(int day, int prevDayActivity, int[][] points) {
        if (day == 0) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < points[0].length; i++) {
                if (i != prevDayActivity)
                    max = Math.max(max, points[0][i]);
            }
            return max;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < points[day].length; i++) {
            if (i != prevDayActivity) {
                max = Math.max(max, maxMeritPointsTopDown(day - 1, i, points) + points[day][i]);
            }
        }
        return max;
    }

    private int maxMeritPointsTopDownMemoization(int day, int prevDayActivity, int[][] points, int[][] dp) {
        if (day == 0) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < points[0].length; i++) {
                if (i != prevDayActivity)
                    max = Math.max(max, points[0][i]);
            }
            return max;
        }

        if(dp[day][prevDayActivity] > 0)
            return dp[day][prevDayActivity];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < points[day].length; i++) {
            if (i != prevDayActivity) {
                max = Math.max(max, maxMeritPointsTopDownMemoization(day - 1, i, points, dp) + points[day][i]);
            }
        }

        dp[day][prevDayActivity] = max;
        return max;
    }

    private int maxMeritPointsBottomUpTabulation(int[][] points) {
        int[][] dp = new int[points.length][points[0].length + 1];
        int days = points.length, tasks = points[0].length;

        for (int i = 0; i < tasks + 1; i++) {
            int curMax = Integer.MIN_VALUE;
            for (int j = 0; j < tasks; j++) {
                if (i != j)
                    curMax = Math.max(curMax, points[0][j]);
            }
            dp[0][i] = curMax;
        }

        for (int day = 1; day < days; day++) {

            for (int prevTask = 0; prevTask < tasks + 1; prevTask++) {
                int curMax = Integer.MIN_VALUE;
                for (int task = 0; task < tasks; task++) {
                    if (task != prevTask) {
                        curMax = Math.max(curMax, dp[day - 1][task] + points[day][task]);
                    }
                }
                dp[day][prevTask] = curMax;
            }
        }
        return dp[days - 1][tasks - 1];
    }

    private int maxMeritPointsBottomUpTabulationSpaceOptimized(int[][] points) {
        int[] dp = new int[points[0].length + 1];
        int days = points.length, tasks = points[0].length;

        for (int i = 0; i < tasks + 1; i++) {
            int curMax = Integer.MIN_VALUE;
            for (int j = 0; j < tasks; j++) {
                if (i != j)
                    curMax = Math.max(curMax, points[0][j]);
            }
            dp[i] = curMax;
        }

        for (int day = 1; day < days; day++) {
            int[] tempDP = new int[dp.length];
            for (int prevTask = 0; prevTask < tasks + 1; prevTask++) {
                int curMax = Integer.MIN_VALUE;
                for (int task = 0; task < tasks; task++) {
                    if (task != prevTask) {
                        curMax = Math.max(curMax, dp[task] + points[day][task]);
                    }
                }
                tempDP[prevTask] = curMax;
            }
            dp = tempDP;
        }
        return dp[tasks - 1];
    }

    public static void main(String[] args) {
        System.out.println(new NinjaTraining().maxMeritPointsBottomUpTabulationSpaceOptimized(new int[][] {{1, 2, 5}, {3, 1, 1}, {3, 3, 3}}));
    }

}
