package advanced.top;

public class Problem_0283_MoveZeroes {


    public void moveZeroes(int[] arr) {

        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[index++] = arr[i];
            }
        }
        for (; index < arr.length; index++) {
            arr[index] = 0;
        }

    }


}
