package com.code.kai.leetcode.curated75.easy.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DuplicateInArray {

    public static int removeDuplicates(int[] nums) {
        int index = 0;
        int i = 0;
        while (i < nums.length - 1) { //[0,0,1,1,1,2,2,3,3,4]
            int currentElement = nums[i];
            int itr = i + 1;
            while (currentElement == nums[itr]) {
                itr++;
            }
            nums[index++] = currentElement;
            i = itr;
        }
        return index;
    }

    public static int optimizedRemoveDuplicates(int[] nums) {
        int index = 0;
        for (int i=1; i< nums.length; i++) {
            if(nums[i] != nums[i -1]) {
                index++;
                nums[index] = nums[i];
            }
        }
        return index + 1;
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> storage = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!storage.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr =  { 0,0,1,1,1,2,2,3,3,4 };
        System.out.println(optimizedRemoveDuplicates(arr));
        System.out.println(Arrays.toString(arr));
    }
}
