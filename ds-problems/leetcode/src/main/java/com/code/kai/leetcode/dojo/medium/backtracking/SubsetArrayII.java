package com.code.kai.leetcode.dojo.medium.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class SubsetArrayII {

    // General Idea: Subset means 2 branch one including the current elem and go to next and another not including the current elem
    // If elements are unique not including branch will never have same combination of elements e.g. [1, 2] || [2, 1]
    // if elements are not unique not including branch might have same combination of elements e.g. if input array is [1, 2, 2]
    // then repetition [1, 2] || [1, 2] again in not included branch. To overcome this increment the index as long as adjacent elems are similar
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        subsetsBacktracking(nums, new ArrayList<>(), 0, result);
        return result;
    }

    private void subsetsBacktracking(int[] nums, List<Integer> set, int index, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(set);
            return;
        }

        List<Integer> clone = new ArrayList<>(set);
        clone.add(nums[index]);
        subsetsBacktracking(nums, clone, index + 1, result);
        // the branch with included element already would have created the possible subset, so in the not included branch ignore all adjacent duplicate elements
        while (index + 1 < nums.length && nums[index] == nums[index + 1])
            index++;
        subsetsBacktracking(nums, set, index + 1, result);
    }

    public static void main(String[] args) {
        final List<List<Integer>> subsets = new SubsetArrayII().subsetsWithDup(new int[] {1, 2, 2});
        for (List<Integer> subset : subsets){
            System.out.println(subset);
        }
    }
}
