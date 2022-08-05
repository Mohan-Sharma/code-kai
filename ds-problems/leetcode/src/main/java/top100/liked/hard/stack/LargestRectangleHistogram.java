package top100.liked.hard.stack;

import java.util.Stack;

/**
 * @author Mohan Sharma
 */
public class LargestRectangleHistogram {

    // in the area at least one of the heights will be completely included. Let's assume
    // it is the smallest height and we need to find the span left to current position
    public int largestRectangleAreaBruteForce(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minH = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minH = Math.min(minH, heights[j]);
                int curArea = minH * (j - i + 1);
                maxArea = Math.max(maxArea, curArea);
            }
        }
        return maxArea;
    }

    // think of it as if the current height is > previous height means there is a possibility
    // of increasing area but if less then calculate the previous height area then next to previous
    // and so on. e.g [3, 1, 2, 3, 4, 1] so when we reach 1st index we calculate 3 * (1th - 0th) index but
    // when we come to 2 area can be increased in future we are not sure yet so push to ds but
    // when we reach 5th index, 4 height cannot be expanded horizontally anymore, so start calculating
    // prev height area. So first it should be 4 * (5th - 4th) then 3 * (5th - 3rd) index and so on until
    // the ds is empty. Since we required the LIFO elements hence stack
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= heights.length; ) {
            int curHeight = i == heights.length ? 0 : heights[i];
            if (stack.isEmpty() || heights[stack.peek()] <= curHeight) {
                stack.push(i);
                i++;
            } else {
                int height = heights[stack.pop()];
                int index = stack.isEmpty() ? 0 : stack.peek() + 1;
                maxArea = Math.max(maxArea, height * (i - index));
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(new LargestRectangleHistogram().largestRectangleArea(new int[] {2,4}));
    }
}
