package advanced.c4.class5;

import java.util.LinkedList;
import java.util.Stack;

public class C03_ExpressionCompute {

    /*
    227. Basic Calculator II - medium
    224. Basic Calculator - hard
     */

    public static int calculate(String s) {

        char[] str = shedSpace(s);
        return value(str, 0)[0];
    }

    public static char[] shedSpace(String s) {
        char[] s1 = s.toCharArray();
        int space = 0;
        for (int i = 0; i < s1.length; i++) {
            if (s1[i] == ' ') {
                space++;
            }
        }
        char[] str = new char[s1.length - space];
        int index = 0;
        for (int i = 0; i < s1.length; i++) {
            if (s1[i] != ' ') {
                str[index++] = s1[i];
            }
        }
        return str;
    }

    // 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
    // 返回两个值，长度为2的数组
    // 0) 负责的这一段的结果是多少
    // 1) 负责的这一段计算到了哪个位置
    public static int[] value(char[] str, int i) {
        LinkedList<String> queue = new LinkedList<>();
        int cur = 0;
        int[] bracket;
        while (i < str.length && str[i] != ')') {

            if (str[i] >= '0' && str[i] <= '9') { // number
                cur = cur * 10 + (str[i] - '0');
                i++;
            } else if (str[i] != '(') { //遇到了operator
                addNum(queue, cur);
                queue.addLast(String.valueOf(str[i]));
                i++;
                cur = 0;
            } else {  //遇到了左括号, 递归向后
                bracket = value(str, i + 1);
                cur = bracket[0];
                i = bracket[1] + 1;
            }
        }
        //最后一个数字没有后续的内容能把他加入队列
        addNum(queue, cur);

        return new int[]{getNum(queue), i};
    }

    //把一个数字num加入队列(根据栈顶内容情况看是否需要整合)
    private static void addNum(LinkedList<String> queue, int num) {
        if (!queue.isEmpty()) {
            int cur = 0;
            String top = queue.pollLast();
            if ("+".equals(top) || "-".equals(top)) {
                queue.addLast(top);
            } else {
                cur = Integer.parseInt(queue.pollLast());
                num = "*".equals(top) ? cur * num : cur / num;
            }
        }
        //如果队列是空, 直接加进去
        queue.addLast(String.valueOf(num));
    }

    //把队列中的内容(只有数字和+-号)算出结果
    public static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if ("+".equals(cur)) {
                add = true;
            } else if ("-".equals(cur)) {
                add = false;
            } else { //是数字
                int num = Integer.parseInt(cur);
                res += add ? num : -num;
            }
        }
        return res;
    }

    /*------------------------------------------------------------------------------------  */

    public static int calculate2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = shedSpace(s);

        LinkedList<String> queue = new LinkedList<>();

        int res = 0;
        int cur = 0;
        // "10+20+30+40/100";
        int i = 0;
        while (i < str.length) {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + (str[i] - '0');
                i++;
            } else {  //遇到了运算符
                addNum(queue, cur); //把上一个数字加入队列
                queue.addLast(String.valueOf(str[i]));
                i++;
                cur = 0;
            }
        }
        addNum(queue, cur);

        return getNum(queue);
    }

    // 来自Discuss
    public static int calculate3(String s) {
        int len;
        if (s == null || (len = s.length()) == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1) {
                // 当前字符是数字的后面一个字符, sign是前一个字符
                if (sign == '-') {
                    stack.push(-num);
                }
                if (sign == '+') {
                    stack.push(num);
                }
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }

        int re = 0;
        for (int i : stack) {
            re += i;
        }
        return re;
    }


    public static void main(String[] args) {
        String s = "3+2*2";
        System.out.println(calculate2(s));
    }

}
