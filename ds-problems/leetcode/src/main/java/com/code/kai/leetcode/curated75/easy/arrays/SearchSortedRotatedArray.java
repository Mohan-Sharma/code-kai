package com.code.kai.leetcode.curated75.easy.arrays;

/**
 * @author Mohan Sharma
 */
public class SearchSortedRotatedArray {

    //[4,5,6,7,0,1,2]
    public int search(int[] nums, int target) {
        if (nums.length == 0 || (nums.length == 1 && target != nums[0]))
            return -1;
        if (nums.length == 1 && target == nums[0])
            return 0;
        int rotatedIndex = getRotatedIndex(nums);
        int length = nums.length - 1;
        int start = target <= nums[length] ? rotatedIndex : 0;
        int end = target > nums[length] ? rotatedIndex : length;

        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target) {
                start = mid + 1;
            } else end = mid - 1;
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
