class Solution {
    public int palindromePartition(String s, int k) {
        int n = s.length();
        
        // table[i][j] stores the minimum number of changes to make s[i..j] a palindrome
        int[][] table = new int[n][n];
        for (int len = 2; len <= n; len++) {
            for (int i = 0, j = len - 1; j < n; i++, j++) {
                table[i][j] = table[i + 1][j - 1] + (s.charAt(i) == s.charAt(j) ? 0 : 1);
            }
        }
        
        // dp[i][j] stores the minimum number of changes to divide s[0..i] into j palindromes
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            dp[i][1] = table[0][i];
            for (int j = 2; j <= k; j++) {
                dp[i][j] = i + 1; // initialize with an upper bound
                for (int l = 0; l < i; l++) {
                    dp[i][j] = Math.min(dp[i][j], dp[l][j - 1] + table[l + 1][i]);
                }
            }
        }
        
        return dp[n - 1][k];
    }
}
