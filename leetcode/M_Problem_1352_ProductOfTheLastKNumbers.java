package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class M_Problem_1352_ProductOfTheLastKNumbers {

    /*  not done yet */

    public static void main(String[] args) {
        ProductOfNumbers pon = new ProductOfNumbers();
        pon.add(3);
        pon.add(0);
        pon.add(2);
        pon.add(5);
        pon.add(4);
        System.out.println("pon.getProduct(2) = " + pon.getProduct(2));
        System.out.println("pon.getProduct(3) = " + pon.getProduct(3));
        System.out.println("pon.getProduct(4) = " + pon.getProduct(4));
    }

    static class ProductOfNumbers {

        List<Integer> list = new ArrayList<>();;
        Stack<Integer> lastZero = new Stack<>();
        int index = 0;

        public ProductOfNumbers() {
        }

        public void add(int num) {
            if(num == 0){
                list.add(0);
                lastZero.push(index);
            }else{
                // num != 0
                if(index == 0 || list.get(index-1) == 0){
                    list.add(num);
                }else{
                    int pre = list.get(index - 1) * num;
                    list.add(pre);
                }
            }
            index++;
            System.out.println(list);
        }

        public int getProduct(int k) {
            int zero = lastZero.peek();
            int targetIndex = list.size() - k;
            if(targetIndex < zero) return 0;
            return list.get(targetIndex) / list.get(index - zero - 1);
        }
    }

}
