package Hard_Java;

import java.util.Arrays;

public class _2573 {
	public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];
        Arrays.fill(word, 'a'); // Start with all 'a' characters

        for (int i = 0; i < n; i++) {
            boolean[] used = new boolean[26]; // Tracks the characters already used in the current row

            for (int j = 0; j < i; j++) {
                used[word[j] - 'a'] = true; // Mark characters already used in previous columns
            }

            char smallestChar = 'a';

            for (int j = 0; j < n; j++) {
                if (lcp[i][j] == 0) {
                    // If lcp[i][j] is 0, it means word[i,n-1] and word[j,n-1] have no common prefix
                    // In this case, we need to choose the smallest available character for word[i]
                    while (used[smallestChar - 'a']) {
                        smallestChar++;
                    }
                    word[i] = smallestChar;
                } else if (lcp[i][j] == lcp[j][i]) {
                    // If lcp[i][j] and lcp[j][i] are equal, it means word[i,n-1] and word[j,n-1] have the same common prefix
                    // In this case, word[i] should be equal to word[j]
                    word[i] = word[j];
                } else {
                    // If neither of the above conditions is satisfied, it means lcp[i][j] and lcp[j][i] are different
                    // In this case, there is no valid string that satisfies the given lcp matrix
                    return "";
                }
            }
        }

        return new String(word);
    }
}
