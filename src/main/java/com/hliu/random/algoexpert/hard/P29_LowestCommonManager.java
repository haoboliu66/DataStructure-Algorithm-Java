package com.hliu.random.algoexpert.hard;

import java.util.ArrayList;
import java.util.List;

/*
lowest common ancestor
 */
public class P29_LowestCommonManager {

  public static void main(String[] args) {

    OrgChart h = new OrgChart('H');
    OrgChart i = new OrgChart('I');

    OrgChart d = new OrgChart('D');
    OrgChart e = new OrgChart('E');
    OrgChart f = new OrgChart('F');
    OrgChart g = new OrgChart('G');

    OrgChart b = new OrgChart('B');
    OrgChart c = new OrgChart('C');

    OrgChart a = new OrgChart('A');

    a.addDirectReports(new OrgChart[]{b, c});
    b.addDirectReports(new OrgChart[]{d, e});
    c.addDirectReports(new OrgChart[]{f, g});
    d.addDirectReports(new OrgChart[]{h, i});

    OrgChart res = getLowestCommonManager(a, e, i);
    System.out.println(res);
  }


  public static OrgChart getLowestCommonManager(OrgChart root, OrgChart node1, OrgChart node2) {
    if (root == null) {
      return null;
    }
    return process(root, node1, node2).common;
  }

  public static Info process(OrgChart node, OrgChart node1, OrgChart node2) {
    boolean hasOne = false, hasTwo = false;
    OrgChart common = null;
    List<OrgChart> nexts = node.directReports;
    if (nexts.isEmpty()) {  // leaf node
      hasOne = node == node1;
      hasTwo = node == node2;
      return new Info(common, hasOne, hasTwo);
    }

    for (OrgChart nextNode : nexts) {
      Info info = process(nextNode, node1, node2);
      hasOne = hasOne ||    // nexts节点中, 只要有一个节点满足hasOne or hasTwo, 这两个值就是true
          info.hasOne || node == node1;
      hasTwo = hasTwo ||
          info.hasTwo || node == node2;
      if (hasOne && hasTwo) {
        common = (info.common != null ? info.common : node);
        break;
      }
    }

    return new Info(common, hasOne, hasTwo);
  }

  static class Info {

    OrgChart common;
    boolean hasOne;
    boolean hasTwo;

    public Info(OrgChart common, boolean hasOne, boolean hasTwo) {
      this.common = common;
      this.hasOne = hasOne;
      this.hasTwo = hasTwo;
    }
  }


  static class OrgChart {

    public char name;
    public List<OrgChart> directReports;

    OrgChart(char name) {
      this.name = name;
      this.directReports = new ArrayList<OrgChart>();
    }

    // This method is for testing only.
    public void addDirectReports(OrgChart[] directReports) {
      for (OrgChart directReport : directReports) {
        this.directReports.add(directReport);
      }
    }

    @Override
    public String toString() {
      return "OrgChart{" +
          "name=" + name +
          '}';
    }
  }

}
