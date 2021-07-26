package advanced.top;

import java.util.HashSet;
import java.util.Set;

public class Problem_0277_FindTheCelebrity {

    private boolean knows(int a, int b) {
        return false;
    }

    public int findCelebrity0(int n) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(i);
            for (int j = 0; j < n; j++) {

                if (i != j && (knows(i, j) || !knows(j, i))) {
                    set.remove(i);
                }

            }
        }
        if (set.size() == 0) return -1;
        return set.iterator().next();
    }


    public int findCelebrity(int n) {
        return 0;
    }


}
