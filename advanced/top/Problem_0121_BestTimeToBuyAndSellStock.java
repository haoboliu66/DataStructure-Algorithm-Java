package advanced.top;

public class Problem_0121_BestTimeToBuyAndSellStock {

    public int maxProfit(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            min = Math.min(arr[i], min);
            res = Math.max(arr[i] - min, res);
        }
        return res;
    }
}
