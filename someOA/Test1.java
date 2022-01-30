package someOA;

import java.util.List;

public class Test1 {


    // 4 4 9 2 3
    // 4 + 0
    public static long calculateAmount(List<Integer> prices) {

        int min = prices.get(0);
        int total = prices.get(0);
        for (int i = 1; i < prices.size(); i++) {
            total += Math.max(0, (prices.get(i) - min));
            min = Math.min(min, prices.get(i));
        }
        return total;
    }

}
