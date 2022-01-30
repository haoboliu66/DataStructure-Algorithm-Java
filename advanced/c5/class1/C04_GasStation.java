package advanced.c5.class1;

public class C04_GasStation {
    /*
    https://leetcode.com/problems/gas-station/
     */

    public static int canCompleteCircuit(int[] gas, int[] cost) {


        return 0;
    }

    public static int canCompleteCircuit0(int[] gas, int[] cost) {
        int[] help = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            help[i] = gas[i] - cost[i];
        }
        int n = gas.length;
        boolean good = true;
        for (int i = 0; i < n; i++) {
            int tank = 0;
            good = true;
            for (int cur = i; cur != prevIndex(i, n); cur++) {
                tank += help[cur];
                if (tank < 0) {
                    good = false;
                    break;
                }
            }
            if (good) {
                return i;
            }

        }
        return -1;
    }


    public static int right(int[] gas, int[] cost) {
        int res = 0;
        for (int i = 0; i < gas.length; i++) {


        }

        return 0;
    }

    public static int nextIndex(int i, int size) {
        return i < size - 1 ? i + 1 : 0;
    }

    public static int prevIndex(int i, int size) {
        return i == 0 ? size - 1 : i - 1;
    }

}
