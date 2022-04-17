package src.main.java.advanced.c4.class2;

public class C01_BestTimetoBuyandSellStock1 {
    /*
    121. Best Time to Buy and Sell Stock
    */

    /*
    只进行一次交易, 找到[i]前面的最小值, 计算每个[i] - min, 答案必在其中
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
