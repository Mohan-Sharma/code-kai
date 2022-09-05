package com.code.kai.leetcode.dojo.medium.linkedlist;

import java.util.LinkedHashMap;

/**
 * @author Mohan Sharma
 */
// General problem in LRU is searching of the element if it is already present takes O(n)(solve using hashing) and re-ordering
// i.e. moving the found element to the front takes O(n)(solve using doubly linked-list)
// the problem is simple we don't need to implement doubly linked list, since doubly linked list maintains insertion order
// entrySet.iterator.remove() will always remove from the head. and put inserts at the end
public class LRUCacheUsingLinkedHashMap {

    private int size;
    private LinkedHashMap<Integer, Integer> storage;

    public LRUCacheUsingLinkedHashMap(int capacity) {
        storage = new LinkedHashMap<>();
        size = capacity;
    }

    public int get(int key) {
        if (storage.containsKey(key)) {
            int value = storage.remove(key);
            storage.put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (storage.containsKey(key)) {
            storage.remove(key);
        } else if (storage.size() + 1 > size) {
            // illegal state exception why? since we haven't called next() on your Iterator,
            // so it's not referring to the first item yet. You can't remove the item that isn't specified yet
            //storage.entrySet().iterator().remove();
            storage.remove(storage.keySet().iterator().next());
        }
        storage.put(key, value);
    }
}
