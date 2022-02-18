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

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //creating dummy node so dont need to check if head of result is null first time
        ListNode result = new ListNode(0);
        //current will always point to tail for next insert whereas result will store the complete merged list
        ListNode current = result;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        current.next = list1 == null ? list2 : list1;
        //ignore the first 0 node
        return result.next;
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
        mergeTwoLists(l1.head, l2.head);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
