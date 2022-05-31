package top100.liked.medium.binarysearch;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        // ideally we can use brute force to find the speed between 1 to max(piles)
        // but instead of iterating over all the 1 ... max speed settings why not
        // use binary search since the ints are sorted
        int low = 1, high = getMaxPile(piles);
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (canEatAll(piles, mid, h)) {
                // why move left when found a value b/c we need the minimum integer k
                // such that she can eat all the bananas within h hours.
                high = mid - 1;
            } else {
                // cannot eat all piles since speed might be low in the provided hour h
                // hence increase the speed by moving right
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean canEatAll(int[] piles, int speed, int hours) {
        int count = 0;
        for (int pile : piles) {
            count += pile / speed;
            if (pile % speed != 0)
                count++;
        }
        return count <= hours;
    }

    private int getMaxPile(int[] piles) {
        return Arrays.stream(piles).max().getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(new KokoEatingBananas().minEatingSpeed(new int[] {30,11,23,4,20}, 6));
    }
}
