package com.code.kai.leetcode.curated75.easy.arrays;

/**
 * @author Mohan Sharma
 */
public class SearchSortedRotatedArray {

    //[4,5,6,7,0,1,2]
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        int length = nums.length - 1;
        int rotatedIndex = getRotatedIndex(nums);
        int start = nums[length] >= target ? rotatedIndex : 0;
        int end = nums[length] < target ? rotatedIndex : length;
        return binarySearch(nums, start, end, target);
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        // search will start <= end bcs end is also valid index here unlike end = len - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] < target)
                start = mid + 1;
            else if (nums[mid] > target)
                end = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    private int getRotatedIndex(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] > nums[end])
                start = mid + 1;
            else
                end = mid;
        }
        return start;
    }

    public static void main(String[] args) {
        System.out.println(new SearchSortedRotatedArray().search(new int[] {1, 3}, 3));
    }
}
