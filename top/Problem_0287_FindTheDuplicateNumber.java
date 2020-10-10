package top;

public class Problem_0287_FindTheDuplicateNumber {

    public int findDuplicate1(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]);
            if (nums[index] < 0) {
                res = nums[i];
            } else {
                nums[index] = -nums[index];
            }
        }
        return Math.abs(res);
    }

    /*
    Follow-ups:
        How can we prove that at least one duplicate number must exist in nums?
        Can you solve the problem without modifying the array nums?
        Can you solve the problem using only constant, O(1) extra space?
        Can you solve the problem with runtime complexity less than O(n2)?
     */


    // linked list cycle problem
    public int findDuplicate2(int[] nums) {
        int fast = nums[0];
        int slow = nums[0];
        fast = nums[nums[fast]];
        slow = nums[slow];

        while (fast != slow) {
            fast = nums[nums[fast]];
            slow = nums[slow];
        }

        fast = nums[0];
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return fast;
    }

}
