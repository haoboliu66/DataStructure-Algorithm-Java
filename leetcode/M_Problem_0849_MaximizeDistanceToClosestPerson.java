package leetcode;

import java.util.ArrayList;
import java.util.List;

public class M_Problem_0849_MaximizeDistanceToClosestPerson {

    /*
    Input: seats = [1,0,0,0,1,0,1]
    Output: 2
    Explanation:
    If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
    If Alex sits in any other open seat, the closest person has distance 1.
    Thus, the maximum distance to the closest person is 2.

    Input: seats = [1,0,0,0]
    Output: 3
    Explanation:
    If Alex sits in the last seat (i.e. seats[3]), the closest person is 3 seats away.
    This is the maximum distance possible, so the answer is 3.
     */

    public int maxDistToClosest(int[] seats) {
        if (seats == null || seats.length < 2) return 0;
        List<Integer> people = new ArrayList<>();
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                people.add(i);
            }
        }

        return 0;

    }
}
