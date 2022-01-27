package com.code.kai.leetcode.curated75.easy.bits;

/**
 * @author Mohan Sharma
 */
public class MissingNumber {

    public int missingNumber(int[] nums) {
        int result = nums.length;
        for (int i=0; i<nums.length; i++) {
            result = result ^ i ^ nums[i];
        }
        return result;
    }
}
