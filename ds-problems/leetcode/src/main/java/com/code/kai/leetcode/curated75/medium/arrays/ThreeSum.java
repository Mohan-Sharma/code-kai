package com.code.kai.leetcode.curated75.medium.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mohan Sharma
 */
public class ThreeSum {

    //exceeds time due to power of 2 for large input
    private static List<List<Integer>> generateThreeSumsBitManipulation(int[] nums) {
        final Set<List<Integer>> subsets = new HashSet<>();
        int noOfSubsets = 1<<nums.length;
        for (int i = 0; i < noOfSubsets; i++) {
            final List<Integer> subset = new ArrayList<>();
            for (int j=0; j < nums.length; j++) {
                int mask = 1<<j;
                if ((i & mask) > 0) {
                    subset.add(nums[j]);
                }
            }
            if (subset.size() == 3 && (subset.get(0) + subset.get(1) + subset.get(2) == 0)) {
                Collections.sort(subset);
                subsets.add(subset);
            }
        }
        return new ArrayList<>(subsets);
    }

    //exceeds time due to power of 2 for large input
    private static void generateThreeSumsBacktracking(Set<List<Integer>> subsets, List<Integer> subset, int[] nums, int index) {
        if (index == nums.length) {
            if (subset.size() == 3 && (subset.get(0) + subset.get(1) + subset.get(2) == 0)) {
                Collections.sort(subset);
                subsets.add(subset);
            }
            return;
        }
        List<Integer> newSubset = new ArrayList<>(subset);
        newSubset.add(nums[index]);
        generateThreeSumsBacktracking(subsets, newSubset, nums, index+1);
        generateThreeSumsBacktracking(subsets, subset, nums, index + 1);
    }
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        //since 3 elems sum iterate till n-3 when i=n-3 then sum with other 2 remaining for last chance check
        for (int i = 0; i < nums.length - 2; i++) {
            // suppose for [-1 -1 -1 2 2] subset is already found with [-1 -1 2], to prevent duplicate skip all remaining -1
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int low = i + 1, high = nums.length-1, sum = 0 - nums[i];
                while (low < high) {
                    if (nums[low] + nums[high] == sum) {
                        subsets.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        //skip all similar digits to prevent duplicate
                        // e.g. [-1 -1 -1 2 2] since sum is found in [-1 -1 2] move the index to 3 to prevent duplicate
                        while (low < high && nums[low] == nums[low+1]) low++;
                        while (low < high && nums[high] == nums[high - 1]) high--;
                        low++;
                        high--;
                    } else if (nums[low] + nums[high] < sum) {
                        while (low < high && nums[low] == nums[low+1]) low++;
                        low++;
                    } else {
                        while (low < high && nums[high] == nums[high - 1]) high--;
                        high--;
                    }
                }
            }
        }
        return subsets;
    }

    //-4 -1 -1 0 1 2 2
    public static List<List<Integer>> twoSum(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        int low = 0, high = nums.length - 1;
        while (low < high) {
            if (low == 0 || ( low > 0 && nums[low] != nums[low - 1])) {
                if (nums[low] + nums[high] == 0) {
                    subsets.add(Arrays.asList(nums[low], nums[high]));
                    while (low < high && nums[low] == nums[low+1]) low++;
                    while (low < high && nums[high] == nums[high - 1]) high--;
                    low++;
                    high--;
                } else if (nums[low] + nums[high] < 0) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return subsets;
    }
    public static void main(String[] args) {
        final List<List<Integer>> subsets = threeSum(new int[]{-1,0,1,2,-1,-4});
        for (List<Integer> subset: subsets){
            System.out.println(subset);
        }
    }
}
