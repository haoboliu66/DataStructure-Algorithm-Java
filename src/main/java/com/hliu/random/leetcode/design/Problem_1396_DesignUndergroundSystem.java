package com.hliu.random.leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_1396_DesignUndergroundSystem {

  static class Record {

    String start;
    int startTime;

    public Record(String start, int startTime) {
      this.start = start;
      this.startTime = startTime;
    }
  }

  static class UndergroundSystem {

    Map<Integer, Record> peopleMap;
    Map<String, Map<String, List<Integer>>> timeMap;

    public UndergroundSystem() {
      peopleMap = new HashMap<>();
      timeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
      if (peopleMap.containsKey(id)) {
        return;
      }
      Record record = new Record(stationName, t);
      peopleMap.put(id, record);
    }

    public void checkOut(int id, String stationName, int t) {
      if (!peopleMap.containsKey(id)) {
        return;
      }
      Record record = peopleMap.get(id);

      timeMap.computeIfAbsent(record.start, x -> new HashMap<>())
             .computeIfAbsent(stationName, x -> new ArrayList<>())
             .add(t - record.startTime);

      peopleMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
      List<Integer> timeList = timeMap.get(startStation)
                                      .get(endStation);
      double sum = 0.0d;
      for (int t : timeList) {
        sum += t;
      }
      return sum / timeList.size();
    }
  }


  static class UndergroundSystem2 {

    Map<Integer, Record> peopleMap;
    Map<String, Map<String, int[]>> timeMap;
    // int[] => int[0] sum of the time;   int[1]  travel count

    public UndergroundSystem2() {
      peopleMap = new HashMap<>();
      timeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
      if (peopleMap.containsKey(id)) {
        return;
      }
      Record record = new Record(stationName, t);
      peopleMap.put(id, record);
    }

    public void checkOut(int id, String stationName, int t) {
      if (!peopleMap.containsKey(id)) {
        return;
      }
      Record record = peopleMap.get(id);

      timeMap.computeIfAbsent(record.start, x -> new HashMap<>())
             .computeIfAbsent(stationName, x -> new int[2]);
      int[] pair = timeMap.get(record.start)
                          .get(stationName);
      pair[0] += t - record.startTime;
      pair[1] += 1;

      peopleMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
      int[] pair = timeMap.get(startStation)
                          .get(endStation);

      return (double) pair[0] / pair[1];
    }
  }


}
