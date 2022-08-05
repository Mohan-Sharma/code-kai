package com.code.kai.leetcode.curated75.medium.graph;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mohan Sharma
 */
public class LongestConsecutiveSequence {

    public static int longestConsecutive(int[] nums) {
        Set<Integer> space = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int lcs = 0;
        while (!space.isEmpty()) {
            int leftPointer = space.iterator().next(), rightPointer = leftPointer + 1, count = 0;
            while (space.remove(leftPointer--)) count++;
            while (space.remove(rightPointer++)) count++;
            lcs = Math.max(lcs, count);
        }
        return lcs;
    }

    public static int longestConsecutiveBF(int[] nums) {
        int lcs = -1;
        for (int i = 0; i < nums.length; i++) {
            int count = 1, cur = nums[i] + 1, start = 0;
            for (int j = start; j < nums.length; j++) {
                if (nums[j] == cur) {
                    cur++;
                    count++;
                    j = -1;
                }
            }
            lcs = Math.max(lcs, count);
        }
        return lcs;
    }

    //TLE error
    public static int longestConsecutiveBFWithSpace(int[] nums) {
        int lcs = -1;
        Set<Integer> numbers = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        for (int i = 0; i < nums.length; i++) {
            int count = 1, cur = nums[i] + 1;
            while (numbers.contains(cur)) {
                cur++;
                count++;
            }
            lcs = Math.max(lcs, count);
        }
        return lcs;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutiveBFWithSpace(new int[] {100, 98, 99}));
    }
}
