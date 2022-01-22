package org.msharma.algorithm.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class TrieNode {
    private final Map<Character, TrieNode> children = new HashMap<>();
    private boolean endOfWord;

    Map<Character, TrieNode> getChildren() {
        return children;
    }

    boolean isEndOfWord() {
        return endOfWord;
    }

    void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }
}
