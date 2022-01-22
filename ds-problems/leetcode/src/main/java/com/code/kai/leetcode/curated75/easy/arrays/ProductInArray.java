package com.code.kai.leetcode.curated75.easy.arrays;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class ProductInArray {
    public static int[] productExceptSelf(int[] nums) {

        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];

        int current = 1;
        prefix[0] = 1;
        suffix[nums.length - 1] = 1;

        for (int i = 1; i < nums.length; i++) {
            current = current * nums[i - 1];
            prefix[i] = current;
        }

        current = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            current = current * nums[i + 1];
            suffix[i] = current;
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = prefix[i] * suffix[i];
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[] {-1,1,0,-3,3})));
    }
}
