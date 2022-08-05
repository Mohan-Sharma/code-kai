package top100.liked.medium.string;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author Mohan Sharma
 */
public class PermutationInString {

    // very similar to Minimum Window Substring only difference is while checking
    // the counter we check if the length of window is valid to check the counter
    // meaning if s length is 2 then we check if end >= 2 then only check if counter==0
    public boolean checkInclusionUsingTemplate(String s, String t) {
        if (s.length() > t.length())
            return false;
        if (s.equals(t))
            return true;
        // the use of counter variable is to track the condition e.g. if s=ab, initially the counter
        // will be initialized with counter=2, since s.length equals 2, now if we find a character in t
        // which matches the character in s we decrement counter. Finally when counter becomes 0 and
        // substring in t's length is equals s we return true
        int end = 0, counter = s.length();
        int[] dp = new int[26];
        // populate the array with count e.g. if s=ab then at 0th index we will have count as 1 and
        // at 1th index we will have count as 1. Remaining index will be count 0
        s.chars().forEach(c -> dp[c - 'a']++);
        while (end < t.length()) {
            int ch = t.charAt(end++) - 'a';
            // dp[ch] > 0 means a character in t matches the character in s why? b/c we populated
            // the dp array with s' characters so the character count of s in dp will always be > 0
            if (dp[ch] > 0)
                counter--;
            dp[ch]--;
            // end >= s.length means we are eligible to take the substring of t
            if (end >= s.length()) {
                if (counter == 0)
                    return true;
                // if substring is not found in t populate the character of t from the front since
                // we decremented using dp[ch]-- so reverting back
                int sch = t.charAt(end - s.length()) - 'a';
                dp[sch]++;
                if (dp[sch] > 0)
                    counter++;
            }
        }
        return false;
    }

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
                if (allZeros(chars))
                    return true;
            }
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
        System.out.println(new PermutationInString().checkInclusion("a", "ab"));
    }
}
