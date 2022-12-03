package com.code.kai.leetcode.dojo.medium.dp;

/**
 * @author Mohan Sharma
 */
public class NumberOfLIS {

    public int numberOfLIS(int[] arr) {
        int length = arr.length;
        int[] len = new int[length];
        int[] count = new int[length];
        int maxLen = 1, result = 0;
        for (int i = 0; i < length; i++) {
            // initially the lis length is 1 and count is 1 till that index
            len[i] = count[i] = 1;
            int curNum =  arr[i];
            /*
                if 1 + len[j] > len[i] than we know that including the element at i LIS will increase by 1
                that means the count at i will remain same s the element at index j suppose at j LIS was 3 and count was 2
                means for length 3, no of subsequences with length 3 was 2 and even if I add one more element for index i,
                count will be 2 but now the LIS length will increase by 1.

                but if 1 + len[j] == len[i] means one more subsequence will same length so increment
             */
            for (int j = 0; j < i; j++) {
                if (curNum > arr[j] && 1 + len[j] > len[i]) {
                    len[i] = 1 + len[j];
                    count[i] = count[j];
                } else if (curNum > arr[j] && 1 + len[j] == len[i]) {
                    count[i] += count[j];
                }
            }
            if (maxLen == len[i]) result += count[i];
            if (maxLen < len[i]) {
                maxLen = len[i];
                result = count[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfLIS().numberOfLIS(new int[] {1, 5, 4, 3, 2, 6, 7, 2}));
    }
}
