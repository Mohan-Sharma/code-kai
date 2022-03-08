package com.code.kai.leetcode.curated75.medium.dp;

/**
 * @author Mohan Sharma
 */
public class CombinationSum {
    public static int combinationSum4(int[] nums, int target) {
        return combinationSumBottomUp(nums, target, new int[target + 1]);
    }

    private static int combinationSumBottomUp(int[] nums, int target, int[] dp ) {
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] = dp[i] + dp[i - num];
                }
            }
        }
        return dp[target];
    }

    private static int combinationSumTopDown(int[] nums, int target, int[] dp) {
        int count = 0;
        if (target == 0) {
            return 1;
        }
        if (dp[target] > 0)
            return dp[target];
        for (int num : nums) {
            if (target - num >= 0) {
                count += combinationSumTopDown(nums, target-num, dp);
                dp[target] = count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(combinationSum4(new int[]{1, 2, 3}, 4));
    }
}
