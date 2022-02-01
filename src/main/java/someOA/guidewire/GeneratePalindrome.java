package src.main.java.someOA.guidewire;

public class GeneratePalindrome {

    public static void main(String[] args) {
        int len = 11;
        int k = 6;
        System.out.println(solution(len, k));
        // a b c d e f
        // abcde f edcba
    }

    public static String solution(int len, int k) {
        if (k > (len + 1) / 2) return "";

        char[] res = new char[len];
        char[] candidate = new char[k];
        for (int i = 0; i < candidate.length; i++) {
            candidate[i] = (char) (i + 'a');
        }
        int left = 0, right = res.length - 1;
        int index = -1;
        while (left <= right) {
            int next = nextIndex(candidate, index++);
            char curr = candidate[next];
            res[left++] = curr;
            res[right--] = curr;
        }

        return new String(res);
    }

    public static int nextIndex(char[] candidate, int i) {
        return i != candidate.length - 1 ? i + 1 : 0;
    }

}
