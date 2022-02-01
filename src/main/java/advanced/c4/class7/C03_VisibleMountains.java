package src.main.java.advanced.c4.class7;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class C03_VisibleMountains {

    public static void main(String[] args) {
        int maxSize = 100, maxVal = 250, times = 20000;
        int[] arr = generateRandomArray(maxSize, maxVal);
        System.out.println("Go");
        for (int i = 0; i < times; i++) {
            if (rightGetVisibleMountains(arr) != getVisibleNum(arr)) {
                System.out.println("Oops");
                System.out.println(Arrays.toString(arr));
                System.out.println(rightGetVisibleMountains(arr));
                System.out.println(getVisibleNum(arr));
                break;
            }
        }
        System.out.println("Done");
    }

    private static int countVisibleMountains(int[] arr) {
        if (arr.length == 1) return 0;
        if (arr.length == 2) return 1;
        if (arr.length == 3) return 3;
        return (arr.length - 2) * 2 + 1;
    }

    private static int rightNums(int[] arr) {
        if (arr.length == 1) return 0;
        if (arr.length == 2) return 1;
        if (arr.length == 3) return 3;
        for (int i = 0; i < arr.length; i++) {

        }
        return 0;
    }

    private static int rightGetVisibleMountains(int[] arr) {
        if (arr.length == 1) return 0;
        if (arr.length == 2) return 1;
        if (arr.length == 3) return 3;
        Set<int[]> visited = new HashSet<>();
        int count = 0, index = 0, n = arr.length;
        for (; index < n; index++) {
            int cur = arr[index];
            int maxOnNext = cur;
            int maxOnPre = cur;
            visited.clear();

            int nextIndex = next(index, n);
            if (arr[nextIndex] >= cur) {
                visited.add(new int[]{arr[nextIndex], nextIndex});
                nextIndex = next(nextIndex, n);
                maxOnNext = arr[nextIndex];
                count++;
            }
            while (nextIndex != index) {
                if (arr[nextIndex] < cur || arr[nextIndex] < maxOnNext) {
                    nextIndex = next(nextIndex, n);
                    continue;
                }
                if (arr[nextIndex] >= cur && cur >= maxOnNext) {
                    visited.add(new int[]{arr[nextIndex], nextIndex});
                    count++;
//                    break;
                }
                nextIndex = next(nextIndex, n);
                maxOnNext = Math.max(maxOnNext, arr[nextIndex]);
            }

            int preIndex = prev(index, n);
            if (arr[preIndex] >= cur) {
                visited.add(new int[]{arr[preIndex], preIndex});
                preIndex = prev(preIndex, n);
                maxOnPre = arr[preIndex];
                count++;
            }
            while (preIndex != index) {
                if (arr[preIndex] < cur || arr[preIndex] < maxOnPre) {
                    preIndex = prev(preIndex, n);
                    continue;
                }
//                if (preIndex != prev(index, n) && maxOnPre > cur) break;
                if (!visited.contains(new int[]{arr[preIndex], preIndex})
                        && arr[preIndex] >= cur
                        && cur >= maxOnPre) {
                    visited.add(new int[]{arr[preIndex], preIndex});
                    count++;
//                    break;
                }
                preIndex = prev(preIndex, n);
                maxOnPre = Math.max(maxOnPre, arr[preIndex]);
            }
        }
        return count;
    }

    private static int prev(int i, int n) {
        return i > 0 ? i - 1 : n - 1;
    }

    private static int next(int i, int n) {
        return i < n - 1 ? i + 1 : 0;
    }


    // for test
    public static int[] generateRandomNoDuplicateArray(int maxSize, int maxValue) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < (int) ((Math.random()) * maxSize) + 1; i++) {
            set.add((int) ((Math.random()) * (maxValue + 1)));
        }
        int[] arr = new int[set.size()];
        int i = 0;
        for (int n : set) {
            arr[i++] = n;
        }
        return arr;
    }

    private static int[] generateRandomArray(int maxVal, int maxLen) {
        int size = (int) (Math.random() * maxLen) + 1;
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = (int) (Math.random() * maxVal);
        }
        return res;
    }

    /* -------------------------- */


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
