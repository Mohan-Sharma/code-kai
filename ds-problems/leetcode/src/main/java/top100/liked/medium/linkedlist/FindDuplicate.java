package top100.liked.medium.linkedlist;

/**
 * @author Mohan Sharma
 */
public class FindDuplicate {

    // nums.length == n + 1 since the array contains n+1 elements in the range of 1..n
    // e.g. if no. of elements is 5 array will contain elements from 1 to 4. So we can use the element as index of the array
    // use the num as index so that we make the value as -ve. Hence let's say 2 repeats so at index 2 we will make
    // the value -ve first them then next time when 2 comes again it already -ve so 2 is the duplicate
    public int findDuplicateModifyArray(int[] nums) {
        for (int num : nums) {
            int idx = Math.abs(num);
            if (nums[idx] < 1)
                return idx;
            nums[idx] = -nums[idx];
        }
        return -1;
    }

    // nums = [2, 5, 9, 6, 9, 3, 8, 9, 7, 1]
    // basic idea is it's cyclic linked list if your consider the element of an index as next index to go to
    // so find the starting of the loop of the linkedlist
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(new FindDuplicate().findDuplicate(new int[] {1,3,4,2,2}));
    }
}
