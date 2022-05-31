package top100.liked.medium.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author Mohan Sharma
 */
public class DailyTemperatures {

    public int[] dailyTemperaturesBruteForce(int[] temperatures) {
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length - 1; i++) {
            int days = 0;
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[i] < temperatures[j]) {
                    days = j - i;
                    break;
                }
            }
            result[i] = days;
        }
        return result;
    }

    // think of it like if you reach nth height, from this height while the previous height is lower
    // then nth height substract and store result. So there should be a way to get previous stored indexes
    // in LIFO manner hence stack if current height is less then previous height just keep storing in the stack
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = new DailyTemperatures().dailyTemperaturesBruteForce(new int[] {73,74,75,71,69,72,76,73});
        System.out.println(Arrays.toString(result));
    }
}
