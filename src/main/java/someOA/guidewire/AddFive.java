package src.main.java.someOA.guidewire;

public class AddFive {

    public static void main(String[] args) {
        System.out.println(solution(268));
        System.out.println(solution(670));
        System.out.println(solution(1570));
        System.out.println(solution(50));
        System.out.println(solution(-199));
        System.out.println(solution(-1));
        System.out.println(solution(-10));
    }

    // -199
    public static int solution(int N) {
        if (N == 0) return 50;
        int max = N < 0 ? Integer.MIN_VALUE : N;
        String num = String.valueOf(N < 0 ? Math.abs(N) : N);
        int size = num.length();

        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i <= size; i++) {

            // if (i != 0 && i != size && num.charAt(i) >= '5') continue;

            StringBuilder copy = new StringBuilder(sb);
            copy.insert(i, 5);
            int curr = Integer.parseInt(copy.toString());
            max = Math.max(max, (N < 0 ? (-1) * curr : curr));
        }
        return max;
    }

}
