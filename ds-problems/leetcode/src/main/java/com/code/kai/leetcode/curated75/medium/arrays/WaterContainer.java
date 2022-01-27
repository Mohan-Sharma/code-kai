package com.code.kai.leetcode.curated75.medium.arrays;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class WaterContainer {

    public static int maxArea(int[] height) {
        return maxWaterContainerSorting(height);
    }

    private static int maxWaterContainerSorting(int[] height) {
        Arrays.sort(height);
        int maxArea = 0;
        if (height.length == 0)
            return maxArea;
        for (int i = 0; i < height.length; i++) {
            int minHeight = Math.min(height[i], height[height.length - 1]);
            int area = minHeight * (height.length - 1 - i);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
    //exceeds times
    private static int maxWaterContainerBruteForce(int[] height) {
        int maxArea = 0;
        if (height.length == 0)
            return maxArea;
        for (int i = 0; i < height.length; i++) {
            for (int j = height.length - 1; j >= 0 ; j--) {
                int minHeight = Math.min(height[i], height[j]);
                int area = minHeight * (j - i);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
