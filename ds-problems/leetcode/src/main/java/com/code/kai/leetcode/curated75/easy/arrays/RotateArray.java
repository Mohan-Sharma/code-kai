package com.code.kai.leetcode.curated75.easy.arrays;

import java.util.Arrays;

public class RotateArray {

    public static int[] rotateUsingSpace(int[] nums, int k) {
        int[] first = Arrays.copyOf(nums, nums.length);
        System.arraycopy(first, nums.length - k, nums, 0, k);
        System.arraycopy(first, 0, nums, k, nums.length - k);
        return nums;
    }

    public static void rotate(int[] nums, int k) {
        k %= nums.length;
        int index = 0;
        int count = nums.length - k;
        while (count > index && index < k) {
            swap(count, count - 1, nums);
            count--;
            if (count == index) {
                index++;
                count = nums.length - k + index;
            }
        }
    }

    public static void rotateUsingReverse(int[] nums, int k) {
        //reverse the whole array
        k %= nums.length;
        reverseArr(nums, 0, nums.length - 1);
        reverseArr(nums, 0, k - 1);
        reverseArr(nums, k, nums.length - 1);
    }

    private static void reverseArr(int[] nums, int start, int end) {
        while (end > start) {
            swap(start, end, nums);
            start++;
            end--;
        }
    }

    private static void swap(int k, int s, int[] nums) {
        int temp = nums[k];
        nums[k] = nums[s];
        nums[s] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(rotateUsingSpace(arr, 3)));
    }
}
