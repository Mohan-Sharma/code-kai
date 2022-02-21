package com.code.kai.leetcode.curated75.medium.interval;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length < 2)
            return intervals.length;
        int[] startTimes = new int[intervals.length];
        int[] endTimes = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            startTimes[i] = intervals[i][0];
            endTimes[i] = intervals[i][1];
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int roomsUsed = 0, endTimePointer = 0;
        for (int i = 0; i < startTimes.length; i++) {
            if (startTimes[i] < endTimes[endTimePointer])
                roomsUsed++;
            else
               endTimePointer++;
        }
        return roomsUsed;
    }
}
