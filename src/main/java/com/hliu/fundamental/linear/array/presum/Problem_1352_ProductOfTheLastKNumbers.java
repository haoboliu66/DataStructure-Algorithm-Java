package com.hliu.fundamental.linear.array.presum;

import java.util.ArrayList;
import java.util.List;

/*

Design an algorithm that accepts a stream of integers and retrieves the product of the last k integers of the stream.

Implement the ProductOfNumbers class:

ProductOfNumbers() Initializes the object with an empty stream.
void add(int num) Appends the integer num to the stream.
int getProduct(int k) Returns the product of the last k numbers in the current list. You can assume that always the current list has at least k numbers.
The test cases are generated so that, at any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.

 */
public class Problem_1352_ProductOfTheLastKNumbers {

  /*
  这个题首先一看就是涉及到preSum的问题, 但是特别之处在于, 如果遇到一个数字是0, 那么计算product的时候无法连续
  所以要特别追踪这个0的位置, 如果遇到0, 那么这个preSum的值就要重计, 也即把0的位置填为1, 这样不影响后续的计算

  在计算last K乘机时, 要考虑0的影响, 所以要考虑追踪数组中0的位置, 由于是要从后往前计算product, 所以只追踪最后一个0的位置即可
   */

  static class ProductOfNumbers {

    private List<Integer> preProduct;
    int index;
    int lastZero;

    public ProductOfNumbers() {
      preProduct = new ArrayList<>();  //  index i, 表示前i个数字的乘积 (including i)  i.e. [0 1 2 3]  index 3
      index = 0;  // index 追踪数组的最后一个位置
      preProduct.add(1); // 填充一个dummy position, 方便计算preProduct
      index++;
      /*
      填充的原因是:
      0 1 2 3 4 5 ] 6 如果index是6, 那么如果我没有dummy position, 实际就加入了6个num, 如果我要计算6个数字的product
      这个preIndex就跑到-1位置, 就没有对应的值了;

      如果不填充也可以, 就是在计算时多加了一个edge case的判断, 参考下面的 class ProductOfNumbers1
       */

      lastZero = -1;  // 追踪最后一个0的位置, 便于从后往前计算product
    }

    public void add(int num) {
      if (num == 0) {
        lastZero = index;
        preProduct.add(1);
        index++;
        return;
      }
      preProduct.add(preProduct.get(index - 1) * num);
      index++;
    }

    // 0 1 2 3 4 5 ] 6
    public int getProduct(int k) {
      int preIndex = index - k - 1;
      /*
      如果我的这个preIndex位置在最后一个0的位置之前, 那么这个last K序列就包含了0, 所以这个product肯定是0
      e.g.  [ ......  (preIndex) ... (lastZero) ...... ] index
       */
      if (preIndex < lastZero) {
        return 0;
      }
      return preProduct.get(index - 1) / preProduct.get(preIndex);
    }
  }

  // 不填充dummy position的版本, 多处理了一个edge case (i.e. 要返回已加入的所有num的product)
  static class ProductOfNumbers1 {

    private List<Integer> preProduct;
    int index;
    int lastZero;

    public ProductOfNumbers1() {
      preProduct = new ArrayList<>();
      index = 0;
      lastZero = -1;
    }

    public void add(int num) {
      if (num == 0) {
        lastZero = index;
        preProduct.add(1);
        index++;
        return;
      }
      if (index == 0) {
        preProduct.add(num);
        index++;
        return;
      }
      preProduct.add(preProduct.get(index - 1) * num);
      index++;
    }

    // 0 1 2 3 4 5 ] 6
    public int getProduct(int k) {
      int preIndex = index - k - 1;
      if (preIndex < lastZero) {
        return 0;
      }
      if(preIndex < 0) {
        return preProduct.get(index - 1);
      }
      return preProduct.get(index - 1) / preProduct.get(preIndex);
    }
  }


}
