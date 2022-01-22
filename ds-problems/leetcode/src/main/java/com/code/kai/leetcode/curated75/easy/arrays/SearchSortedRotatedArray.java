package com.code.kai.leetcode.curated75.easy.arrays;

/**
 * @author Mohan Sharma
 */
public class SearchSortedRotatedArray {

    //[4,5,6,7,0,1,2]
    public static int search(int[] nums, int target) {
        if (nums.length == 0 || (nums.length == 1 && target != nums[0]))
            return -1;
        if (nums.length == 1 && target == nums[0])
            return 0;
        int rotatedIndex = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            if (nums[start] <= nums[end]) {
                rotatedIndex = start;
                break;
            }
            int mid = (start + end)/2;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        if (rotatedIndex > -1) {
            if (nums[rotatedIndex] <= target && nums[nums.length - 1] >= target) {
                return binarySearch(rotatedIndex, nums.length - 1, target, nums);
            } else {
                return binarySearch(0, rotatedIndex - 1, target, nums);
            }
        }
        return -1;
    }

    private static int binarySearch(int start, int end, int target, int[] nums) {
        while (start <= end) {
            int mid = (start + end)/2;
            if (nums[mid] == target)
                return mid;
            else if(nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[] {4,5,6,7,0,1,2}, 3));
    }
}
