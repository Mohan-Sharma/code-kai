package org.msharma.algorithm.priority_queue;

import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author Mohan Sharma
 */
public class MergeKSortedArray {

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

    public int[] mergeKSortedArray(int[][] arrs) {
        int k = arrs.length;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(k, (o1, o2) -> o1.value - o2.value);
        int totalElements = 0;
        int count = 0;
        for(int[] arr : arrs) {
            totalElements += arr.length;
            priorityQueue.add(new Node(count++, 0, arr[0]));
        }
        int[] result = new int[totalElements];

        for(int i=0; i<totalElements; i++) {
            Node data = priorityQueue.poll();
            if(Objects.nonNull(data)) {
                result[i] = data.value;
                if(data.elementIndex +  1 < arrs[data.arrNumber].length) {
                    priorityQueue.add(new Node(data.arrNumber, data.elementIndex + 1, arrs[data.arrNumber][data.elementIndex + 1]));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] input  = {{ 1, 2, 3 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }};
        MergeKSortedArray obj = new MergeKSortedArray();
        int[] result = obj.mergeKSortedArray(input);
        System.out.println(Arrays.toString(result));
    }
}
