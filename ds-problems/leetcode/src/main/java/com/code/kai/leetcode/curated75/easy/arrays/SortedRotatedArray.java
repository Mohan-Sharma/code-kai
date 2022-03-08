package com.code.kai.leetcode.curated75.easy.arrays;

/**
 * @author Mohan Sharma
 */
public class SortedRotatedArray {
    public static int findMin(int[] nums) {
        int result = -1;
        if (nums.length == 1)
            return nums[0];
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            if (nums[start] <= nums[end]) {
                result = nums[start];
                break;
            }
            int mid =  (start + end)/2;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return result;
    }

    public static int findMax(int[] nums) {
        int result = -1;
        if (nums.length == 1)
            return nums[0];
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            if (nums[start] <= nums[end]) {
                result = nums[end];
                break;
            }
            int mid =  (start + end)/2;
            if (nums[mid] > nums[end]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[] {2, 1}));
    }
}
