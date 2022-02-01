package src.main.java.advanced.c3.class4;

import java.util.Arrays;
import java.util.Comparator;


public class C05_EnvelopesProblem {

    /*
    LeetCode 354. Russian Doll Envelopes
     */

    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length < 1) {
            return 0;
        }
        if (envelopes.length == 1) {
            return 1;
        }
        Envelope[] arr = new Envelope[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            arr[i] = new Envelope(envelopes[i][0], envelopes[i][1]);
        }
        Arrays.sort(arr, new EnvelopeComparator());
        int[] ends = new int[arr.length];
        ends[0] = arr[0].height;
        int right = 0;
        int l;
        int r;
        int m;
        for (int i = 0; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = l + ((r - l) >> 1);
                if (ends[m] >= arr[i].height) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i].height;
        }

        return right + 1;
    }

    public static class Envelope {
        int width;
        int height;

        public Envelope(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    public static class EnvelopeComparator implements Comparator<Envelope> {
        @Override
        public int compare(Envelope o1, Envelope o2) {
            return o1.width != o2.width ? o1.width - o2.width : o2.height - o1.height;
        }
    }


    public static void main(String[] args) {
        int[][] test = { { 3, 4 }, { 2, 3 }, { 4, 5 }, { 1, 3 }, { 2, 2 }, { 3, 6 }, { 1, 2 }, { 3, 2 }, { 2, 4 } };
        System.out.println(maxEnvelopes(test));
    }

}
