package com.code.kai.leetcode.curated75.medium.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class MergeInterval {

    public static int[][] merge(int[][] intervals) {
        if (intervals.length < 2)
            return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> space = new ArrayList<>();
        int[] baton = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            if (baton[1] < currentInterval[0]) {
                space.add(baton);
                baton = currentInterval;
            } else if (currentInterval[1] < baton[0]) {
                space.add(currentInterval);
            } else {
                baton = new int[] { Math.min(baton[0], currentInterval[0]), Math.max(baton[1], currentInterval[1]) };
            }
        }
        space.add(baton);
        return space.toArray(new int[space.size()][2]);
    }

    private static int[] getInitialBaton(int[][] intervals) {
        int[] min = {Integer.MAX_VALUE, 0};
        for (int[] interval: intervals){
            min = interval[0] < min[0] ? interval : min;
        }
        return min;
    }

    public static void main(String[] args) {
        int[][] result = merge(new int[][] {{1, 4}, {0, 0}});
        for (int[] arr: result) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
