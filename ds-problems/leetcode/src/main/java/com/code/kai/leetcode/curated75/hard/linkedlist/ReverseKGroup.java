package com.code.kai.leetcode.curated75.hard.linkedlist;

/**
 * @author Mohan Sharma
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)
            return null;
        int size = getSize(head);
        if (k > size)
            return head;
        return reverseK(head, k, size);
    }

    private ListNode reverseK(ListNode head, int k, int size) {
        if (head == null)
            return null;
        if (k > size)
            return head;
        ListNode current = head, prev = null;
        int count = 0;

        while (current != null && count++ < k) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head.next = reverseK(current, k, size - k);
        return prev;
    }

    private int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    static public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
