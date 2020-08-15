package advanced.c3.class5;


import java.util.*;

/**
 * @author andy-liu
 * @date 2020/7/5 - 4:30 PM
 */
public class C05_WordMinPaths {

    /*
    LeetCode 126. Word Ladder II
     */

    public static List<List<String>> findMinPaths(String start, String end, List<String> list) {
        list.add(start);
        //list中所有字符串生成邻居表
        HashMap<String, ArrayList<String>> nexts = getNexts(list);
        //求出所有字符串到start的距离, 所有的key到start的距离
        HashMap<String, Integer> distances = getDistance(start, nexts);
        LinkedList<String> pathList = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        getShortestPaths(start, end, nexts, distances, pathList, res);
        return res;
    }

    // 现在来到了什么：cur
    // 目的地：to
    // 邻居表：nexts
    // 最短距离表：distances
    // 沿途走过的路径：path上{....}
    // 答案往res里放，收集所有的最短路径
    /*
    深度优先遍历DFS
     */
    private static void getShortestPaths(String cur, String to, HashMap<String, ArrayList<String>> nexts, HashMap<String, Integer> distances, LinkedList<String> path, List<List<String>> res) {
        //来到了cur, 加入path
        path.add(cur);
        if (to.equals(cur)) { // to和cur相等, 说明到了终点, 收集答案
            res.add(new LinkedList<>(path)); //拷贝path, 加入res结果集

        } else {
            //遍历cur的每一个邻居
            for (String next : nexts.get(cur)) {
                //只有当邻居距离当前cur的距离是1, 才往下走
                if (distances.get(next) - distances.get(cur) == 1) {
                    getShortestPaths(next, to, nexts, distances, path, res);
                }
            }
        }
        //清理现场
        path.pollLast();
    }


    //生成邻居表, 类似Graph结构
    private static HashMap<String, ArrayList<String>> getNexts(List<String> list) {
        HashSet<String> dict = new HashSet<>(list);

        HashMap<String, ArrayList<String>> nexts = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            nexts.put(list.get(i), getNext(list.get(i), dict));
        }
        return nexts;
    }

    // 对于字符串s, 检查dict中的字符串是否是他的邻居, 是就加入数组返回
    private static ArrayList<String> getNext(String s, HashSet<String> dict) {
        ArrayList<String> neighbours = new ArrayList<>();
        char[] str = s.toCharArray();
        //循环枚举字符串s可能的所有邻居
        for (int i = 0; i < str.length; i++) {
            for (char cur = 'a'; cur <= 'z'; cur++) {
                if (str[i] != cur) {
                    char tmp = str[i];
                    str[i] = cur;  //把i位置字符变成cur
                    if (dict.contains(String.valueOf(str))) {
                        //如果当前可能的邻居出现在dict中, 就把他加入
                        neighbours.add(String.valueOf(str));
                    }
                    str[i] = tmp; //恢复改变的i位置字符
                }
            }
        }
        return neighbours;
    }


    //生成所有字符串到start的距离, 宽度优先遍历
    private static HashMap<String, Integer> getDistance(String start, HashMap<String, ArrayList<String>> nexts) {

        HashMap<String, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        HashSet<String> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (String next : nexts.get(cur)) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                    distances.put(next, distances.get(cur) + 1);
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        List<String> list = new ArrayList<>();
        //["hot","dot","dog","lot","log","cog"]
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        List<List<String>> res = findMinPaths(start, end, list);
        System.out.println(res);


    }


}
