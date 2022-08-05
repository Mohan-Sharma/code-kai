package com.code.kai.leetcode.curated75.easy.arrays;

/**
 * @author Mohan Sharma
 */
public class SortedRotatedArray {
    public int findMin(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int start = 0, end = nums.length -1;
        while (start < end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] >  nums[end])
                start = mid + 1;
            else end = mid;
        }
        return nums[start];
    }

    public int findMax(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int start = 0, end = nums.length -1;
        while (start < end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] >  nums[end])
                end = mid;
            else start = mid + 1;
        }
        return nums[end];
    }

    public static void main(String[] args) {
        System.out.println(new SortedRotatedArray().findMax(new int[] {1, 2}));
    }
}
