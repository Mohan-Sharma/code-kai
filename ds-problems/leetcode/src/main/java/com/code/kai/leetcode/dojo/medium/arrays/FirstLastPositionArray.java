package com.code.kai.leetcode.dojo.medium.arrays;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class FirstLastPositionArray {

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0)
            return new int[] {-1, -1};
        // check the binary search function below to understand the actual logic.
        // We are calling binary seach twice. For the first occurrance scan left part and
        // For the last occurrance scan right part
        return new int[] { binarySearch(nums, target, true), binarySearch(nums, target, false)};
    }

    // Even if we find the target, we need to check once in the right side for higher index
    // and once in the left side for lower index.
    // if leftBias is true search in the left part and if it is false search in right part
    private int binarySearch(int[] nums, int target, boolean leftBias) {
        int index = -1;
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (nums[mid] > target) {
                end = mid -1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            }
            else {
                // mid element equals to target, in traditional binary search we stop here and return mid index.
                index = mid;
                // but for this problem if we want to search for the first occurring index search in left part again
                // hence we do end = mid - 1 means search again in left part for lower index
                if (leftBias) {
                    end = mid - 1;
                }
                // but for this problem if we want to search for the last occurring index search in right part again
                // hence we do start = mid + 1 means search again in right part for higher index
                else {
                    start = mid + 1;
                }
            }
        }
        // return the found index or -1
        return index;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FirstLastPositionArray().searchRange(new int[] {5,7,7,8,8,10}, 8)));
    }
}
