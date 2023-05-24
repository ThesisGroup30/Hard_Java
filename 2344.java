import java.util.Arrays;

class Solution {
    public int minOperations(int[] nums, int[] numsDivide) {
        int minNums = Arrays.stream(nums).min().getAsInt();
        int minDivide = Arrays.stream(numsDivide).min().getAsInt();
        
        if (minDivide % minNums != 0) {
            return -1;
        }
        
        int count = 0;
        for (int num : nums) {
            if (num % minNums != 0) {
                count++;
            }
        }
        
        return count;
    }
}
