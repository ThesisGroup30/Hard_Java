import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int maxValue = Integer.MIN_VALUE;

        for (int[] point : points) {
            int x = point[0];
            int y = point[1];

            while (!deque.isEmpty() && x - points[deque.peekFirst()][0] > k) {
                deque.pollFirst(); // Remove points that are outside the window
            }

            if (!deque.isEmpty()) {
                int prevIndex = deque.peekFirst();
                int prevX = points[prevIndex][0];
                int prevY = points[prevIndex][1];
                int equationValue = prevY - prevX + y + x;
                maxValue = Math.max(maxValue, equationValue);
            }

            while (!deque.isEmpty() && y - x >= points[deque.peekLast()][1] - points[deque.peekLast()][0]) {
                deque.pollLast(); // Remove points that have smaller equation values
            }

            deque.offerLast(points.indexOf(point));
        }

        return maxValue;
    }
}
