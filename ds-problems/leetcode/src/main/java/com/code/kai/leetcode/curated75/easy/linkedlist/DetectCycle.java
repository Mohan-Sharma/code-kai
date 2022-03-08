package com.code.kai.leetcode.curated75.easy.linkedlist;

/**
 * @author Mohan Sharma
 */
public class DetectCycle {
    // explanation of why moving the slow to head works to find the start of the loop
    //https://stackoverflow.com/questions/1536944/detecting-the-start-of-a-loop-in-a-singly-linked-link-list
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
