package com.code.kai.leetcode.dojo.medium.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class CombinationSumII {

    // Similar to subset array II
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combinationSumBackTracking(candidates, target, 0, result, new ArrayList<>());
        return result;
    }

    private void combinationSumBackTracking(int[] candidates, int target, int index, List<List<Integer>> result, List<Integer> set) {
        if (0 == target) {
            result.add(set);
            return;
        }
        if (index >= candidates.length || 0 > target)
            return;
        int elem = candidates[index];
        List<Integer> clone = new ArrayList<>(set);
        clone.add(elem);
        combinationSumBackTracking(candidates, target - elem, index + 1, result, clone);
        while (index + 1 < candidates.length && candidates[index] == candidates[index + 1])
            index++;
        combinationSumBackTracking(candidates, target, index + 1, result, set);
    }

    public static void main(String[] args) {
        final List<List<Integer>> subsets = new CombinationSumII().combinationSum(new int[] {10,1,2,7,6,1,5}, 8);
        for (List<Integer> subset : subsets){
            System.out.println(subset);
        }
    }
}
