package com.code.kai.leetcode.curated75.medium.linkedlist;

import com.code.kai.leetcode.curated75.easy.linkedlist.ListNode;

/**
 * @author Mohan Sharma
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        ListNode walker = head, runner = head, prev = null;
        while (runner != null && runner.next != null) {
            prev = walker;
            walker = walker.next;
            runner = runner.next.next;
        }

        prev.next = null; // cut off the list into 2 halves
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

    public void reorderListAnotherWay(ListNode head) {
        ListNode walker = head, runner = head;
        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
        }
        ListNode second = walker.next;
        walker.next = null;
        second = reverseList(second);

        while (head != null && second != null) {
            ListNode fn = head.next;
            ListNode sn = second.next;
            head.next = second;
            head.next.next = fn;
            head = fn;
            second = sn;
        }
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
        four.next = five;
        new ReorderList().reorderListAnotherWay(one);
        one.print();
    }
}
