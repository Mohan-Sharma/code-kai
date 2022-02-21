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
}
