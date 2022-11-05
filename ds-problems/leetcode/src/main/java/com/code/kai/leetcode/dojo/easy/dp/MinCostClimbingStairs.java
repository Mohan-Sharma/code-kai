package com.code.kai.leetcode.dojo.easy.dp;

/**
 * @author Mohan Sharma
 */
/*
    Problem: We are given an array where the index is the stair number and the value at an index is the
    cost at that point. Now from any stair one can go either 1 step or 2 steps. We need to find in what
    minimum cost we can reach top + 1 means arr.length
 */
public class MinCostClimbingStairs {

    /*
    Solution: For every index we have2 choices - go 1 step or go 2 step e.g. [10, 15, 20] for 0 index
    we can go 1 step to index 1 which cost=10 or go 2 step and reach index 2 which cost 10. When we
    reach index 1 we have 2 choices - go 1 step go to index 2 which cost 10 + 15 or take 2 steps reach
    index 3(destination) which cost 10 + 15. Again from index 2 we can take only 1 step since 2 steps are
    out of bound which cost 10 + 15 + 20. Repeated work can be avoided by memoization
                         0
                  10 /      \ 10
                   1         2
              15 /  \ 15 20 /   \
               2      3    3    out of bound
          20 /  \
           3    out of bound return +INF
     */
    public int minCostClimbingStairsDFS(int[] cost) {
        int[] dp = new int[cost.length];
        minCostClimbingStairsDFS(cost, dp, 0);
        return Math.min(dp[0], dp[1]);
    }

    private int minCostClimbingStairsDFS(int[] costs, int[] dp, int index) {
        if (index == costs.length)
            return 0;
        if (index > costs.length)
            return Integer.MAX_VALUE;
        if (dp[index] > 0)
            return dp[index];
        int firstChoice = minCostClimbingStairsDFS(costs, dp, index + 1);
        if (firstChoice != Integer.MAX_VALUE)
            firstChoice += costs[index];
        int secondChoice = minCostClimbingStairsDFS(costs, dp, index + 2);
        if (secondChoice != Integer.MAX_VALUE)
            secondChoice += costs[index];
        int min = Math.min(firstChoice, secondChoice);
        dp[index] = min;
        return min;
    }

    /*
    [10, 15, 20, dest, out of bound]
    Solution: Let's say we start from end i.e. reverse way. When we are at index 2 of cost there is only 1
    choice we can go only 1 step hence dp[2] will always be cost[2] (current index cost) + 0 (base case)
    so result array i.e. [0,0, 20]
    when index is 1, if we take 1 step means it would be current index value which is 15 + dp[2] which is 20
    so when we take 1 step it would require 35. But if we take 2 steps then it would be 15 + 0 (base case which) is 15
    so dp[1] = min(35, 15)
    similarly from index 0, if we take one step it would be 10 + dp[1] and if we take 2 steps it would be 10 + dp[2]
    so dp[0] = min(25, 30)
    finally we can return the min of (dp[0], dp[1]);
     */
    public int minCostClimbingStairsDP(int[] cost) {
        for (int i = cost.length - 2; i >= 0; i--) {
            int firstChoice = cost[i] + cost[i +1];
            int secondChoice = cost[i];
            if (i + 2 < cost.length) {
                secondChoice += cost[i+2];
            }
            cost[i] = Math.min(firstChoice, secondChoice);
        }
        return Math.min(cost[0], cost[1]);
    }

    public static void main(String[] args) {
        System.out.println(new MinCostClimbingStairs().minCostClimbingStairsDP(new int[] {1,100,1,1,1,100,1,1,100,1}));
    }
}
