package com.code.kai.leetcode.dojo.medium.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class CombinationSum {

    // since the same number be included again and again, the same number becomes eligible for multiple
    // selection. So don't increment startIndex unless boundary condition met. Index 0 select and recurs again to select index 0
    // and so on unless curTarget > target || startIndex >=  numsLength. When boundary condition is met and returned, ignore this startIndex
    // so it does not cause duplicate e.g. [2, 2, 3] = 7 and [3, 2, 2] = 7. So once boundary is reached and return, increment startIndex
    // so that the previous element which was already included in last recursions,  would never appear in new combinations.
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSumBackTracking(candidates, target, 0, 0, result, new ArrayList<>());
        return result;
    }

    private void combinationSumBackTracking(int[] candidates, int target, int curTarget, int start, List<List<Integer>> result, List<Integer> set) {
        if (curTarget == target) {
            result.add(set);
            return;
        }
        if (start >= candidates.length || curTarget > target)
            return;
        int elem = candidates[start];
        curTarget += elem;
        List<Integer> clone = new ArrayList<>(set);
        clone.add(elem);
        combinationSumBackTracking(candidates, target, curTarget, start, result, clone);
        combinationSumBackTracking(candidates, target, curTarget - elem, start + 1, result, set);
    }

    public static void main(String[] args) {
        final List<List<Integer>> subsets = new CombinationSum().combinationSum(new int[] {2, 3, 6, 7}, 7);
        for (List<Integer> subset : subsets){
            System.out.println(subset);
        }
    }
}
