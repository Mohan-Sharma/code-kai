package org.msharma.algorithm.priority_queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Mohan Sharma
 */
public class FirstKNonRepeatingCharacters {

    class Entry {
        int index;
        int count;
        Entry(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }

    public List<Character> firstKNonRepeatingCharacters(final String word, int k) {
        Map<Character, Entry> storage = new HashMap<>();
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(storage.containsKey(c)) {
                Entry entry = storage.get(c);
                entry.count += 1;
                storage.put(c, entry);
            } else {
                storage.put(c, new Entry(i, 1));
            }
        }

        PriorityQueue<Entry> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.index));
        for(Map.Entry<Character, Entry> entry : storage.entrySet()) {
            if(entry.getValue().count == 1)
                pq.add(entry.getValue());
        }
        List<Character> result = new ArrayList<>();
        while(--k >= 0 && !pq.isEmpty()) {
            result.add(word.charAt(pq.poll().index));
        }
        return result;
    }

    public static void main(String[] args) {
        FirstKNonRepeatingCharacters obj = new FirstKNonRepeatingCharacters();
        List<Character> result = obj.firstKNonRepeatingCharacters("ABCDAGHCHFAC", 3);
        System.out.println(result);
    }
}
