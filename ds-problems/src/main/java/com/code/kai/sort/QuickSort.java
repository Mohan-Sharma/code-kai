package com.code.kai.sort;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class QuickSort {

    public void quickSort(int[] nums, int start, int end) {
        if (start >= end)
            return;
        int pIndex = partition(nums, start, end);
        quickSort(nums, start, pIndex - 1);
        quickSort(nums, pIndex + 1, end);
    }

    private int partition(int[] nums, int start, int end) {
        if (start >= end) {
            return -1;
        }
        int pivot = nums[end], pIndex = start;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                swap(nums, pIndex, i);
                pIndex++;
            }
        }
        swap(nums, pIndex, end);
        return pIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 1, 6, 8, 5, 3, 4};
        new QuickSort().quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
