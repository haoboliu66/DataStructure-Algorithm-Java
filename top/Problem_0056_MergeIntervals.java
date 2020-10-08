package advanced.top;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Problem_0056_MergeIntervals {

    public int[][] merge(int[][] intervals) {

        Range[] ranges = new Range[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            ranges[i] = new Range(intervals[i][0], intervals[i][1]);
        }

        if (ranges.length == 0) {
            return new int[0][0];
        }

//        Arrays.sort(ranges, new RangeComparator());
        Arrays.sort(ranges, (r1, r2) -> r1.start - r2.start);

        List<Range> res = new ArrayList<>();

        int s = ranges[0].start;
        int e = ranges[0].end;

        for (int i = 1; i < ranges.length; i++) {
            Range cur = ranges[0];
            if (cur.start > e) {
                res.add(new Range(s, e));
                s = cur.start;
            }
            e = Math.max(e, cur.end);
        }
        res.add(new Range(s, e));

        // transform List into int[][]
        int[][] ans = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = new int[]{res.get(i).start, res.get(i).end};
        }

        return ans;
    }

//    public static class RangeComparator implements Comparator<Range> {
//        @Override
//        public int compare(Range o1, Range o2) {
//            return o1.start - o2.start;
//        }
//    }

    public static class Range {

        int start;
        int end;

        public Range(int s, int e) {
            start = s;
            end = e;
        }
    }


}
