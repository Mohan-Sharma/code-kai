package com.code.kai.leetcode.curated75.medium.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class SubsetArray {

    public static List<List<Integer>> generateSubsetsUsingBacktracking(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        generateSubsetsUsingBacktracking(subsets, new ArrayList<>(), nums, 0);
        return subsets;
    }

    // suppose element is [1, 2, 3] first we will process 1 and add to the subset and once not add to the subset, when we don't add
    // it remains same as the passed subset
    // the pass 2 and 3 and so on...
    private static void generateSubsetsUsingBacktracking(List<List<Integer>> subsets, List<Integer> subset, int[] nums, int startIndex) {
        if (startIndex == nums.length) {
            subsets.add(subset);
            return;
        }
        // adding element to list
        List<Integer> clone = new ArrayList<>(subset);
        clone.add(nums[startIndex]);
        generateSubsetsUsingBacktracking(subsets, clone, nums, startIndex + 1);
        // not adding element to list
        generateSubsetsUsingBacktracking(subsets, subset, nums, startIndex + 1);
    }

    private static void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // if i = 1 then 001 and j when 0, 1<<j = 001, 001 & 001 > 0 so add nums[j] to subset. Again when i = 3 [011], j=0
    // then 1 << j = 001. So 011 & 001 > 0 add to subset. then j becomes 1, 1 << j= 010 so 011 & 010 > 0, add to subset.
    public static List<List<Integer>> generateSubArraysUsingBinary(int[] nums) {
        int noOfSubsets = 1<<nums.length;
        final List<List<Integer>> subsets = new ArrayList<>(noOfSubsets);
        for (int i = 0; i < noOfSubsets; i++)
        {
            final List<Integer> subset = new ArrayList<>();
            if (i > 0) {
                for (int j = 0; j < nums.length; j++) {
                    if ((i & (1 << j)) > 0)
                        subset.add(nums[j]);
                }
            }
            subsets.add(subset);
        }
        return subsets;
    }

    public static void main(String[] args) {
        final List<List<Integer>> subsets = generateSubsetsUsingBacktracking(new int[] {1,2,3});
        for (List<Integer> subset : subsets){
            System.out.println(subset);
        }
    }
}
