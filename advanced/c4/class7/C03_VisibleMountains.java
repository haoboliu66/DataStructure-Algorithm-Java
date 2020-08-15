package advanced.c4.class7;


import java.util.Stack;

/**
 * @author andy-liu
 * @date 2020/8/1 - 3:23 PM
 */
public class C03_VisibleMountains {


    public static class Record {
        int value;
        int times;

        public Record(int value) {
            this.value = value;
            times = 1;
        }
    }

    public static int getVisibleNum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int maxIndex = 0;
        //找到max值所在的index
        for (int i = 1; i < N; i++) {
            maxIndex = arr[i] > arr[maxIndex] ? i : maxIndex;
        }

        Stack<Record> stack = new Stack<>();  //栈底到栈顶, 由大到小
        stack.push(new Record(arr[maxIndex]));

        int index = nextIndex(maxIndex, N);
        int res = 0;
        while (index != maxIndex) {
            while (stack.peek().value < arr[index]) {
                int times = stack.pop().times;
                // 每个mountain, 弹出结算, 可以有:外部k * 2 + 内部C(k,2)个山峰对
                res += times * 2 + getInternalNum(times);
            }

            //对于当前元素arr[index], 入栈情况分两种:
            if (stack.peek().value == arr[index]) { //栈顶的值 == arr[index]
                stack.peek().times++;
            } else {
                stack.push(new Record(arr[index]));
            }

            index = nextIndex(index, N);
        }

        while (stack.size() > 2) {
            int times = stack.pop().times;
            res += times * 2 + getInternalNum(times);
        }
        if (stack.size() == 2) {
            int second = stack.pop().times;
            res += getInternalNum(second) + stack.peek().times == 1 ? second : second * 2;
        }

        res += getInternalNum(stack.peek().times);

        return res;
    }

    //  返回C(k, 2)
    private static int getInternalNum(int k) {
        return k * (k - 1) / 2;
    }


    public static int nextIndex(int i, int size) {
        return i < size - 1 ? i + 1 : 0;
    }

    public static int lastIndex(int i, int size) {
        return i > 0 ? i - 1 : size - 1;
    }


}
