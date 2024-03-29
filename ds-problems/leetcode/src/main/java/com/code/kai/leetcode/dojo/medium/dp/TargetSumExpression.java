package com.code.kai.leetcode.dojo.medium.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class TargetSumExpression {

    public int findTargetSumWays(int[] nums, int target) {
        // cannot use int[][] dp why b/c there can be -ve values as well in recursion stack
        Map<String, Integer> dp = new HashMap<>();
        //return findTargetSumWaysBottomUpMemoization(nums, nums.length - 1, target, dp);
        return findTargetSumWaysBottomUp(nums, nums.length - 1, target);
    }

    private int findTargetSumWaysBottomUp(int[] nums, int index, int target) {
        if (index == 0) {
            if (target == 0 && nums[0] == 0) {
                return 2;
            }
            else if (target + nums[0] == 0 || target - nums[0] == 0)
                return 1;
            else return 0;
        }
        int minus = findTargetSumWaysBottomUp(nums, index - 1, target - nums[index]);
        int plus = findTargetSumWaysBottomUp(nums, index - 1, target + nums[index]);
        return minus + plus;
    }

    private int findTargetSumWaysBottomUpMemoization(int[] nums, int index, int target, Map<String, Integer> dp) {
        if (index == 0) {
            if (target == 0 && nums[0] == 0) {
                return 2;
            }
            else if (target + nums[0] == 0 || target - nums[0] == 0)
                return 1;
            else return 0;
        }
        String key = index + "-" + target;
        if (dp.containsKey(key))
            return dp.get(key);
        int minus = findTargetSumWaysBottomUpMemoization(nums, index - 1, target - nums[index], dp);
        int plus = findTargetSumWaysBottomUpMemoization(nums, index - 1, target + nums[index], dp);
        int count = minus + plus;
        dp.put(key, count);
        return count;
    }

    //does not work, ans it at dp.get((nums.length - 1) + "-" + target - 1)
    private int findTargetSumWaysTabulation(int[] nums, int target) {
        int length = nums.length;
        int[][] dp = new int[length][target + 1];
        if (nums[0] == 0) {
            dp[0][0] = 2;
        } else {
            dp[0][0] = 1;
        }
        for (int i = 1; i <= target; i++) {
            if (nums[0] != 0 && (i + nums[0] == 0 || i - nums[0] == 0))
                dp[0][i] = 1;
        }

        for (int i = 1; i < length; i++) {
            for (int j = 0; j <= target; j++) {
                int minus = j >= nums[i] ? dp[i - 1][j - nums[i]] : 0;
                int plus = j + nums[i] <= target ? dp[i - 1][j + nums[i]] : 0;
                dp[i][j] = minus + plus;
            }
        }
        return dp[length - 1][target];
    }

    public static void main(String[] args) {
        System.out.println(new TargetSumExpression().findTargetSumWaysTabulation(new int[] {1,1,1,0,0,0,0,0,0}, 1));
    }
}
