package com.code.kai.leetcode.curated75.hard.linkedlist;

import com.code.kai.leetcode.curated75.easy.linkedlist.ListNode;

/**
 * @author Mohan Sharma
 */
public class MergeKSortedList {

    public ListNode mergeKLists(ListNode[] lists) {
        return divideAndMerge(lists, 0, lists.length - 1);
    }

    private static ListNode divideAndMerge(ListNode[] lists, int start, int end) {
        if (start > end)
            return null;
        if (start ==  end) {
            return lists[start];
        }
        int mid = start + (end - start)/2;
        ListNode l1 = divideAndMerge(lists, start, mid);
        ListNode l2 = divideAndMerge(lists, mid + 1, end);
        return merge2SortedList(l1, l2);
    }

    private static ListNode merge2SortedList(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode current = result;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        current.next = l1 == null ? l2 : l1;
        return result.next;
    }
}
