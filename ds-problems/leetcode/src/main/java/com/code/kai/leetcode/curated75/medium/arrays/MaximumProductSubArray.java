package com.code.kai.leetcode.curated75.medium.arrays;

/**
 * @author Mohan Sharma
 */
public class MaximumProductSubArray {
    //2,3,-2,4
    public static int maxProduct(int[] nums) {
        if (nums.length == 0)
            return 0;
        int max =  nums[0], runningMax =  nums[0], runningMin = nums[0];
        for(int i=1; i<nums.length; i++){
            if(nums[i] < 0){ int tmp = runningMax; runningMax = runningMin; runningMin = tmp;}
            runningMax = Math.max(runningMax*nums[i], nums[i]);
            runningMin = Math.min(runningMin*nums[i], nums[i]);

            max = Math.max(max, runningMax);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[] {-2, 1, -4, -3}));
    }
}
