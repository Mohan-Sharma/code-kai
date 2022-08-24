package top100.liked.medium.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class CopyListRandomPointer {

    // Copying list can be done by iterating over the list but when we iterate over the list the cur node should be next of the prev node ideally
    // which can be handled using a dummy node meaning if we have a dummy node with 0 value while iterating over the list, we set assume the
    // dummy was the prev node so dummy.next = new Node(cur.val) but while iterating some of the random nodes might not yet be created since
    // the iteration did not complete yet and random can point to any nodes before or after the cur node. Hence while we are iterating the
    // cur node let's store the new nodes in a hash table. Now again we can iterate over the list get the random nodes from the hash and set them
    public Node copyRandomList(Node head) {
        Node dummy = new Node(0);
        Node result = dummy;
        Node current = head;
        Map<Node, Node> hash = new HashMap<>();
        while(current != null) {
            Node node = new Node(current.val);
            hash.put(current, node);
            result.next = node;
            current = current.next;
            result = result.next;
        }
        result = dummy.next;
        current = head;
        while (current != null) {
            Node random = current.random;
            if (random != null) {
                result.random = hash.get(random);
            } else
                result.random = null;
            current = current.next;
            result = result.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        one.random = null;
        two.random = one;
        three.random = five;
        four.random = two;
        five.random = three;
        Node result = new CopyListRandomPointer().copyRandomList(one);
        while (result != null) {
            System.out.println(result.val);
            System.out.println(result.random != null ? result.random.val : -1);
            System.out.println();
            result = result.next;
        }
    }
}
