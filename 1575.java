class Solution {
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int MOD = 1000000007;
        int n = locations.length;
        int[][] dp = new int[n][fuel+1];
        dp[start][fuel] = 1;
        for (int f = fuel; f >= 0; f--) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        int dist = Math.abs(locations[i] - locations[j]);
                        if (f >= dist) {
                            dp[j][f-dist] = (dp[j][f-dist] + dp[i][f]) % MOD;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int f = 0; f <= fuel; f++) {
            ans = (ans + dp[finish][f]) % MOD;
        }
        return ans;
    }
}
