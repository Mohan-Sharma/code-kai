package com.code.kai.leetcode.curated75.easy.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class ClimbingStairs {

    public static int climbStairs(int n) {
        int step = 1;
        int stepBefore = 1;
        for (int i = 0; i < n - 1; i++) {
            int temp = stepBefore;
            stepBefore = step + stepBefore;
            step = temp;
        }
        return stepBefore;
    }

    public static int climbStairsMemoization(int n) {
        if (n < 0)
            return 0;
        Map<Integer, Integer> mem = new HashMap<>();
        mem.put(0, 1);
        mem.put(1, 1);
        return countWaysToClimbStairs(n, mem);
    }

    private static int countWaysToClimbStairs(int n, Map<Integer, Integer> mem) {
        if (mem.containsKey(n))
            return mem.get(n);
        mem.put(n, countWaysToClimbStairs(n - 1, mem) + countWaysToClimbStairs(n - 2, mem));
        return mem.get(n);
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(45));
    }
}
