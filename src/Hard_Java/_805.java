package Hard_Java;

import java.util.Arrays;

public class _805 {
	public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        Arrays.sort(nums);
        for (int lenA = 1; lenA <= n/2; lenA++) {
            if (sum * lenA % n == 0) {
                if (dfs(nums, 0, lenA, sum * lenA / n)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(int[] nums, int idx, int lenA, int target) {
        if (lenA == 0) {
            return target == 0;
        }
        if (idx == nums.length || lenA > nums.length - idx) {
            return false;
        }
        if (nums[idx] > target / lenA) {
            return false;
        }
        // case 1: use nums[idx] in A
        if (dfs(nums, idx+1, lenA-1, target-nums[idx])) {
            return true;
        }
        // case 2: skip nums[idx] in A
        if (dfs(nums, idx+1, lenA, target)) {
            return true;
        }
        return false;
    }
}
