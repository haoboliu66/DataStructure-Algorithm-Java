package advanced.c4.class2;

/**
 * @author andy-liu
 * @date 2020/7/18 - 12:12 AM
 */
public class C01_BestTimetoBuyandSellStock1 {
    /*
    121. Best Time to Buy and Sell Stock
    */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            profit = Math.max(profit, prices[i] - min);
        }

        return profit;
    }

}
