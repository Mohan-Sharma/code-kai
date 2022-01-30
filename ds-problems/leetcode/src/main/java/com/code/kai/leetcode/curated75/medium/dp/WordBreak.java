package com.code.kai.leetcode.curated75.medium.dp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class WordBreak {

    public static boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakBottomUp(s, wordDict);
    }

    private static boolean wordBreakBottomUp(String str, List<String> wordDict) {
        //leetcode
        boolean[] dp = new boolean[str.length() + 1];
        dp[str.length()] = true;
        for (int i = str.length() - 1; i >= 0 ; i--) {
            for (String word : wordDict) {
                if (i + word.length() <= str.length() && str.substring(i, i + word.length()).equalsIgnoreCase(word)) {
                    dp[i] = dp[i + word.length()];
                }
                if (dp[i])
                    break;
            }
        }
        return dp[0];
    }

    private static boolean wordBreakTopDown(String str, List<String> wordDict, Map<String, Boolean> dp) {
        if (wordDict.contains(str))
            return true;
        if (dp.containsKey(str))
            return dp.get(str);
        for (int i = 1; i <= str.length(); i++) {
            String left = str.substring(0, i);
            if (wordDict.contains(left) && wordBreakTopDown(str.substring(i), wordDict, dp)) {
                dp.put(str, true);
                return true;
            }
        }
        dp.put(str, false);
        return false;
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("catsanddog", Arrays.asList("cats","dog","sand","and","cat")));
    }
}
