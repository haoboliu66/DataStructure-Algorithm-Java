package top;

public class Problem_0003_LongestSubstringWithoutRepeatingCharacters {

    /*
    c3 - class7 - C06讲过
     */

    public int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }

        int pre = -1;
        int len = 0;
        for (int i = 0; i < str.length; i++) {
            pre = Math.max(pre, map[str[i]]);  //定位瓶颈
            len = Math.max(len, i - pre);
            map[str[i]] = i;
        }

        return len;
    }

}
