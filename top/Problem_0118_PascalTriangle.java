package advanced.top;

import org.omg.PortableInterceptor.INACTIVE;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_0118_PascalTriangle {

    public static List<List<Integer>> generate(int n) {

        List<List<Integer>> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        List<Integer> row = new ArrayList<>();
        row.add(1);
        res.add(row);

        for (int i = 1; i < n; i++) {
            row = new ArrayList<>(i + 1);

            for (int j = 0; j < i; j++) {
                if (j == 0) row.add(1);
                else {
                    row.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }

            }
            row.add(1);
            res.add(row);
        }
        return res;
    }


    public static void main(String[] args) throws NoSuchFieldException {
        List<List<Integer>> res = generate(5);
        System.out.println(res);

    }
}
