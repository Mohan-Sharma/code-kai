package com.code.kai.leetcode.curated75.easy.linkedlist;

/**
 * @author Mohan Sharma
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        ListNode current = head, previous = null, next = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    public ListNode reverseListRec(ListNode head) {
        return helper(head, null);
    }

    public ListNode helper(ListNode current, ListNode prev) {
        if (current == null)
            return prev;
        ListNode next = current.next;
        current.next = prev;
        return helper(next, current);
    }
}
