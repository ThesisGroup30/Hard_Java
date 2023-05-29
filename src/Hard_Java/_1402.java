package Hard_Java;

import java.util.Arrays;

public class _1402 {
	public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int sum = 0, ans = 0;
            for (int j = i; j < n; j++) {
                sum += satisfaction[j];
                ans += sum;
            }
            dp[i] = Math.max(dp[i + 1], ans);
        }
        return dp[0];
    }
}
