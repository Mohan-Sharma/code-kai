package com.code.kai.leetcode.curated75.medium.linkedlist;

import com.code.kai.leetcode.curated75.easy.linkedlist.ListNode;

/**
 * @author Mohan Sharma
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        ListNode walker = head, runner = head, firstHalf = null;
        while (runner != null && runner.next != null) {
            firstHalf = walker;
            walker = walker.next;
            runner = runner.next.next;
        }

        firstHalf.next = null; // cut off the list into 2 halves
        ListNode secondHalf = reverseList(walker);
        mergeLists(head, secondHalf);
    }

    private void mergeLists(ListNode firstHalf, ListNode secondHalf) {
        while (firstHalf != null) {
            ListNode firstNext = firstHalf.next;
            ListNode secondNext = secondHalf.next;
            firstHalf.next = secondHalf;
            if (firstNext == null)
                break;
            secondHalf.next = firstNext;
            firstHalf = firstNext;
            secondHalf = secondNext;

        }
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null, current = head, next = null;
        while (current != null) {
            next = current.next;
            current = new ListNode(current.val);
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        new ReorderList().reorderList(one);
        System.out.println(one);
    }
}
