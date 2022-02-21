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

    public static void main(String[] args) {
        SingleLinkedList l1 = new SingleLinkedList();
        l1.insertNode(1);
        l1.insertNode(4);
        l1.insertNode(5);
        SingleLinkedList l2 = new SingleLinkedList();
        l2.insertNode(1);
        l2.insertNode(3);
        l2.insertNode(4);
    }
}

