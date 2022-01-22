package org.msharma.algorithm.trees;

import java.util.ArrayList;
import java.util.LinkedList;

public class BSTSequences {

    public static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
        if (first.size() == 0 || second.size() == 0) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }
        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);
        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headSecond);
    }

    public static ArrayList<LinkedList<Integer>> allSequences(Node<Integer> node) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();

        if (node == null) {
            result.add(new LinkedList<Integer>());
            return result;
        }

        LinkedList<Integer> prefix = new LinkedList<Integer>();
        prefix.add(node.getData());
        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.getLeft());
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.getRight());
        for (LinkedList<Integer> left : leftSeq) {
            for (LinkedList<Integer> right : rightSeq) {
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(20, bst.getRoot());
        bst.insert(10, bst.getRoot());
        bst.insert(25, bst.getRoot());
        bst.insert(5, bst.getRoot());
        bst.insert(15, bst.getRoot());

        System.out.println(BSTSequences.allSequences(bst.getRoot()));
    }
}
