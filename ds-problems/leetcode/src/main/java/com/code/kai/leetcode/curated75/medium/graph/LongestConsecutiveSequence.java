package com.code.kai.leetcode.curated75.medium.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Mohan Sharma
 */
public class LongestConsecutiveSequence {

    public static int longestConsecutiveOneDirectionCheck(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            set.add(n);
        }
        int best = 0;
        // suppose input is [100, 99, 98, 97], since on first iteration 99 is present it will just skip the count and revisit again.
        // so 100 skip the count since 99 exists, 99 skip, 98 skip then 97 since 96 does not exist start the forward count
        // by increment by 1 until 101 is reached
        for(int n : set) {
            if(!set.contains(n - 1)) {
                int m = n + 1;
                while(set.contains(m)) {
                    m++;
                }
                best = Math.max(best, m - n);
            }
        }
        return best;
    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> space = IntStream.of(nums).boxed().collect(Collectors.toSet());
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

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[] {9, 10, 20, 19, 18, 17, 15, 14, 13, 12, 11, 0,3,7,2,5,8,4,6,0,1}));
    }
}
