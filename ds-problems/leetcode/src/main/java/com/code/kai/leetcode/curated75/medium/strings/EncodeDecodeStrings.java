package com.code.kai.leetcode.curated75.medium.strings;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class EncodeDecodeStrings {

    public String encode(List<String> strs) {
        return String.join("#", strs);
    }

    public List<String> decode(String str) {
        String[] words = str.split("#");
        return Arrays.asList(words);
    }

    public static void main(String[] args) {
        EncodeDecodeStrings instance = new EncodeDecodeStrings();
        String encodedString = instance.encode(Arrays.asList("lint","code","love","you"));
        System.out.println("Encoded String: " + encodedString);
        System.out.println(instance.decode(encodedString));
    }
}
