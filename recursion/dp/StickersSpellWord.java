package recursion.dp;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author andy-liu
 * @date 2020/5/27 - 7:12 AM
 */
public class StickersSpellWord {

    public static void main(String[] args) {
    }

    public static int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        int[][] map = new int[n][26];
        HashMap<String, Integer> dp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c : str) {
                map[i][c - 'a']++;
            }
        }
        dp.put("", 0);
        return process(dp, map, target);
    }

    public static int process(HashMap<String, Integer> dp, int[][] map, String t) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        char[] target = t.toCharArray();
        int[] tmap = new int[26];
        for (char c : target) { //统计要处理的字符串中的字符个数
            tmap[c - 'a']++;
        }
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < map.length; i++) { //遍历map中的每一个字符串
            if (map[i][target[0] - 'a'] == 0) { // 如果i位置的字符串中不包含target的第一个字符
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) { //遍历要处理的字符串
                if (tmap[j] > 0) {   // j位置的字符串需要处理, 去map里找
                    for (int k = 0; k < Math.max(0, tmap[i] - map[i][j]); k++) {
                        //循环判断, 用掉map[i]这个字符串中 j位置的字符串, 还需要多少个这样的字符(tmap[i] - map[i][j])
                        sb.append((char) ('a' + j));  //把还需要的字符加入到sb中
                    }
                }
            }
            String s = sb.toString(); // 循环结束s中剩下的字符就是继续还要处理的字符
            int tmp = process(dp, map, s);
            if (tmp != -1) {
                ans = Math.min(ans, 1 + tmp);
            }
        }
        dp.put(t, ans == Integer.MAX_VALUE ? -1 : ans); //如果结果有效, 就加入缓存
        return dp.get(t);
    }
}
