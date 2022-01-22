package org.msharma.algorithm.trees;

import java.util.Map;
import java.util.Objects;

/**
 * @author Mohan Sharma
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for(Character c :  word.toCharArray()) {
            Map<Character, TrieNode> children = current.getChildren();
            TrieNode newNode = new TrieNode();
            if(!children.containsKey(c)) {
                children.put(c, newNode);
            }
            current = newNode;
        }
        current.setEndOfWord(true);
    }

    public boolean find(String word) {
        TrieNode current = root;
        for(Character c : word.toCharArray()) {
            TrieNode node = current.getChildren().get(c);
            if(Objects.isNull(node))
                return false;
            current = node;
        }
        return current.isEndOfWord();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("MAN");
        System.out.println(trie.find("MAN"));
    }
}

