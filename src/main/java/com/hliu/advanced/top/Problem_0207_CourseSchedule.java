package src.main.java.advanced.top;

import java.util.*;

public class Problem_0207_CourseSchedule {


    public static void main(String[] args) {
        int mask = 0x55555555;
        System.out.println(Integer.toBinaryString(mask));
        System.out.println(mask);
    }

    // src.main.java.com.hliu.fundamental.graph
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

    /* -----------------------------------  */

    public boolean canFinish2(int num, int[][] prerequisites) {
        if (num <= 1 || prerequisites.length <= 1) return true;
        // Id -> Course
        HashMap<Integer, Course> courseMap = new HashMap<>();

        // explore all pairs and establish dependency relationship
        for (int i = 0; i < prerequisites.length; i++) {
            int restrictedCourseId = prerequisites[i][0];
            int conditionCourseId = prerequisites[i][1];
            if (!courseMap.containsKey(restrictedCourseId)) {
                Course c = new Course(restrictedCourseId);
                courseMap.put(restrictedCourseId, c);
            }
            if (!courseMap.containsKey(conditionCourseId)) {
                Course c = new Course(conditionCourseId);
                courseMap.put(conditionCourseId, c);
            }
            Course restrictedCourse = courseMap.get(restrictedCourseId);
            Course conditionCourse = courseMap.get(conditionCourseId);
            conditionCourse.in++;
            restrictedCourse.nexts.add(conditionCourseId);
        }

        Queue<Course> queue = new LinkedList<>();
        int total = courseMap.size();
        // O(M)
        for (Course c : courseMap.values()) {
            if (c.in == 0) {
                queue.add(c);
            }
        }
        while (!queue.isEmpty()) {
            Course cur = queue.poll();
            total--;
            for (int nextId : cur.nexts) {
                Course nextCourse = courseMap.get(nextId);
                if (--nextCourse.in == 0) {
                    queue.add(nextCourse);
                }
            }
        }
        return total == 0;
    }

    private static class Course {
        int id;
        int in;
        List<Integer> nexts;

        public Course(int id) {
            this.id = id;
            this.in = 0;
            nexts = new ArrayList<>();
        }
    }


}
