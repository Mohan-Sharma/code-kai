package com.code.kai.leetcode.dojo.hard.arrays;

/**
 * @author Mohan Sharma
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        int result = 0, leftPointer = 0, rightPointer = height.length - 1,
                maxLeft = height[leftPointer], maxRight = height[rightPointer];
        while (leftPointer <= rightPointer) {
            if (maxLeft <= maxRight) {
                int curLeftHeight = height[leftPointer];
                if (maxLeft - curLeftHeight > 0) {
                    result += (maxLeft - curLeftHeight);
                }
                maxLeft = Math.max(maxLeft, curLeftHeight);
                leftPointer++;
            }
            else {
                int curRightHeight = height[rightPointer];
                if (maxRight - curRightHeight > 0) {
                    result += (maxRight - curRightHeight);
                }
                maxRight = Math.max(maxRight, curRightHeight);
                rightPointer--;
            }
        }
        return result;
    }

    // at any building to get the left high rise, we can see if adjacent left is high or previous encounter building was high
    // and similar to get right height of a building see if adjacent right building is high or previous encounter building was high
    // since we are measuring all buildings in the locality
    public int trapWithSpace(int[] height) {
        int result = 0;
        if (height.length == 0)
            return result;
        int[] leftBoundary = new int[height.length];
        int maxLeft = height[0];
        for (int i = 1; i < height.length; i++) {
            leftBoundary[i - 1] = Math.max(height[i - 1], maxLeft);
            maxLeft = Math.max(maxLeft, height[i]);
        }

        int[] rightBoundary = new int[height.length];
        int maxRight = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            rightBoundary[i + 1] = Math.max(height[i + 1], maxRight);
            maxRight = Math.max(maxRight, height[i]);
        }

        for (int i = 0; i < height.length; i++) {
            int water = Math.min(leftBoundary[i], rightBoundary[i]) - height[i];
            if (water > 0) {
                result += water;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new TrappingRainWater().trap(new int[]{4,2,0,3,2,5}));
    }
}
