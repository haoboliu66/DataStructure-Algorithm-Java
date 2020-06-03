package tree.recursion;

/**
 * @author andy-liu
 * @date 2020/5/24 - 8:46 AM
 */
public class PrintFold {

    public static void printAllFold(int N) {
        process(1, N, true); // 根节点一定是down
        System.out.println();
    }

    public static void process(int i, int N, boolean down) {
        //inorder traversal
        if (i > N) {
            return;
        }
        process(i + 1, N, true); //左树一定是down
        System.out.print(down ? "down " : "up ");
        process(i + 1, N, false); //右树一定是up
    }

    public static void main(String[] args) {
        printAllFold(3);
    }
}
