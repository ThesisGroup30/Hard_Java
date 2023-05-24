class Solution {
    public int totalStrength(int[] strength) {
        int n = strength.length;
        int mod = 1000000007;
        long result = 0;

        for (int i = 0; i < n; i++) {
            long minStrength = strength[i];
            long sumStrength = strength[i];
            result += minStrength * sumStrength;

            for (int j = i + 1; j < n; j++) {
                minStrength = Math.min(minStrength, strength[j]);
                sumStrength += strength[j];
                result += minStrength * sumStrength;
            }
        }

        return (int) (result % mod);
    }
}
