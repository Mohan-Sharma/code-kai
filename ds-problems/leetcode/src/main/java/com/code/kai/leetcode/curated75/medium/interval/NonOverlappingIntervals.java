package com.code.kai.leetcode.curated75.medium.interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Mohan Sharma
 */
public class NonOverlappingIntervals {

    // similar to maximum number of intervals that are non-overlapping.
    public static int eraseOverlapIntervals(int[][] intervals) {
        // algo: find non overlapping count then substract from length of interval
        // it will give the count of overlapping intervals
        int count = 0;
        if (intervals.length < 2) {
            return count;
        }
        int nonOverlappingCount = 1;
        // sorting by end b/c as the condition is based on end and in case of 2 complete overlapping
        // we want to keep the one with the small end so sorting by end means small end one
        // will be used for comparison and has higher chance of finding min overlaps
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                nonOverlappingCount++;
                end = intervals[i][1];
            }
        }
        return intervals.length - nonOverlappingCount;
    }

    public static void main(String[] args) {
        System.out.println(eraseOverlapIntervals(new int[][] {{1,2},{2,3},{3,4},{1,3}}));
    }
}
