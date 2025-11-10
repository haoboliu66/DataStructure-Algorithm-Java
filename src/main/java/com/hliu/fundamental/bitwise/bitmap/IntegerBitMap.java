package com.hliu.fundamental.bitwise.bitmap;

public class IntegerBitMap {

  private final int[] bitMap;

  public IntegerBitMap(int max) {
    bitMap = new int[(max + 32) >> 4];
  }

  public boolean contains(int num) {
    return (bitMap[num >> 4] & (1 << (num & 31))) != 0;
  }

  public void add(int num) {
    bitMap[num >> 4] |= (1 << (num & 31));
  }

  public void delete(int num) {
    bitMap[num >> 4] &= ~(1 << (num & 31));
  }

}
