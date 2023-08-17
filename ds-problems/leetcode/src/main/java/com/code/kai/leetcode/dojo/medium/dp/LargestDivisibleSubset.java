package com.code.kai.leetcode.dojo.medium.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class LargestDivisibleSubset {

    /*
        If we analyse LIS algo we find that the same can be applied here.
        Now if we have [2, 4, 8] if 4 is divisible by 2 and 8 is divisible by 4 means 8 is also divisible by 2. From this
        we realise that if the numbers are in increasing order it would be helpful, so we sort it. In LIS we used the logic
        (nums[i] > nums[j]) && dp[j] + 1 > dp[i] where dp array holds the increase subsequence count. Here we can use
        (nums[i] % nums[j] == 0) && dp[j] + 1 > dp[i] to keep previous divisible count and at the same time keep track of
        max count index and max length
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        /*
            store count of previous divisible e.g. {1, 2, 3, 4}
            when i = 1, j = 0 dp[1,2,1,1] hash[-1, 0, -1, -1]
            when i = 2, j = 0 dp[1,2,2,1] hash[-1, 0, 0, -1], when i = 2, j = 1 dp[1,2,2,1] hash[-1, 0, 0, -1]
            when i = 3, j = 0 dp[1,2,2,2] hash[-1, 0, 0, 0], when i = 3, j = 1 dp[1,2,2,3] hash[-1, 0, 0, 1], when i = 3, j = 2 dp[1,2,2,3] hash[-1, 0, 0, 1]
            hence in dp, maxIndex = 3, maxLen = 3
            so using hash we can backtrack elements e.g. maxIndex was 3 so print nums[3], from hash[3] we get 1 now go to 1 print nums[1] from hash[1] we get 0
            go to 0 print nums[0] etc

         */
        int[] dp = new int[nums.length];
        int[] hash = new int[nums.length];
        Arrays.sort(nums);
        int maxLen = 1;
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            hash[i] = -1;
            for (int j = 0; j < i; j++) {
                if ((nums[i] % nums[j] == 0) && dp[j] + 1 > dp[i]) {
                    dp[i] = 1 + dp[j];
                    hash[i] = j;
                    if (dp[i] > maxLen) {
                        maxLen = dp[i];
                        maxIndex = i;
                    }
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        while (maxIndex != -1) {
            result.add(nums[maxIndex]);
            maxIndex = hash[maxIndex];
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new LargestDivisibleSubset().largestDivisibleSubset(new int[] {5,9,18,54,108,540,90,180,360,720}));
    }
}
