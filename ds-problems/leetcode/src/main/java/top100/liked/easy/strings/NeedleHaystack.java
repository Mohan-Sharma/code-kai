package top100.liked.easy.strings;

/**
 * @author Mohan Sharma
 */
public class NeedleHaystack {

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        if (haystack.length() == 0)
            return -1;
        for (int i = 0; i < haystack.length() + 1 - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle))
                return i;
            /*for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j))
                    break;
                else if (j == needle.length() - 1)
                    return i;
            }*/
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new NeedleHaystack().strStr("a","a"));
    }
}
