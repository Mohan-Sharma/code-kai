package com.code.kai.leetcode.curated75.medium.trees;

/**
 * @author Mohan Sharma
 */
public class AddAndSearchWord extends Trie{

    @Override
    public boolean search(String word) {
        return dfsSearch(word, 0, root);
    }

    private boolean dfsSearch(String word, int index, TrieNode root) {
        TrieNode curr = root;
        for (int i = index; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch ==  '.') {
                for (TrieNode n : curr.dict.values()) {
                    if (dfsSearch(word, i + 1, n))
                        return true;
                }
                return false;
            }
            else {
                if (!curr.dict.containsKey(ch)) {
                    return false;
                }
                curr = curr.dict.get(ch);
            }
        }
        return curr.endOfWord;
    }
}
