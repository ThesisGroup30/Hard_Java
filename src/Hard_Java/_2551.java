package Hard_Java;

import java.util.Arrays;

public class _2551 {
	public int putMarbles(int[] weights, int k) {
        int n = weights.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + weights[i - 1];
        }
        
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        dp[0][1] = weights[0];
        for (int i = 1; i < n; i++) {
            dp[i][1] = prefixSum[i + 1];
            for (int j = 2; j <= k; j++) {
                for (int x = 0; x < i; x++) {
                    dp[i][j] = Math.min(dp[i][j], dp[x][j - 1] + prefixSum[i + 1] - prefixSum[x + 1]);
                }
            }
        }
        
        int minScore = Integer.MAX_VALUE;
        int maxScore = Integer.MIN_VALUE;
        for (int i = k - 1; i < n; i++) {
            minScore = Math.min(minScore, dp[i][k]);
            maxScore = Math.max(maxScore, dp[i][k]);
        }
        
        return maxScore - minScore;
    }
}
