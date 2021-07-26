package advanced.top;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem_0253_MeetingRoomsII {

    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        int N = intervals.length;
        Interval[] windows = new Interval[N];
        PriorityQueue<Interval> minHeap = new PriorityQueue<>();
        for (int[] window : intervals) {
            Interval interval = new Interval(window[0], window[1]);
            minHeap.add(interval);
        }
        int num = 1;
        Interval first = minHeap.poll();
        Interval cur;
        int start = first.start;
        int end = first.end;
        while (!minHeap.isEmpty()) {
            cur = minHeap.poll();
            if (cur.start < first.end) {
                num++;
                cur.end = Math.min(first.end, cur.end);
            }
            first = cur;
        }

        return num;
    }


    private static class Interval implements Comparable<Interval> {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Interval o) {
            return this.start - o.start;
        }
    }





    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        // Sort the intervals by start time
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) { return a.start - b.start; }
        });

        // Use a min heap to track the minimum end time of merged intervals
        PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) { return a.end - b.end; }
        });

        // start with the first meeting, put it to a meeting room
        heap.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            // get the meeting room that finishes earliest
            Interval interval = heap.poll();

            if (intervals[i].start >= interval.end) {
                // if the current meeting starts right after
                // there's no need for a new room, merge the interval
                interval.end = intervals[i].end;
            } else {
                // otherwise, this meeting needs a new room
                heap.offer(intervals[i]);
            }

            // don't forget to put the meeting room back
            heap.offer(interval);
        }

        return heap.size();
    }



}
