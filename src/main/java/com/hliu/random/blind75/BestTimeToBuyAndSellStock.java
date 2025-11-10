package com.hliu.random.blind75;

public class BestTimeToBuyAndSellStock {

  public int maxProfit(int[] prices) {
    int max = 0;
    int minPrice = prices[0];
    for (int i = 1; i < prices.length; i++) {
      int profit = prices[i] - minPrice;
      max = Math.max(profit, max);
      if (prices[i] < minPrice) {
        minPrice = prices[i];
      }
    }
    return max;
  }

}
