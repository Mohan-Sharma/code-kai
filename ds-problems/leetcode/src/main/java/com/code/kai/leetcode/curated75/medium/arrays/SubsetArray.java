package com.code.kai.leetcode.curated75.medium.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class SubsetArray {

    public static List<List<Integer>> generateSubsetsUsingBacktracking(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        generateSubsetsUsingBacktracking(subsets, new ArrayList<>(), 0, nums);
        return subsets;
    }

    private static void generateSubsetsUsingBacktracking(List<List<Integer>> subsets, List<Integer> subset, int startIndex, int[] nums) {
        if (startIndex == nums.length) {
            subsets.add(subset);
            return;
        }
        List<Integer> clone = new ArrayList<>(subset);
        Integer num = nums[startIndex];
        clone.add(num);
        generateSubsetsUsingBacktracking(subsets, clone, startIndex + 1, nums);
        generateSubsetsUsingBacktracking(subsets, subset, startIndex + 1, nums);
    }

    public static List<List<Integer>> generateSubArraysUsingBinary(int[] nums) {
        int noOfSubsets = 1<<nums.length;
        final List<List<Integer>> subsets = new ArrayList<>(noOfSubsets);
        for (int i = 0; i < noOfSubsets; i++)
        {
            final List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) > 0)
                    subset.add(nums[j]);
            }
            subsets.add(subset);
        }
        return subsets;
    }

    public static void main(String[] args) {
        final List<List<Integer>> subsets = generateSubArraysUsingBinary(new int[] {1,2,3});
        for (List<Integer> subset : subsets){
            System.out.println(subset);
        }
    }
}
