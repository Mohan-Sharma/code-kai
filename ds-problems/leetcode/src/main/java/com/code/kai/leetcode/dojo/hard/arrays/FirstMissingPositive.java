package com.code.kai.leetcode.dojo.hard.arrays;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mohan Sharma
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0)
                nums[i] = 0;
        }
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]);
            if (index > 0 && index <= nums.length) {
                if (nums[index - 1] > 0)
                    nums[index - 1] = -1 * nums[index - 1];
                else if (nums[index - 1] == 0) {
                    nums[index - 1] = -1 * (nums.length + 1);
                }
            }
        }

        for (int i = 1; i <= nums.length; i++) {
            if (nums[i - 1] >= 0)
                return i;
        }
        return nums.length + 1;
    }

    public int firstMissingPositiveBySorting(int[] nums) {
        Arrays.sort(nums);
        int num = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num)
                num++;
        }
        return num;
    }

    public int firstMissingPositiveWithSpace(int[] nums) {
        Set<Integer> numbers = Arrays.stream(nums).boxed().filter(num -> num > 0).collect(Collectors.toSet());
        for (int i = 1; i < nums.length + 1; i++) {
            if (!numbers.contains(i))
                return i;
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        System.out.println(new FirstMissingPositive().firstMissingPositive(new int[] {1}));
    }
}
