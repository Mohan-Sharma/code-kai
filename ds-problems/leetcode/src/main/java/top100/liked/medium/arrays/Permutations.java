package top100.liked.medium.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteRecursively(nums, result, new ArrayList<>());
        return result;
    }

    private void permuteRecursively(int[] nums, List<List<Integer>> result, List<Integer> set) {
        if (nums.length == set.size()) {
            result.add(new ArrayList<>(set));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
                permuteRecursively(nums, result, set);
                set.remove(set.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = new Permutations().permute(new int[] {1, 2, 3});
        for (List<Integer> set : result) {
            System.out.println(set);
        }
    }
}
