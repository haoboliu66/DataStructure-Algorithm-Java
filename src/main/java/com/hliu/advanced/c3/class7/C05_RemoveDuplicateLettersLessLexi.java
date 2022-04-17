package src.main.java.advanced.c3.class7;

import java.util.Stack;

public class C05_RemoveDuplicateLettersLessLexi {

    // 贪心 + 递归
    // https://leetcode.com/problems/remove-duplicate-letters/


    public static void main(String[] args) {
        String s = "cbbbcaa";
        String res = removeDuplicateLetters1(s);
        System.out.println(res);

    }

    public static String removeDuplicateLetters(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        int[] map = new int[128];
        for (int i = 0; i < str.length(); i++) {
            map[str.charAt(i)]++;
        }
        int minACSIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            minACSIndex = str.charAt(i) < str.charAt(minACSIndex) ? i : minACSIndex;
            if (--map[str.charAt(i)] == 0) {
                break;
            }
        }
        String rightPart = str.substring(minACSIndex + 1).replaceAll(String.valueOf(str.charAt(minACSIndex)), "");

        return str.charAt(minACSIndex) + removeDuplicateLetters(rightPart);
    }


    public static String removeDuplicateLetters1(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        int[] map = new int[128];
        for (int i = 0; i < str.length(); i++) {
            map[str.charAt(i)]++;
        }
        return helper(str, map);
    }

    // 在循环中使用stringBuilder删除字符串时, 要注意下标的突变, 会导致结果不正确
    public static String helper(String s, int[] map) {
        int minACSIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            minACSIndex = s.charAt(i) < s.charAt(minACSIndex) ? i : minACSIndex;
            if (--map[s.charAt(i)] == 0) {
                break;
            }
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = minACSIndex + 1; i < sb.length(); ) {
            if (sb.charAt(i) == s.charAt(minACSIndex)) {
                map[s.charAt(minACSIndex)]--;
                sb.deleteCharAt(i);
            } else {
                i++;
            }
        }
        String remainingPart = String.valueOf(sb).substring(minACSIndex + 1);
        return s.charAt(minACSIndex) + removeDuplicateLetters(remainingPart);
    }

    // with stack
    public String removeDuplicateLetter2(String s) {
        Stack<Character> stack = new Stack<>();
        int[] count = new int[128];
        char[] arr = s.toCharArray();
        for (char c : arr) {
            count[c]++;
        }
        boolean[] visited = new boolean[128];
        for (char c : arr) {
            count[c]--;
            if (visited[c]) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek()] > 0) {
                visited[stack.pop()] = false;
            }
            stack.push(c);
            visited[c] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }


}
