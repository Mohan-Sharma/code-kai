package com.code.kai.leetcode.curated75.medium.linkedlist;

import com.code.kai.leetcode.curated75.easy.linkedlist.ListNode;

/**
 * @author Mohan Sharma
 */
public class RemoveNthNodeFromEnd {

    private int counter;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        return removeRecursively(head, n);
        //return removeTwoPointerIteratively(head, n);
    }

    private static ListNode removeTwoPointerIteratively(ListNode head, int n) {
        ListNode firstWalker = head, secondWalker = head;
        //first walker moves to the (size-n)th node
        while(n-- > 0)
            firstWalker = firstWalker.next;
        // if first walker already reached end means let's say size was 4 and
        // first walker reached null at the end when n becomes 0
        // so the n should be 4 meaning remove the first node from the head or 4th node from tail
        // so simply return head.next;
        if (firstWalker == null)
            return head.next;
        // this extra step ensures that the gap between first and second increased by 1
        // so in a way if we want to remove the 3rd node from end, second runner will be
        // position at 2nd node when first reaches end.
        firstWalker = firstWalker.next;
        while(firstWalker != null) {
            firstWalker = firstWalker.next;
            secondWalker = secondWalker.next;
        }
        secondWalker.next = secondWalker.next.next;
        return head;
    }

    private ListNode removeRecursively(ListNode cur, int n) {
        if (cur == null) //reached end
            return null;
        ListNode result = removeRecursively(cur.next, n);
        counter++;
        if (n == counter) {
            cur = result; //skip current
        } else
            cur.next = result; //consider current
        return cur;
    }

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        //ListNode two = new ListNode(2);
        //ListNode three = new ListNode(3);
        //ListNode four = new ListNode(4);
        //ListNode five = new ListNode(5);
        //one.next = two;
        //two.next = three;
        //three.next = four;
        //four.next = five;
        ListNode result = new RemoveNthNodeFromEnd().removeNthFromEnd(one, 1);
        System.out.println(result);
    }
}
