class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        // Each pig can test up to ceil(minutesToTest / minutesToDie) buckets
        int pigs = 0;
        while (Math.pow(ceil(minutesToTest / minutesToDie) + 1, pigs) < buckets) {
            pigs++;
        }
        return pigs;
    }
}
