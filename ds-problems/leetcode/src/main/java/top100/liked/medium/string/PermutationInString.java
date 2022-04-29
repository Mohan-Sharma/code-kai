package top100.liked.medium.string;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author Mohan Sharma
 */
public class PermutationInString {
    //O(26n), n = s2.length()
    public boolean checkInclusions(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] chars = new int[26];
        s1.chars().forEach(ch -> chars[ch - 'a']++);

        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            chars[ch - 'a']--;
            if (i >= s1.length()) {
                chars[s2.charAt(i - s1.length()) - 'a']++;
            }
            if (allZeros(chars))
                return true;
        }
        return false;
    }

    //O(n), n = s2.length()
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        Map<Character, Integer> dp = new HashMap<>();
        for (char ch : s1.toCharArray()) {
            dp.compute(ch, (k, v) -> v == null ? 1 : v+1);
        }

        int count = 0;
        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            if (dp.containsKey(ch)) {
                dp.put(ch, dp.get(ch) - 1);
                if (dp.get(ch) == 0)
                    count++;

            }
            if (i >= s1.length()) {
                char sch = s2.charAt(i - s1.length());
                if (dp.containsKey(sch)) {
                    if (dp.get(sch) == 0)
                        count--;
                    dp.put(sch, dp.get(sch) + 1);
                }
            }
            if (count == dp.size())
                return true;
        }
        return false;
    }
    private boolean allZeros(int[] chars) {
        return IntStream.of(chars).allMatch(i -> i == 0);
    }

    public static void main(String[] args) {
        System.out.println(new PermutationInString().checkInclusion("ab", "eidbaooo"));
    }
}
