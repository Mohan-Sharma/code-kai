package com.code.kai.leetcode.curated75.medium.arrays;

/**
 * @author Mohan Sharma
 */
public class MinSizeSubArraySumK {

    public static int minSubArrayLen(int target, int[] nums) {
        int start = 0, end = nums.length, left = start, minLength = Integer.MAX_VALUE, runningSum = 0;

        while (start < end) {
            runningSum += nums[start];
            while (runningSum >= target) {
                minLength = Math.min(minLength, start - left + 1);
                runningSum -= nums[left++];
            }
            start++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(15, new int[]{-13, -7, -7, 2,  9, 12, 13, 17, 28, 28}));
    }
}
