package com.code.kai.leetcode.curated75.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class LongestIncreasingSubsequence {

    public static int lengthOfLIS(int[] nums) {
        return lengthOfLISBinarySearch(nums);
    }

    //O(n2)
    private static int lengthOfLISBottomUp(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = nums.length-2; i >= 0 ; i--) {
            for (int j =i+1; j<nums.length; j++) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    //O(nlogn)
    private static int lengthOfLISBinarySearch(int[] nums) {
        int[] lis = new int[nums.length];
        int lastIndexOfSeq = 0;
        lis[lastIndexOfSeq++] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > lis[lastIndexOfSeq - 1])
                lis[lastIndexOfSeq++] = nums[i];
            else {
                int position = findByBinarySearch(lis, 0, lastIndexOfSeq - 1, nums[i]);
                lis[position] = nums[i];
            }
        }
        return lastIndexOfSeq;
    }

    private static int findByBinarySearch(int[] lis, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (lis[mid] == target)
                return mid;
            else if (lis[mid] > target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{0,1,0,3,2,3}));
    }
}
