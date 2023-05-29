package Hard_Java;

public class _410 {
	public int splitArray(int[] nums, int k) {
        int left = 0;  // The minimum possible largest sum
        int right = 0; // The maximum possible largest sum

        // Calculate the range of possible sums
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        // Perform binary search to find the minimum possible largest sum
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countSubarrays(nums, mid);

            if (count > k) {
                // If the count of subarrays is more than k,
                // we need to increase the largest sum
                left = mid + 1;
            } else {
                // If the count of subarrays is less than or equal to k,
                // we can try to minimize the largest sum
                right = mid;
            }
        }

        return left;
    }

    // Helper function to count the number of subarrays
    // with sum less than or equal to target
    private int countSubarrays(int[] nums, int target) {
        int count = 1; // At least one subarray is needed
        int sum = 0;

        for (int num : nums) {
            sum += num;
            if (sum > target) {
                count++;
                sum = num;
            }
        }

        return count;
    }
}
