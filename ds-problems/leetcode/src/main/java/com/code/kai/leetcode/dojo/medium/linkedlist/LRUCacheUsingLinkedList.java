package com.code.kai.leetcode.dojo.medium.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
//Basic idea: when doing get check if node exists for the key in map if yes then remove it first and insert it again for the key and removed node value why? so it becomes recently used.
// When doing put check if key contains simply remove the node by getting with the key or else check if overflow then remove the tail then insert again into the head, so it becomes recently used.
// Initial Head and tail can be dummy nodes so can we avoid more if conditions. Make head.next = tail and tail.prev = head initially
// So inserting a next node is all about head.next = node and node.prev = head then intial headNext.prev = node and node.next = headNext Similarly remove we need to re-establish the link
// node.prev.next = node.next and node.next.prev = node.prev
public class LRUCacheUsingLinkedList {

    private Map<Integer, Node> storage;
    private int capacity;
    Node head = new Node(0, 0), tail = new Node(0, 0);

    public LRUCacheUsingLinkedList(int capacity) {
        head.next = tail;
        tail.prev = head;
        storage = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (storage.containsKey(key)) {
            Node node = storage.get(key);
            remove(key);
            insert(key, node.val);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (storage.containsKey(key)) {
            remove(key);
        } else if (storage.size() + 1 > capacity) {
            remove(tail.prev.key);
        }
        insert(key, value);
    }

    private void insert(int key, int value) {
        Node node = new Node(key, value);
        storage.put(key, node);
        Node headNext = head.next;
        head.next = node;
        node.prev = head;
        headNext.prev = node;
        node.next = headNext;
    }

    private void remove(int key) {
        Node node = storage.remove(key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    class Node {
        Node next, prev;
        int val;
        int key;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
