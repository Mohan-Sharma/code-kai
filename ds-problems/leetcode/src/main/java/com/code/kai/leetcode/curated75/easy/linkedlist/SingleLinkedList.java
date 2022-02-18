package com.code.kai.leetcode.curated75.easy.linkedlist;

/**
 * @author Mohan Sharma
 */
public class SingleLinkedList {
    private ListNode head;
    private int size;

    public void insertNode(int data) {
        ListNode singlyNode = new ListNode(data, null);
        if (head == null) {
            head = singlyNode;
        } else {
            ListNode current = head;
            ListNode tail = null;
            while (current != null) {
                tail = current;
                current = current.next;
            }
            tail.next = singlyNode;
        }
        size++;
    }

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

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
