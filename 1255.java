import java.util.*;

class Solution {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] freq = new int[26];
        for (char c : letters) {
            freq[c - 'a']++;
        }
        return dfs(words, freq, score, 0);
    }
    
    private int dfs(String[] words, int[] freq, int[] score, int idx) {
        if (idx == words.length) {
            return 0;
        }
        int maxScore = 0;
        for (int i = idx; i < words.length; i++) {
            int[] newFreq = Arrays.copyOf(freq, 26);
            int curScore = 0;
            boolean valid = true;
            for (char c : words[i].toCharArray()) {
                int index = c - 'a';
                newFreq[index]--;
                curScore += score[index];
                if (newFreq[index] < 0) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                curScore += dfs(words, newFreq, score, i + 1);
                maxScore = Math.max(maxScore, curScore);
            }
        }
        return maxScore;
    }
}
