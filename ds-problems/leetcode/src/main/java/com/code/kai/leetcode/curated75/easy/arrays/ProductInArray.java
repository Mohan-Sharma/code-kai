package com.code.kai.leetcode.curated75.easy.arrays;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class ProductInArray {
    public static int[] productExceptSelf(int[] nums) {
        int curProduct = 1;
        int[] prefix = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            prefix[i] = curProduct;
            curProduct *= nums[i];
        }

        curProduct = 1;
        int[] suffix = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            suffix[i] = curProduct;
            curProduct *=  nums[i];
            // since suffix, prefix already computed for this index why use another loop, populate it here
            nums[i] = prefix[i] * suffix[i];
        }

        return nums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[] {1, 2, 3, 4})));
    }
}
