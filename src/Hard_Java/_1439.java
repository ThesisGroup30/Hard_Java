package Hard_Java;

import java.util.HashMap;
import java.util.Map;

public class _1439 {
	public int kthSmallest(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        int left = m;
        int right = m * 5000; // The maximum possible sum

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countArrays(mat, mid, 0, 0, k, new HashMap<>());
            
            if (count >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private int countArrays(int[][] mat, int targetSum, int row, int currentSum, int k, Map<String, Integer> memo) {
        if (row == mat.length) {
            return 1;
        }

        String key = row + "_" + currentSum;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int count = 0;

        for (int col = 0; col < mat[row].length; col++) {
            if (currentSum + mat[row][col] > targetSum) {
                break; // The remaining elements in this row will be even larger
            }

            count += countArrays(mat, targetSum, row + 1, currentSum + mat[row][col], k - count, memo);

            if (count >= k) {
                break; // Early termination if we have found enough arrays
            }
        }

        memo.put(key, count);
        return count;
    }
}
