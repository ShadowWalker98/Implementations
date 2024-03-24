package Arrays;

import java.util.*;

public class Subarrays {
    public Subarrays() {}

    // problem 930: Binary Subarrays With Sum
    public int numSubarraysWithSum(int[] nums, int goal) {
        return helper(nums, goal) - helper(nums, goal - 1);
    }

    public int helper(int[] nums, int goal) {
        int slow = 0;
        int total = 0;
        int fast = 0;
        int currSum = 0;

        int n = nums.length;

        while(fast < n) {

            currSum += nums[fast];
            if(currSum <= goal) {
                total += (fast - slow + 1);
            } else {
                while(slow <= fast && currSum > goal) {
                    currSum -= nums[slow];
                    slow++;
                }

                total += (fast - slow + 1);
            }
            fast++;
        }
        return total;
    }





}
