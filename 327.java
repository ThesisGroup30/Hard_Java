class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Convert the input array into a long[] array
        long[] prefixSums = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }
        
        return countRangeSumRecursive(prefixSums, lower, upper, 0, nums.length);
    }
    
    private int countRangeSumRecursive(long[] prefixSums, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        }
        
        int mid = left + (right - left) / 2;
        int count = countRangeSumRecursive(prefixSums, lower, upper, left, mid)
                  + countRangeSumRecursive(prefixSums, lower, upper, mid + 1, right);
        
        int i = left;
        int j = mid + 1;
        int k = mid + 1;
        
        // Count the number of range sums that lie in [lower, upper]
        long[] merged = new long[right - left + 1];
        int p = 0;
        
        while (i <= mid) {
            while (j <= right && prefixSums[j] - prefixSums[i] < lower) {
                j++;
            }
            
            while (k <= right && prefixSums[k] - prefixSums[i] <= upper) {
                k++;
            }
            
            while (p <= right - left && prefixSums[p] < prefixSums[i]) {
                merged[p++] = prefixSums[p++];
            }
            
            merged[p++] = prefixSums[i++];
            count += k - j;
        }
        
        System.arraycopy(merged, 0, prefixSums, left, p);
        return count;
    }
}
