package org.msharma.algorithm.priority_queue;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Mohan Sharma
 */
public class FirstKMaximumOccurringWords {

    private TrieNode root;

    FirstKMaximumOccurringWords() {
        this.root = new TrieNode();
    }

    class TrieNode {
        int count;
        String word;
        Map<Character, TrieNode> children = new HashMap<>();

        TrieNode() {}

        TrieNode(int count, String word) {
            this.count = count;
            this.word = word;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }

        public void setChildren(Map<Character, TrieNode> children) {
            this.children = children;
        }
    }

    private void insert(final String word) {
        TrieNode current = root;
        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            current = current.getChildren().computeIfAbsent(ch, c -> new TrieNode());
        }
        current.setCount(current.getCount() + 1);
        current.setWord(word);
    }

    private void preOrderTraversal(TrieNode node, PriorityQueue<TrieNode> pq) {
        if(node == null)
            return;
        for(Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
            TrieNode current = entry.getValue();
            if(current.getCount() != 0) {
                pq.add(new TrieNode(current.getCount(), current.getWord()));
            }
            preOrderTraversal(current, pq);
        }
    }

    public void getFirstKMaximumOccurringWords(String[] words, int k) {
        for(String word : words) {
            insert(word);
        }

        PriorityQueue<TrieNode> pq = new PriorityQueue<>((a, b) -> b.getCount() - a.getCount());
        preOrderTraversal(root, pq);

        while(--k >=  0 && !pq.isEmpty()) {
            TrieNode node = pq.poll();
            System.out.printf("%s occurring %d times\n", node.getWord(), node.getCount());
        }
    }

    public static void main(String[] args) {
        FirstKMaximumOccurringWords obj = new FirstKMaximumOccurringWords();
        String[] words = {
                "code", "coder", "coding", "codable", "codec", "codecs", "coded",
                "codeless", "codec", "codecs", "codependence", "codex", "codify",
                "codependents", "codes", "code", "coder", "codesign", "codec",
                "codeveloper", "codrive", "codec", "codecs", "codiscovered"
        };
        obj.getFirstKMaximumOccurringWords(words, 4);
    }
}
