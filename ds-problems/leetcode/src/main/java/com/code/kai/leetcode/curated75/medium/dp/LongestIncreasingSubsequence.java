package com.code.kai.leetcode.curated75.medium.dp;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        //return lengthOfLISBottomUp(nums, 0, -1);
        int[][] dp = new int[nums.length][nums.length + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return lengthOfLISBottomUpMemoization(nums, 0, -1, dp);
    }

    private static int lengthOfLISBottomUp(int[] nums, int index, int prevIndex) {
        if (index >= nums.length) return 0;
        int notPick = lengthOfLISBottomUp(nums, index + 1, prevIndex);
        int pick = Integer.MIN_VALUE;
        if (prevIndex == -1 || nums[index] > nums[prevIndex])
            pick = 1 + lengthOfLISBottomUp(nums, index + 1, index);

        return Math.max(notPick, pick);
    }

    private static int lengthOfLISBottomUpMemoization(int[] nums, int index, int prevIndex, int[][] dp) {
        if (index >= nums.length) return 0;
        if (dp[index][prevIndex + 1] != -1)
            return dp[index][prevIndex + 1]; // handle -1 for previndex
        int notPick = lengthOfLISBottomUpMemoization(nums, index + 1, prevIndex, dp);
        int pick = Integer.MIN_VALUE;
        if (prevIndex == -1 || nums[index] > nums[prevIndex])
            pick = 1 + lengthOfLISBottomUpMemoization(nums, index + 1, index, dp);

        return dp[index][prevIndex + 1] = Math.max(notPick, pick);
    }

    private int lengthOfLISTabulation(int[] nums) {
        // since -1 prev index cannot be handled, we shift the cols by 1 so for -1 we store in 0th col and so on
        int len = nums.length;
        int[][] dp = new int[len + 1][len + 1];
        for (int index = len - 1; index >= 0; index--) {
            for (int prevIndex = index - 1; prevIndex >= -1; prevIndex--) {
                int notPick = dp[index + 1][prevIndex + 1];
                int pick = Integer.MIN_VALUE;
                if (prevIndex == -1 || nums[index] > nums[prevIndex])
                    pick = 1 + dp[index + 1][index + 1];
                dp[index][prevIndex + 1] = Math.max(notPick, pick);
            }
        }
        return dp[0][0]; // should be dp[0][-1] but since prev index shifted by 1 hence dp[0][-1+1]
    }

    private int lengthOfLISTabulationSpaceOptimized(int[] nums) {
        // since -1 prev index cannot be handled, we shift the cols by 1 so for -1 we store in 0th col and so on
        int[] dp = new int[nums.length + 1];
        for (int index = nums.length - 1; index >= 0; index--) {
            int[] tempDP = new int[dp.length];
            for (int prevIndex = index - 1; prevIndex >= -1; prevIndex--) {
                int notPick = dp[prevIndex + 1];
                int pick = Integer.MIN_VALUE;
                if (prevIndex == -1 || nums[index] > nums[prevIndex])
                    pick = 1 + dp[index + 1];
                tempDP[prevIndex + 1] = Math.max(notPick, pick);
            }
            dp = tempDP;
        }
        return dp[0]; // should be dp[0][-1] but since prev index shifted by 1 hence dp[0][-1+1]
    }

    /*
        Intuition: for an index we can if previous index element is small, if small then we count becomes 1 + previously index stored count
        that means we can initially have a dp[1,1,1,1,1] for [7, 6, 1, 2, 3] which means for each array index element individually LIS is of 1
        now let's start a loop from index=1 to n and another loop from prev=0 to index - 1
        Now let's say index=2 and prev runs from 0 to 1, since both elements at index 0 and 1 are greater than index 2 no update required in dp array
        when index=3, and prev runs from 0 to 2 when prev becomes 2 than elem at index 2 is small than index 3 hence we can update dp
        like dp[index] = Math.max(dp[index], 1 + dp[prev]) so dp[1,1,1,2,1] now then index is 4 prev runs from 0 to 3. When prev is 2 then dp becomes
        dp[1,1,1,2,2] then when prev is 3 dp beccomes dp[1,1,1,2,3]
     */
    public int lengthOfLISBackTrack(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) { // tracking prev and updating dp
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }

    //O(nlogn)
    /*
        array to store increasing order elements. e.g. [4, 2, 1, 3, 6]
        Let's create a subsequence array of same size and store the 0th index element dp[8]
        when i=1 we check if 2 is greater than 4 which is not, so what we can do is create another subsequence array like [2]
        When i=2 we check if 1 is greater than either of the last element of prev subsequences which is not so create another array, so it becomes 3 arrays now - [4], [2], [1]
        when i=3, we check if 3 is greater than the last elements of prev subsequence which is true for only 2nd, 3rd array, so we put it there -  [4], [2, 3], [1, 3]
        when i=4, we check if 6 is greater than the last elements of prev subsequence which is true for all prev arrays, so we put it there -  [4, 6], [2, 3, 6], [1, 3, 6]
        hence LIS is length 3
        So the intuition was whenever an element is greater than previous subsequence we cut a subsequence in such a way that it becomes sorted. e.g. [1, 7, 8, 4, 5] when we reach 4
        we create subsequence like [1, 7, 8] and [1, 4]. Now instead of creating a new subsequence why don't we replace 7 with 4 so it becomes [1, 4, 8] at index 3 and for index 5 it
        becomes [1, 4, 5] replace 8 with 5 since 5 is smaller than 8 and greater than 4. This is where binary search will come.
        Remember this cannot be used to print LIS ony can be used to find length of LIS
     */
    private int lengthOfLISBinarySearch(int[] nums) {
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

    private int findByBinarySearch(int[] lis, int low, int high, int target) {
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
        System.out.println(new LongestIncreasingSubsequence().lengthOfLISBackTrack(new int[]{10,9,2,5,3,7,101,18}));
    }
}
