package advanced.top;

public class Problem_0122_BestTimeToBuyAndSellStockII {

    // 无限次交易的实质: 把每一个上坡的差值累加起来

    public int maxProfit(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i] > arr[i - 1] ? arr[i] - arr[i - 1] : 0;

        }
        return sum;
    }
}
