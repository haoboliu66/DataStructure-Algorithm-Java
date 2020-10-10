package top;

import java.util.HashMap;

// top16
public class Problem_0149_MaxPointsOnALine {

    public static int maxPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        HashMap<String, Integer> map = new HashMap<>();
        int res = 1;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int sameX = 0;
            int sameY = 0;
            int samePosition = 1;
            int sameSlope = 0;
            int x = points[i][0];
            int y = points[i][1];
            // stand at one point(x,y) and compare each point after
            for (int j = i + 1; j < points.length; j++) {
                if (points[j][0] == x && points[j][1] == y) {
                    samePosition++;
                } else if (points[j][0] == x) {
                    sameX++;
                } else if (points[j][1] == y) {
                    sameY++;
                } else {
                    // same slope
                    int xDiff = points[j][0] - x;
                    int yDiff = points[j][1] - y;
                    int gcd = gcd(xDiff, yDiff);
                    xDiff /= gcd;
                    yDiff /= gcd;
                    String slope = xDiff + "_" + yDiff;
                    if (!map.containsKey(slope)) {
                        map.put(slope, 1);
                    } else {
                        map.put(slope, map.get(slope) + 1);
                    }
                    sameSlope = Math.max(map.get(slope), sameSlope);
                }

                int total = Math.max(Math.max(sameX, sameY), sameSlope) + samePosition;
                res = Math.max(res, total);
            }
        }

        return res;
    }

    // gcd(a, 0) == a,  gcd(a,b) = gcd(b, a % b)
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        int[][] points = {{1, 1}, {2, 2}, {3, 3}};
        int[][] points1 = {{0, 0}, {1, 65536}, {65536, 0}};
        int[][] points2 = {{3, 10}, {0, 2}, {0, 2}, {3, 10}};
        System.out.println(maxPoints(points2));
    }
}
