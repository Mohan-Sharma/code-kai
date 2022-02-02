package com.code.kai.leetcode.curated75.medium.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohan Sharma
 */
public class DecodeWays {

    public static int numDecodings(String s) {
        return numDecodingsTopDown(s, 0,new HashMap<>());
    }

    //11106
    private static int numDecodingsTopDown(String str, int index, Map<String, Integer> dp) {
        int count = 0;
        if (index >= str.length()) {
            return 1;
        }
        int oneDigit = str.charAt(index) - '0';
        if (oneDigit < 1 || oneDigit > 26) {
            return 0;
        }
        if (index == str.length() - 1) {
            return 1;
        }
        if (dp.containsKey(str.substring(index))) {
            return dp.get(str.substring(index));
        }
        count += numDecodingsTopDown(str, index + 1, dp);
        dp.put(str.substring(index), count);
        int twoDigit = Integer.parseInt(str.substring(index, index + 2));
        if (twoDigit >= 1 && twoDigit <= 26) {
            count += numDecodingsTopDown(str, index + 2, dp);
            dp.put(str.substring(index), count);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("11106"));
    }
}
