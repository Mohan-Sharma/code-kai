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
        int length = height.length;
        if (length == 0)
            return result;
        int[] leftBoundary = new int[length];
        int maxLeft = height[0];
        for (int i = 1; i <= length; i++) {
            leftBoundary[i - 1] = Math.max(height[i - 1], maxLeft);
            if (i < length)
                maxLeft = Math.max(maxLeft, height[i]);
        }

        int[] rightBoundary = new int[length];
        int maxRight = height[length - 1];
        for (int i = length - 2; i >= -1; i--) {
            rightBoundary[i + 1] = Math.max(height[i + 1], maxRight);
            if (i > -1)
                maxRight = Math.max(maxRight, height[i]);
        }

        for (int i = 0; i < length; i++) {
            int water = Math.min(leftBoundary[i], rightBoundary[i]) - height[i];
            if (water > 0) {
                result += water;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new TrappingRainWater().trapWithSpace(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
