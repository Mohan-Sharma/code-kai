package com.code.kai.leetcode.curated75.medium.graph;

import java.util.Arrays;
import java.util.HashSet;
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

    public static int longestConsecutiveByCatchingTheMin(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            set.add(n);
        }
        int best = 0;
        for(int n : set) {
            //if the current is the min in the set,
            // then catch the min and check sequence by incrementing by 1
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

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[] {100, 98, 99}));
    }
}
