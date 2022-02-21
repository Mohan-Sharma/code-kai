package com.code.kai.leetcode.curated75.easy.intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Mohan Sharma
 */
public class MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length < 2)
            return true;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end)
                return false;
            else
                end = intervals[i][1];
        }
        return true;
    }
}
