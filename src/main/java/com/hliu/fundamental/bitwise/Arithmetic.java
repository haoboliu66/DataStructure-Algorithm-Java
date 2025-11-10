package com.hliu.fundamental.bitwise;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Arithmetic {

  public static void main(String[] args) {

    TreeSet<Coupon> set = new TreeSet<>();

    Coupon x1 = new Coupon("j", "electronics", true);
    Coupon x2 = new Coupon("j", "electronics", true);
    set.add(x1);
    set.add(x2);
    System.out.println(set.size());
  }

  public static int add(int a, int b) {
    int carry = ((a & b) << 1);
    while (carry != 0) {
      int tmp = a;
      a = a ^ b;
      b = tmp & b;
    }
    return a ^ b;
  }


  private static class Coupon implements Comparable<Coupon> {

    public String code;
    public String businessLine;
    public boolean isActive;

    public Coupon(String code, String businessLine, boolean isActive) {
      this.code = code;
      this.businessLine = businessLine;
      this.isActive = isActive;
    }

    @Override
    public int compareTo(Coupon coupon) {
      if (this.businessLine.compareTo(coupon.businessLine) != 0) {
        return this.businessLine.compareTo(coupon.businessLine);
      } else if (this.code.compareTo(coupon.code) != 0) {
        return this.code.compareTo(coupon.code);
      } else {
        return this == coupon ? 0 : 1;
      }
    }

    @Override
    public boolean equals(Object o) {
      return this == o;
    }
  }

  public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
    TreeSet<Coupon> treeSet = new TreeSet<>();
    for (int i = 0; i < code.length; i++) {
      if (isActive[i] && validBusinessLine(businessLine[i]) && validCode(code[i])) {
        treeSet.add(new Coupon(code[i], businessLine[i], isActive[i]));
      }
    }

    return treeSet.stream()
                  .map(c -> c.code)
                  .collect(Collectors.toList());
  }

  private boolean validBusinessLine(String businessLine) {
    return "electronics".equals(businessLine) || "grocery".equals(businessLine) || "pharmacy".equals(businessLine) ||
        "restaurant".equals(businessLine);

  }

  private boolean validCode(String code) {
    if (code == null || code.length() == 0) {
      return false;
    }
    char[] str = code.toCharArray();
    for (int i = 0; i < str.length; i++) {
      if (Character.isDigit(str[i]) || Character.isLetter(str[i]) || str[i] == '_') {
        continue;
      }
      return false;

    }
    return true;
  }

}
