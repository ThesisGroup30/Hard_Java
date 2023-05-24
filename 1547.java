class Solution {
    public int longestDecomposition(String text) {
        int n = text.length();
        int count = 0;
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();

        for (int i = 0; i < n; i++) {
            left.append(text.charAt(i));
            right.insert(0, text.charAt(n - 1 - i));

            if (left.toString().equals(right.toString())) {
                count += 2;
                left.setLength(0);
                right.setLength(0);
            }
        }

        if (left.length() > 0)
            count++;

        return count;
    }
}
