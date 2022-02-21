package com.code.kai.leetcode.curated75.hard.heap;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author Mohan Sharma
 */
public class MergeKSortedLists {

    public static ListNode mergeKListsPriorityQueue(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            while (node != null) {
                pq.add(node);
                node = node.next;
                new ArrayList<>(pq);
            }
        }
        ListNode head = pq.poll();
        ListNode cur = head;
        while (!pq.isEmpty() && cur != null) {
            ListNode node = pq.poll();
            cur.next = node;
            cur = node;
        }
        if (cur != null)
            cur.next = null;
        return head;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-2,-1,-1,-1};
        ListNode root = new ListNode(-2);
        int index = 1;
        ListNode cur = root;
        while (index < arr.length) {
            ListNode next = new ListNode(arr[index++]);
            cur.next = next;
            cur = next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
