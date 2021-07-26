package advanced.top;

public class Problem_0084_LargestRectangleInHistogram {

    public static void main(String[] args) {


        System.out.println(pow(2, 1024));
    }


    public static int[][] pow(int[][] matrix1, int[][] matrix2) {
        int m = matrix1.length, n = matrix2[0].length;

        int x = matrix1[0].length, y = matrix2.length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < matrix2.length; k++) {
                    matrix1[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }


        return res;
    }


    public static double pow(int base, int power) {
        int res = 1;
        while (power != 0) {
            boolean rightMostOne = (power & 1) == 1;
            if (rightMostOne) {
                res *= base;
            }
            base *= base;
            power >>>= 1;
        }
        return res;
    }
//
//    public int largestRectangleArea(int[] heights) {
//
//        int maxArea = Integer.MIN_VALUE;
//        Stack<Integer> stack = new Stack<>();
//        for (int i = 0; i < heights.length; i++) {
//
//            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
//                int curIndex = stack.pop();
//                int curArea =  (i - curIndex + 1) * Math.min(heights[i], heights[curIndex]);
//                maxArea = Math.max(maxArea, curArea);
//            }
//            stack.push(i);
//        }
//
//
//    }
}
