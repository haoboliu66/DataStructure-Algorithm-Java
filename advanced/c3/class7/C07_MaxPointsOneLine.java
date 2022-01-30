package advanced.c3.class7;

import java.util.HashMap;

public class C07_MaxPointsOneLine {

    /*
    149. Max Points on a Line
     */
    public static int maxPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        HashMap<String, Integer> map = new HashMap<>();
        int res = 1;
        for (int i = 0; i < points.length; i++) {
            int sameX = 0;
            int sameY = 0;
            int samePosition = 1;
            int sameSlope = 0;
            map.clear();
            int x = points[i][0];
            int y = points[i][1];
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

                int total = samePosition + Math.max(Math.max(sameX, sameY), sameSlope);
                res = Math.max(res, total);
            }
        }

        return res;
    }

    // gcd(a, 0) == a,  gcd(a,b) = gcd(b, a % b)
    public static int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }
}
