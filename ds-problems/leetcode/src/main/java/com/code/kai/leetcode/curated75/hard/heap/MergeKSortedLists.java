package com.code.kai.leetcode.curated75.hard.heap;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author Mohan Sharma
 */
public class MergeKSortedLists {

    public static ListNode mergeKLists(ListNode[] lists) {
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

    public static ListNode mergeKListsEfficient(ListNode[] lists) {
        return divideAndMerge(lists, 0, lists.length - 1);
    }

    private static ListNode divideAndMerge(ListNode[] lists, int start, int end) {
        if (start ==  end) {
            return lists[start];
        }
        int mid = start + (end - start)/2;
        ListNode l1 = divideAndMerge(lists, start, mid);
        ListNode l2 = divideAndMerge(lists, mid + 1, end);
        return merge2SortedList(l1, l2);
    }

    private static ListNode merge2SortedList(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode current = result;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        current.next = l1 == null ? l2 : l1;
        return result.next;
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

        System.out.println(mergeKListsEfficient(new ListNode[] {root}));
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
