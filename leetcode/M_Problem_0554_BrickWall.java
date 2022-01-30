package leetcode;

import java.util.HashMap;
import java.util.List;

public class M_Problem_0554_BrickWall {

    /* HashMap */

    public int leastBricks(List<List<Integer>> wall) {
        int total = wall.size();
        // map <a,b> 以长度a结尾的brick有b个
        HashMap<Integer, Integer> map = new HashMap<>();
        for (List<Integer> row : wall) {
            int sum = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                int brick = row.get(i);
                sum += brick;
                if (!map.containsKey(sum)) {
                    map.put(sum, 1);
                } else {
                    map.put(sum, map.get(sum) + 1);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int count : map.values()) {
            min = Math.min(min, total - count);
        }

        return min == Integer.MAX_VALUE ? total : min;
    }
}
