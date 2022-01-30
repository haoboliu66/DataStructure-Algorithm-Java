package advanced.top;

import java.util.*;

public class Problem_0210_CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Map<Integer, Course> map = new HashMap<>();

        for (int[] pair : prerequisites) {
            int from = pair[0];
            int to = pair[1];
            if (!map.containsKey(from)) {
                map.put(from, new Course(from));
            }
            if (!map.containsKey(to)) {
                map.put(to, new Course(to));
            }
            Course fromCourse = map.get(from);
            Course toCourse = map.get(to);
            fromCourse.to.add(toCourse.from);
            toCourse.in++;
        }
        Queue<Course> queue = new LinkedList<>();
        for (Course c : map.values()) {
            if (c.in == 0) {
                queue.offer(c);
            }
        }
        if (queue.size() == 0) return new int[]{};

        int[] res = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {
            Course cur = queue.poll();
            res[index++] = cur.from;
            List<Integer> nexts = cur.to;
            for (int to : nexts) {
                Course course = map.get(to);
                if (--course.in == 0) {
                    queue.offer(course);
                }
            }
        }
        return res;
    }


    private static class Course {
        int from;
        int in;
        List<Integer> to;

        public Course(int from) {
            this.from = from;
            in = 0;
            to = new ArrayList<>();
        }
    }

}
