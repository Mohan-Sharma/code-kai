package com.code.kai.leetcode.curated75.medium.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> newIntervals = new ArrayList<>();
        // Step 1: if newInterval[1] is less that interval[0] like newInt[y, 2] & int[3, x]
        // means it should go before interval.
        // Step 2: If newInterval[0] is greater that interval[1] like newInt[3, y] & int[x, 2]
        // means it should go after interval
        // Step 3: if above 2 conditions does not satisfy means there is an overlap
        // so simply construct the newInterval by merging the interval and newInterval
        // like newInt = [ Math.min(int[0], newInt[0]), Math.max(int[1], newInt[1]) ]
        for (int[] interval: intervals) {
            if (newInterval[1] < interval[0]) {
                newIntervals.add(newInterval);
                // since newInterval is before current iteration interval satisfying step-1 add to list
                // pass the baton of newInterval to the current iteration interval so in the next iteration
                // the current interval is added again satisfying step-1 and so on
                newInterval = interval;
            } else if (interval[1] < newInterval[0]) {
                newIntervals.add(interval);
            } else {
                newInterval = new int[] {Math.min(interval[0], newInterval[0]), Math.max(interval[1], newInterval[1])};
            }
        }
        //finally when all iteration intervals are added, add the last item holding the baton of newInterval
        newIntervals.add(newInterval);
        return newIntervals.toArray(new int[newIntervals.size()][2]);
    }

    public static void main(String[] args) {
        int[][] result = new InsertInterval().insert(new int[][] {{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[] {4, 8});;
        for (int[] arr  : result) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
