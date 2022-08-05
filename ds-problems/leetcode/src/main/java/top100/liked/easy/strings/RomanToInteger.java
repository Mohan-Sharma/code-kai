package top100.liked.easy.strings;

/**
 * @author Mohan Sharma
 */
public class RomanToInteger {

    public int romanToInt(String s) {
        if (s.length() == 0)
            return 0;
        int result = 0, start = 0, prev = getCorrespondingIntValue(s.charAt(start++));
        for (int i = start; i < s.length(); i++) {
            int current = getCorrespondingIntValue(s.charAt(i));
            if (current > prev)
                result -= prev;
            else
                result += prev;
            prev = current;
        }
        result += prev;
        return result;
    }

    private int getCorrespondingIntValue(char c){
        switch (c){
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X' :
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
        }
        throw new IllegalArgumentException("unsupported character");
    }

    public static void main(String[] args) {
        System.out.println(new RomanToInteger().romanToInt("MCMXCIV"));
    }
}
