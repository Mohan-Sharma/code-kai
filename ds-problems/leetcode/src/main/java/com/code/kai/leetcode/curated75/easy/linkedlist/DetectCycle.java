package com.code.kai.leetcode.curated75.easy.linkedlist;

/**
 * @author Mohan Sharma
 */
public class DetectCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }
}
