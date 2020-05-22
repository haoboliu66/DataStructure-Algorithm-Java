package recursion;

/**
 * @author andy-liu
 * @date 2020/5/21 - 11:21 PM
 */
public class StringPermutation {





    public static void swap(char[] arr, int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
