package top;

import java.util.*;

public class Problem_0207_CourseSchedule {

    // graph
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        HashMap<Integer, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        for (int[] course : prerequisites) {
            int pre = course[1];
            int next = course[0];
            if (!map.containsKey(pre)) {
                map.put(pre, new Node(pre));
            }
            if (!map.containsKey(next)) {
                map.put(next, new Node(next));
            }
            Node from = map.get(pre);
            Node to = map.get(next);
            from.nexts.add(to);
            to.in++;
        }
//        if (map.size() == numCourses) {   [1,0] [0,1] ==> 2
//            return true;
//        }
        int courseNum = map.size();
        int count = 0;
        for (Node n : map.values()) {
            if (n.in == 0) {
                queue.add(n);
            }
        }
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            count++;
            List<Node> neighbours = cur.nexts;
            for (Node n : neighbours) {
                if (--n.in == 0) {
                    queue.add(n);
                }
            }
        }

        return count == courseNum;
    }

    public static class Node {
        int id;
        int in;
        List<Node> nexts;

        public Node(int id) {
            in = 0;
            this.id = id;
            nexts = new ArrayList<>();
        }
    }


}
