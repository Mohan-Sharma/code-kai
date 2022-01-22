package com.code.kai.leetcode.curated75.easy.arrays;

/**
 * @author Mohan Sharma
 */
public class SubArraySum {
    //Kadence algo [-1, -2]
    public int maxSubArray(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int prevSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prevSum = Math.max(nums[i], prevSum + nums[i]);
            if (prevSum > maxSum)
                maxSum = prevSum;
        }
        return maxSum;
    }
}
