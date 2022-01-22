package org.msharma.algorithm.priority_queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author Mohan Sharma
 */
public class MinimumRangeKSortedArrays {
    private class Node {
        private int arrNumber;
        private int elementIndex;
        private int value;

        Node(int arrNumber, int elementIndex, int value) {
            this.arrNumber = arrNumber;
            this.elementIndex = elementIndex;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "arrNumber=" + arrNumber +
                    ", elementIndex=" + elementIndex +
                    ", value=" + value +
                    '}';
        }
    }

    public int[] minimumRangeOfKSortedArrays(int[][] arrays) {
        PriorityQueue<Node> queue = new PriorityQueue<>(arrays.length, Comparator.comparingInt(a -> a.value));
        int totalLength = 0;
        int arrayNumber = 0;
        int minRangelow = Integer.MAX_VALUE;
        int minRangeHigh = Integer.MIN_VALUE;
        int high = Integer.MIN_VALUE;
        int minRange = Integer.MAX_VALUE;
        for(int[] array : arrays) {
            totalLength += array.length;
            high = Math.max(high, array[0]);
            queue.add(new Node(arrayNumber++, 0, array[0]));
        }

        for(int i=0; i<totalLength; i++) {
            Node node = queue.poll();
            if(Objects.nonNull(node)) {
                int range = high - node.value;
                if(range < minRange) {
                    minRange = range;
                    minRangelow = node.value;
                    minRangeHigh = high;
                }
                int elementIndex = node.elementIndex;
                int arrNumber = node.arrNumber;
                if(elementIndex + 1 >= arrays[arrNumber].length) {
                    break;
                } else {
                    int value = arrays[arrNumber][elementIndex + 1];
                    high = Math.max(high, value);
                    queue.add(new Node(arrNumber, elementIndex + 1, value));
                }
            }
        }
        return new int[] {minRangelow, minRangeHigh};
    }

    public static void main(String[] args) {
        int[][] input  = {{ 3, 6, 8, 10, 15 }, { 1, 5, 12 }, { 4, 8, 15, 16 }, { 2, 6 }};
        MinimumRangeKSortedArrays obj = new MinimumRangeKSortedArrays();
        int[] minRange = obj.minimumRangeOfKSortedArrays(input);
        System.out.println(Arrays.toString(minRange));
    }
}
