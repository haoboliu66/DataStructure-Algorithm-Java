package src.main.java.sys.c12;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C01_PermutationInString {

    /*
    similar 438. Find All Anagrams in a String  https://leetcode.com/problems/find-all-anagrams-in-a-string/
    Minimum window substring
    Longest Substring without Repeating Characters
    Longest Substring with at most 2 Distinct Characters
    Longest Substring with at most k Distinct Characters
     */

    // 567. Permutation in String  https://leetcode.com/problems/permutation-in-string/

    public boolean checkInclusion(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str1) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> origin = new HashMap<>(map);
        int all = str1.length, window = str1.length;
        int L = 0, R = 0;
        for (; L < str2.length; ) {
            while (R < str2.length && R - L + 1 <= window) {
                if (!map.containsKey(str2[R])) {
                    R++;
                    L = R;
                    all = str1.length;
                    map = new HashMap<>(origin);
                    continue;
                }
                if (map.get(str2[R]) > 0) {
                    all--;
                }
                map.put(str2[R], map.get(str2[R]) - 1);
                R++;
            }
            if (all == 0) {
                return true;
            }
            if (L >= str2.length || R - L + 1 < window) {
                return false;
            }
            if (map.get(str2[L]) >= 0) {
                all++;
            }
            map.put(str2[L], map.get(str2[L]) + 1);
            L++;
        }
        return false;
    }

    // with int[] map
    public boolean checkInclusion2(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] map = new int[256];
        for (char c : str1) {
            map[c]++;
        }
        int all = str1.length, window = str1.length;
        int L = 0, R = 0;
        for (; R < str2.length; ) {
            if (map[str2[R]]-- > 0) {
                all--;
            }
            if (R - L + 1 == window) {
                break;
            }
            R++;
        }
        // [L...R]
        //  ei dbaoooo
        for (; R < str2.length; ) {
            if (all == 0) {
                return true;
            }
            R++;
            if (R < str2.length && map[str2[R]]-- > 0) {
                all--;
            }
            if (map[str2[L++]]++ >= 0) {
                all++;
            }
        }
        return false;
    }

    // return index of the substring
    public int checkInclusionIndex(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] map = new int[256];
        for (char c : str1) {
            map[c]++;
        }
        int all = str1.length, window = str1.length;
        int L = 0, R = 0;
        for (; R < str2.length; ) {
            if (map[str2[R]]-- > 0) {
                all--;
            }
            if (R - L + 1 == window) {
                break;
            }
            R++;
        }
        // [L...R]
        //  ei dbaoooo
        for (; R < str2.length; ) {
            if (all == 0) {
                return R - window + 1;
            }
            R++;
            if (R < str2.length && map[str2[R]]-- > 0) {
                all--;
            }
            if (map[str2[L++]]++ >= 0) {
                all++;
            }
        }
        // window < s1.length()
        return -1;
    }

    @Test
    public void test1() {
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(checkInclusionIndex(s1, s2));
    }


    // https://leetcode.com/problems/find-all-anagrams-in-a-string/
    // find all p in s
    public List<Integer> findAnagrams(String s, String p) {
        char[] str1 = p.toCharArray();
        char[] str2 = s.toCharArray();
        int[] map = new int[256];
        for (char c : str1) {
            map[c]++;
        }
        List<Integer> ans = new ArrayList<>();
        int L = 0, R = 0, all = str1.length, window = str1.length;
        for (; R < str2.length; ) {
            if (map[str2[R]]-- > 0) {
                all--;
            }
            if (R - L + 1 == window) {
                break;
            }
            R++;
        }

        for (; R < str2.length; ) {
            if (all == 0) {
                ans.add(R - window + 1);
            }
            R++;
            if (R < str2.length && map[str2[R]]-- > 0) {
                all--;
            }
            if (map[str2[L++]]++ >= 0) {
                all++;
            }
        }

        return ans;
    }


    @Test
    public void test2() {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams(s, p));
    }

}
