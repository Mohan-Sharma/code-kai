package top100.liked.hard.arrays;

/**
 * @author Mohan Sharma
 */
public class MedianTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == nums2.length && nums1.length == 0)
            return 0d;
        if (nums1.length > nums2.length)
            return getMedian(nums1, nums2);
        else
            return getMedian(nums2, nums1);
    }

    private double getMedian(int[] largeArray, int[] smallArray) {
        int mid = (largeArray.length + smallArray.length + 1) / 2;
        int start = 0, end = smallArray.length;
        while (start <= end) {
            int firstCut = (start + end) / 2;
            int secondCut = mid - firstCut;
            int leftFirstPart = firstCut == 0 ? Integer.MIN_VALUE : smallArray[firstCut - 1];
            int rightFirstPart = firstCut == smallArray.length ? Integer.MAX_VALUE : smallArray[firstCut];
            int leftSecondPart = secondCut == 0 ? Integer.MIN_VALUE : largeArray[secondCut -1];
            int rightSecondPart = secondCut == largeArray.length ? Integer.MAX_VALUE : largeArray[secondCut];
            if (leftFirstPart > rightSecondPart)
                end = firstCut - 1;
            else if (leftSecondPart > rightFirstPart)
                start = firstCut + 1;
            else {
                if ((largeArray.length + smallArray.length) % 2 == 0) {
                    return (Math.max(leftFirstPart, leftSecondPart) + Math.min(rightFirstPart, rightSecondPart)) / 2d;
                } else {
                    return Math.max(leftFirstPart, leftSecondPart);
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new MedianTwoSortedArrays().findMedianSortedArrays(new int[] {1, 3, 5}, new int[] {2, 4, 6}));
    }
}
