package com.code.kai.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SinglyLinkedList {

    private SinglyLinkedListNode head;
    private int size;

    public SinglyLinkedList() {
    }

    public void insertNode(int data) {
        SinglyLinkedListNode singlyNode = new SinglyLinkedListNode(data, null);
        if (head == null) {
            head = singlyNode;
        } else {
            SinglyLinkedListNode s = head;
            SinglyLinkedListNode tail = null;
            while (s != null) {
                tail = s;
                s = s.getNext();
            }
            tail.setNext(singlyNode);
        }
        size++;
    }

    public void addFromHead(int data) {
        SinglyLinkedListNode singlyNode = new SinglyLinkedListNode(data, null);
        if (head == null) {
            head = singlyNode;
        } else {
            SinglyLinkedListNode s = head;
            singlyNode.setNext(s);
            head = singlyNode;
        }
        size++;
    }

    public void iterateList(SinglyLinkedListNode head) {
        SinglyLinkedListNode s = head;
        int pos = 0;
        while (s != null) {
            System.out.println("Data at position - " + pos++ + " : " + s.getData());
            s = s.getNext();
        }

    }

    public SinglyLinkedListNode reverseTheList() {
        SinglyLinkedListNode current = head;
        SinglyLinkedListNode previous = null;
        SinglyLinkedListNode next = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        return previous;
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        for (int i = 1; i <= 5; i++) {
            list.insertNode(i);
        }
        list.iterateList(list.reverseTheList());
    }
}