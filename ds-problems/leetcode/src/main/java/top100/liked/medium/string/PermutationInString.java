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
    // First count characters of s1. Then Iterate over s2 to find the window. If 1st char is found for the window which matches s1 char
    // incr the count. Keep a track of the length of the window as it should be the size of s1 by doing (i >= s1.length). While keeping track
    // from the front if we encounter a char which contains in the map we should decrement the count and add the car to map why bcs the char became
    // invalid since window is invalid .e.g s1 = ac s2 = dabc first we have [(a:1, c:1)] as count then while iterating over s2, i=1, we find a
    // so count map becomes [(a:0, c:1)] and counter becomes 1 since we found one match but when i=2 now to find the valid window we need to revert
    // any changes we did to count map outside valid window. When i = 2, our window should be from index 1 to 2 and that we can find by checking
    // (i >= s1.length). In this case (2 >= 2) is valid cond so we check if any character previous encountered manipulated the counter or count map
    // e.g. when i = 3, (3 >= 2) and tracking char from outside window i = (3-2) so char at index  of s2 is 'a' which is outside valid window and
    // manipulated counters so we decrement counter and increment count map. Hence any time the window is valid and counter == count map size is our window
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
