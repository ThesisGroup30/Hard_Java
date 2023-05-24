import java.util.HashMap;
import java.util.Map;

class Solution {
    public int makeSimilar(int[] nums, int[] target) {
        Map<Integer, Integer> diffCount = new HashMap<>();
        int maxDiff = 0;
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            int diff = nums[i] - target[i];
            diffCount.put(diff, diffCount.getOrDefault(diff, 0) + 1);
            maxDiff = Math.max(maxDiff, Math.abs(diff));
        }
        
        int minOperations = Integer.MAX_VALUE;
        for (int diff = -maxDiff; diff <= maxDiff; diff++) {
            int operations = 0;
            boolean valid = true;
            
            for (int count : diffCount.values()) {
                int remainder = count - Math.abs(diff);
                if (remainder % 2 != 0) {
                    valid = false;
                    break;
                }
                operations += Math.abs(remainder) / 2;
            }
            
            if (valid) {
                minOperations = Math.min(minOperations, operations);
            }
        }
        
        return minOperations;
    }
}
