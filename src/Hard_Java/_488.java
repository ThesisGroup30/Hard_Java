package Hard_Java;

import java.util.HashMap;
import java.util.Map;

public class _488 {
	public int findMinStep(String board, String hand) {
        Map<Character, Integer> handCount = new HashMap<>();
        for (char ball : hand.toCharArray()) {
            handCount.put(ball, handCount.getOrDefault(ball, 0) + 1);
        }
        int minBalls = dfs(board + "#", handCount);
        return minBalls == Integer.MAX_VALUE ? -1 : minBalls;
    }
    
    private int dfs(String board, Map<Character, Integer> handCount) {
        board = removeConsecutive(board);
        if (board.equals("#")) {
            return 0;
        }
        int minBalls = Integer.MAX_VALUE;
        int i = 0;
        int need = 0;
        for (int j = 1; j < board.length(); j++) {
            if (board.charAt(j) == board.charAt(i)) {
                continue;
            }
            need = 3 - (j - i);
            if (handCount.getOrDefault(board.charAt(i), 0) >= need) {
                handCount.put(board.charAt(i), handCount.get(board.charAt(i)) - need);
                String newBoard = board.substring(0, i) + board.substring(j);
                minBalls = Math.min(minBalls, need + dfs(newBoard, handCount));
                handCount.put(board.charAt(i), handCount.get(board.charAt(i)) + need);
            }
            i = j;
        }
        return minBalls;
    }
    
    private String removeConsecutive(String board) {
        int i = 0;
        int j = 0;
        while (j < board.length()) {
            while (j < board.length() && board.charAt(i) == board.charAt(j)) {
                j++;
            }
            if (j - i >= 3) {
                return removeConsecutive(board.substring(0, i) + board.substring(j));
            } else {
                i = j;
            }
        }
        return board;
    }
}
