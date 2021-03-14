package leetcode;

public class M_Problem_0209_MinimumSizeSubarraySum {

    public int minSubArrayLenBruteForce(int target, int[] arr){

        if(arr == null || arr.length == 0) return 0;
        int sum = 0, len = Integer.MAX_VALUE;
        for(int i = 0 ; i < arr.length; i++){
            sum = arr[i];
            if(sum >= target){
                len = 1;
                break;
            }
            for(int j = i + 1; j < arr.length; j++){
                sum += arr[j];
                if(sum >= target){
                    len = Math.min(len, j-i+1);
                    break;
                }
            }

        }
        return len != Integer.MAX_VALUE? len: 0;
    }

    public int minSubArrayLen(int target, int[] arr) {

        int l = 0, r = 0, sum = 0, len = Integer.MAX_VALUE;
        for(l = 0; l < arr.length && r < arr.length; ){

            while(sum < target){

                sum += arr[r];
                if(r != arr.length - 1){
                    r++;
                }

                if(sum >= target){
                    len = Math.min(len, r - l);
                    break;
                }
            }
            sum -= arr[l];
            l++;

        }
        return len;

    }

}
