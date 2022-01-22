package org.msharma.algorithm.priority_queue;

/**
 * @author Mohan Sharma
 */
public class CheckIfArrayMinHeap {

    public static boolean checkIfMinHeap(int[] arr, int index) {
        // leaf nodes always satisfy
        if(2 * index + 2 > arr.length) {
            return true;
        }
        boolean left = arr[index] < arr[2 * index + 1];
        // check if right child exists if does not exist means its always satisfy
        /**
         * e.g       2
         *      3           4
         *  5     10     15
         *
         * in this case 2*i + 2 > 6 for i = 2 is false but there is no right child hence array out of bound.
         */
        boolean right = 2 * index + 2 >= arr.length || arr[index] < arr[2 * index + 2];
        return left &  right && checkIfMinHeap(arr, 2 * index + 1) && checkIfMinHeap(arr, 2 * index + 2);
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2, 10, 4, 5, 3, 15};
        boolean result = CheckIfArrayMinHeap.checkIfMinHeap(arr, 0);
        System.out.println(result);
    }
}
