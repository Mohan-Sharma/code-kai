package com.code.kai.leetcode.dojo.medium.linkedlist;

/**
 * @author Mohan Sharma
 * Leetcode-2
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0), current = result;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;
            if (sum > 9) {
                carry = sum / 10;
            } else {
                carry = 0;
            }
            current.next = new ListNode(sum % 10);
            current = current.next;
        }

        if (carry != 0) {
            current.next = new ListNode(carry);
        }
        return result.next;
    }

    public static void main(String[] args) {
        ListNode one = new ListNode(10);
        ListNode two = new ListNode(21);
        ListNode three = new ListNode(9);
        ListNode four = new ListNode(9);
        one.next = three;
        two.next = four;
        ListNode result = new AddTwoNumbers().addTwoNumbers(one, two);
        System.out.println(result);
    }
}
