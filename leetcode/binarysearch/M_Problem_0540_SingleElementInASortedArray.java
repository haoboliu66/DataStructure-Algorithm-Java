package leetcode.binarysearch;

public class M_Problem_0540_SingleElementInASortedArray {

    public int singleNonDuplicate(int[] arr) {

        int left = 0, right = arr.length - 1;
        int mid;

        while(left < right){
            mid = left +  ((right - left) >> 1);

            if(arr[mid] != arr[mid - 1] && arr[mid] != arr[mid + 1]){
                return arr[mid];
            }

            if(arr[mid] == arr[mid - 1]){
                if( (right - mid) % 2 == 0){
                    right = mid - 2;
                }else{
                    left = mid + 1;
                }
            }

            if(arr[mid] == arr[mid + 1]){
                if((mid - left) % 2 ==0){
                    left = mid + 2;
                }else{
                    right = mid - 1;
                }
            }
        }

        return arr[left];
    }

    /*
    The pairs which are on the left of the single element,
    will have the first element in an even position and the second element at an odd position.

    All the pairs which are on the right side of the single element,
    will have the first position at an odd position and the second element at an even position.
    Use this fact to decide whether to go to the left side of the array or the right side.
     */
}
