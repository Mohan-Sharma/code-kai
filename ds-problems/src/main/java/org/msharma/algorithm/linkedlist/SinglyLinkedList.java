package org.msharma.algorithm.linkedlist;

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

    public void iterateList() {
        SinglyLinkedListNode s = head;
        int pos = 1;
        while (s != null) {
            System.out.println("Data at position - " + pos++ + " : " + s.getData());
            s = s.getNext();
        }

    }

    public void reverseTheList() {
        SinglyLinkedListNode current = head;
        SinglyLinkedListNode previous = null;
        SinglyLinkedListNode next = null;
        while (current != null && current.getNext() != null) {
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
    }
}