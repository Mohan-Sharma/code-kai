package com.code.kai.leetcode.curated75.medium.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;

        for (Character c : word.toCharArray()) {
            TrieNode newNode = new TrieNode();
            if (current.dict.containsKey(c)) {
                current = current.dict.get(c);
            } else {
                current.dict.put(c, newNode);
                current = newNode;
            }
        }
        current.endOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (Character c : word.toCharArray()) {
            if (!current.dict.containsKey(c))
                return false;
            current = current.dict.get(c);
        }
        return current.endOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (Character c : prefix.toCharArray()) {
            if (!current.dict.containsKey(c))
                return false;
            current = current.dict.get(c);
        }
        return true;
    }
}

class TrieNode {
    Map<Character, TrieNode> dict = new HashMap<>();
    boolean endOfWord;
}
