package recursion;

/**
 * @author andy-liu
 * @date 2020/5/22 - 12:14 AM
 */
public class PlayCard {

    public static void main(String[] args) {
        int[] arr = {1,300,100,101};
        win(arr);
    }

    public static void win(int[] arr){
        int first = first(arr, 0, arr.length - 1);
        int second = second(arr, 0, arr.length - 1);
        if(first > second){
            System.out.println("A wins the game");
        }else{
            System.out.println("B wins the game");
        }

    }

    public static int first(int[] arr, int left, int right){
        if(left == right){
            return arr[left];
        }
        int getLeft = arr[left] + second(arr,left + 1, right);
        int getRight = arr[right] + second(arr,left,right - 1);
        return Math.max(getLeft,getRight);
    }

    public static int second(int[] arr, int left, int right){
        if(left == right){
            return 0;
        }
        int leftGone = first(arr,left + 1,right);
        int rightGone = first(arr,left, right - 1);
        return Math.min(leftGone, rightGone);
    }

}
