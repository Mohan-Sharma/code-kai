package com.code.kai.leetcode.curated75.hard.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class WordSearchII {

    private TrieNode root;

    private List<String> result;

    public List<String> findWords(char[][] board, String[] words) {
        result = new ArrayList<>();
        storeWordsInTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findWordsInTrie(board, i, j, root, "");
            }
        }
        return result;
    }

    private void findWordsInTrie(char[][] board, int r, int c, TrieNode root, String word) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || !root.dict.containsKey(board[r][c]))
            return;
        char alphabet = board[r][c];
        word += alphabet;
        root= root.dict.get(alphabet);
        board[r][c] = '*';
        if (root.endOfWord) {
            result.add(word);
            root.endOfWord = false;
        }
        findWordsInTrie(board, r+1, c, root, word);
        findWordsInTrie(board, r-1, c, root, word);
        findWordsInTrie(board, r, c+1, root, word);
        findWordsInTrie(board, r, c-1, root, word);
        board[r][c] = alphabet;
    }

    private void storeWordsInTrie(String[] board) {
        root = new TrieNode();
        for (String word : board) {
            TrieNode cur = root;
            for (char ch : word.toCharArray()) {
                TrieNode newNode = new TrieNode();
                if (cur.dict.containsKey(ch)) {
                    cur = cur.dict.get(ch);
                } else {
                    cur.dict.put(ch, newNode);
                    cur = newNode;
                }
            }
            cur.endOfWord = true;
        }
    }

    public static void main(String[] args) {
        System.out.println(new WordSearchII().findWords(new char[][] {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}}, new String[] {"oath","pea","eat","rain"}));
    }
}

class TrieNode {
    Map<Character, TrieNode> dict = new HashMap<>();
    boolean endOfWord;
}


