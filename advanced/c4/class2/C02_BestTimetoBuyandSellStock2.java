package advanced.c4.class2;

public class C02_BestTimetoBuyandSellStock2 {

    /*
    122. Best Time to Buy and Sell Stock II
    unlimited transactions
     */

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0;
        }

        return profit;
    }
}
