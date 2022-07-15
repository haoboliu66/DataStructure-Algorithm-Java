package com.hliu.advanced.c3.class3;

import java.util.*;

/*
    每种工作有难度和报酬，规定如下
    class Job {
        public int money;// 该工作的报酬
        public int hard; // 该工作的难度
    }

    给定一个Job类型的数组jobarr，表示所有岗位，每个岗位都可以提供任意份工作
    选工作的标准是在难度不超过自身能力值的情况下，选择报酬最高的岗位
    给定一个int类型的数组arr，表示所有人的能力
    返回int类型的数组，表示每个人按照标准选工作后所能获得的最高报酬

 */
public class C01_MostProfitAssigningWork {
    /* 826. Most Profit Assigning Work
    https://leetcode.com/problems/most-profit-assigning-work/
    */

  public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
    int n = profit.length;
    List<Job> jobs = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      jobs.add(new Job(difficulty[i], profit[i]));
    }
        /*
         工作排序原则:
         难度:小->大; 难度相同的, 钱:大->小
         */
    Collections.sort(jobs, (a, b) -> a.hard != b.hard ? a.hard - b.hard : b.profit - a.profit);

    TreeMap<Integer, Integer> jobMap = new TreeMap<>();

    /*
     hard去重, 相同hard只保留profit高的, 且要保证: hard升高, profit一定多
     */
    jobMap.put(jobs.get(0).hard, jobs.get(0).profit);
    Job pre = jobs.get(0);
    for (int i = 1; i < jobs.size(); i++) {
      // 1.如果[i]和pre的hard相同, 那么根据排序原则, 意味着: [i]的钱一定 <= pre, 直接忽略[i]
      // 2.保证hard变高, profit也要多
      if (jobs.get(i).hard != pre.hard && jobs.get(i).profit > pre.profit) {
        jobMap.put(jobs.get(i).hard, jobs.get(i).profit);
        pre = jobs.get(i);
      }
    }
//        for (int i = 0; i < jobs.size(); i++) {
//            Job curJob = jobs.get(i);
//            int curHard = curJob.hard;
//            int curProfit = curJob.profit;
//            Integer lower = jobMap.floorKey(curHard);
//            // 如果lower 不为null && 当前profit <= 前面的profit
//            if (lower != null && curProfit <= jobMap.get(lower)) {
//                continue;
//            } else if (lower == null) {
//                jobMap.put(curHard, curProfit);
//            } else {
//                jobMap.put(curHard, curProfit);
//            }
//        }
    int max = 0;
    for (int i = 0; i < worker.length; i++) {
      int curAbility = worker[i];
      Integer hard = jobMap.floorKey(curAbility);
      if (hard != null) {
        max += jobMap.get(hard);
      }
    }
    return max;
  }


  private static class Job {

    int hard;
    int profit;

    public Job(int h, int p) {
      hard = h;
      profit = p;
    }

    @Override
    public String toString() {
      return "Job{" +
          "hard=" + hard +
          ", profit=" + profit +
          '}';
    }
  }

  // for test
  public static void main(String[] args) {
    int[] difficulty = {68, 35, 52, 47, 86};
    int[] profit = {67, 17, 1, 81, 3};
    int[] worker = {92, 10, 85, 84, 82};
    int res = maxProfitAssignment(difficulty, profit, worker);
    System.out.println(res);
  }


}
